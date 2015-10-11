package com.mattluedke.snowshoelib;

import android.annotation.TargetApi;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Build.VERSION_CODES;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.MotionEvent;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;

import junit.framework.Assert;

import org.scribe.builder.ServiceBuilder;
import org.scribe.model.OAuthRequest;
import org.scribe.model.Response;
import org.scribe.model.Token;
import org.scribe.model.Verb;
import org.scribe.oauth.OAuthService;

import java.util.ArrayList;
import java.util.List;

public class SnowShoeView extends View {

  private static final int NUMBER_OF_STAMP_PTS = 5;
  private static final String API_URL = "http://beta.snowshoestamp.com/api/v2/stamp";

  private OnStampListener mOnStampListener;
  private Boolean mStampBeingChecked = false;
  private String mAppKey;
  private String mAppSecret;

  public SnowShoeView(Context context) {
    super(context);
  }

  public SnowShoeView(Context context, AttributeSet attrs) {
    super(context, attrs);
  }

  public SnowShoeView(Context context, AttributeSet attrs, int defStyleAttr) {
    super(context, attrs, defStyleAttr);
  }

  @TargetApi(VERSION_CODES.LOLLIPOP)
  public SnowShoeView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
    super(context, attrs, defStyleAttr, defStyleRes);
  }

  public void setAppKeyAndSecret(String key, String secret) {
    mAppKey = key;
    mAppSecret = secret;
  }

  public void setOnStampListener(OnStampListener listener) {
    mOnStampListener = listener;
  }

  public boolean onTouchEvent(MotionEvent event) {

    if (event.getAction() == MotionEvent.ACTION_MOVE) {
      if (event.getPointerCount() == NUMBER_OF_STAMP_PTS) {
        if (!mStampBeingChecked) {
          mStampBeingChecked = true;

          List<List<Float>> requestData = new ArrayList<>(NUMBER_OF_STAMP_PTS);

          for (int i = 0; i < NUMBER_OF_STAMP_PTS; i++) {
            List<Float> pointData = new ArrayList<>(2);
            pointData.add(event.getX(i));
            pointData.add(event.getY(i));
            requestData.add(pointData);
          }

          new DownloadFilesTask().execute(requestData);
        }
      }
    }
    return true;
  }

  private class DownloadFilesTask extends AsyncTask<List<List<Float>>, Void, String> {

    @Override
    protected void onPreExecute() {
      super.onPreExecute();
      if (mOnStampListener != null) {
        mOnStampListener.onStampRequestMade();
      }
    }

    @Override
    protected String doInBackground(List<List<Float>>... requestData) {
      Assert.assertNotNull("app key can't be null", mAppKey);
      Assert.assertNotNull("app secret can't be null", mAppSecret);

      Gson gson = new Gson();
      String stringData = gson.toJson(requestData[0]);
      String base64data = Base64.encodeToString(stringData.getBytes(), Base64.DEFAULT);;

      OAuthService service = new ServiceBuilder()
          .provider(SnowShoeApi.class)
          .apiKey(mAppKey)
          .apiSecret(mAppSecret)
          .build();

      OAuthRequest request = new OAuthRequest(Verb.POST, API_URL);
      request.addBodyParameter("data", base64data);
      service.signRequest(Token.empty(), request);
      Response response = request.send();
      return response.getBody();
    }

    protected void onPostExecute(String result) {
      Gson gson = new GsonBuilder()
          .setDateFormat("yyyy-MM-dd HH:mm:ss.SSSSSS") //  "2015-10-11 12:15:18.741769"
          .create();
      StampResult stampResult;
      try {
        stampResult = gson.fromJson(result, StampResult.class);
      } catch (JsonSyntaxException jsonException) {
        stampResult = new StampResult();
        stampResult.error = new SnowShoeError();
        stampResult.error.message = result;
      }
      mOnStampListener.onStampResult(stampResult);
      mStampBeingChecked = false;
    }
  }
}
