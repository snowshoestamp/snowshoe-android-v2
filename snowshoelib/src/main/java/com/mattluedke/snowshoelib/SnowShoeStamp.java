package com.mattluedke.snowshoelib;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

@Keep
public class SnowShoeStamp implements Serializable {

  @SerializedName("serial")
  private String serial;

  public String getSerial() {
    return serial;
  }

  public void setSerial(String serial) {
    this.serial = serial;
  }
}
