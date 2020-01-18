package com.mattluedke.snowshoelib;

import android.support.annotation.Keep;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.Date;

@Keep
public class StampResult implements Serializable {

  @SerializedName("error")
  private SnowShoeError error;
  @SerializedName("stamp")
  private SnowShoeStamp stamp;
  @SerializedName("secure")
  private boolean secure;
  @SerializedName("receipt")
  private String receipt;
  @SerializedName("created")
  private Date created;

  public SnowShoeError getError() {
    return error;
  }

  public void setError(SnowShoeError error) {
    this.error = error;
  }

  public SnowShoeStamp getStamp() {
    return stamp;
  }

  public void setStamp(SnowShoeStamp stamp) {
    this.stamp = stamp;
  }

  public boolean isSecure() {
    return secure;
  }

  public void setSecure(boolean secure) {
    this.secure = secure;
  }

  public String getReceipt() {
    return receipt;
  }

  public void setReceipt(String receipt) {
    this.receipt = receipt;
  }

  public Date getCreated() {
    return created;
  }

  public void setCreated(Date created) {
    this.created = created;
  }
}
