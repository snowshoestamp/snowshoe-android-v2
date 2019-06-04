package com.mattluedke.snowshoelib;

import android.support.annotation.Keep;

import java.io.Serializable;
import java.util.Date;

@Keep
public class StampResult implements Serializable {
  public SnowShoeError error;
  public SnowShoeStamp stamp;
  public boolean secure;
  public String receipt;
  public Date created;
}
