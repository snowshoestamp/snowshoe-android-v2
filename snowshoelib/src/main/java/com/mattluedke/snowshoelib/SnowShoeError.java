package com.mattluedke.snowshoelib;

import android.support.annotation.Keep;

import java.io.Serializable;

@Keep
public class SnowShoeError implements Serializable {
  public int code;
  public String message;
}
