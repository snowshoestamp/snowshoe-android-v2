package com.mattluedke.snowshoelib;

import java.io.Serializable;
import java.util.Date;

public class StampResult implements Serializable {
  public SnowShoeError error;
  public SnowShoeStamp stamp;
  public boolean secure;
  public String receipt;
  public Date created;
}
