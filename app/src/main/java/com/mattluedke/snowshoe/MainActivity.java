package com.mattluedke.snowshoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import com.mattluedke.snowshoelib.OnStampListener;
import com.mattluedke.snowshoelib.StampResult;
import com.mattluedke.snowshoelib.SnowShoeView;

public class MainActivity extends AppCompatActivity implements OnStampListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SnowShoeView snowShoeView = findViewById(R.id.snowshoeview);
    snowShoeView.setApiKey("YOUR_API_KEY");
    snowShoeView.setOnStampListener(this);
  }

  @Override
  public boolean onCreateOptionsMenu(Menu menu) {
    // Inflate the menu; this adds items to the action bar if it is present.
    getMenuInflater().inflate(R.menu.menu_main, menu);
    return true;
  }

  @Override
  public boolean onOptionsItemSelected(MenuItem item) {
    // Handle action bar item clicks here. The action bar will
    // automatically handle clicks on the Home/Up button, so long
    // as you specify a parent activity in AndroidManifest.xml.
    int id = item.getItemId();

    //noinspection SimplifiableIfStatement
    if (id == R.id.action_settings) {
      return true;
    }

    return super.onOptionsItemSelected(item);
  }

  @Override public void onStampRequestMade() {
      TextView textView = findViewById(R.id.text_view);
      textView.setText("SENDING");
  }

  @Override public void onStampResult(StampResult result) {

      if (result != null) {
          TextView textView = findViewById(R.id.text_view);
          if (result.getStamp() != null) {
              textView.setText(result.getStamp().getSerial() + "  " + result.getReceipt());
          }
          else {
              textView.setText("NO STAMP" + "  " + result.getReceipt());
              if (result.getError() == null) {
                  textView.setText("No Error or Stamp.");
              }
          }
      }

  }
}
