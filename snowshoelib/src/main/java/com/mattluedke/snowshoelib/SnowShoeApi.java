package com.mattluedke.snowshoelib;

import org.scribe.builder.api.DefaultApi10a;
import org.scribe.model.Token;

public class SnowShoeApi extends DefaultApi10a {
  @Override
  public String getRequestTokenEndpoint() {
    return "";
  }

  @Override
  public String getAccessTokenEndpoint() {
    return "";
  }

  @Override
  public String getAuthorizationUrl(Token requestToken) {
    return "";
  }
}
