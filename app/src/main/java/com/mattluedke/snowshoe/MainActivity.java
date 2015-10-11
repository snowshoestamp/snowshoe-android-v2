package com.mattluedke.snowshoe;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import com.mattluedke.snowshoelib.OnStampListener;
import com.mattluedke.snowshoelib.StampResult;
import com.mattluedke.snowshoelib.SnowShoeView;

public class MainActivity extends AppCompatActivity implements OnStampListener {

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    SnowShoeView snowShoeView = (SnowShoeView) findViewById(R.id.snowshoeview);
    snowShoeView.setAppKeyAndSecret("YOUR_APP_KEY", "YOUR_APP_SECRET");
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

  @Override public void onStampRequestMade() {}

  @Override public void onStampResult(StampResult result) {}
}
