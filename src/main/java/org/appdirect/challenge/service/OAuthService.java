package org.appdirect.challenge.service;

import java.net.URI;
import java.nio.charset.Charset;
import java.util.List;

import oauth.signpost.OAuthConsumer;
import oauth.signpost.basic.DefaultOAuthConsumer;
import oauth.signpost.exception.OAuthCommunicationException;
import oauth.signpost.exception.OAuthExpectationFailedException;
import oauth.signpost.exception.OAuthMessageSignerException;
import oauth.signpost.signature.QueryStringSigningStrategy;

import org.apache.commons.codec.digest.HmacUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.utils.URLEncodedUtils;
import org.appdirect.challenge.config.OAuthConfig;
import org.appdirect.challenge.service.exception.UnauthorizedUserException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class OAuthService {

  @Autowired
  private OAuthConfig oauthConfig;

  private static final String OAUTH_CONSUMER_KEY = "oauth_consumer_key";
  private static final String OAUTH_SIGNATURE = "oauth_signature";

  /**
   * Creates the signed url.
   *
   * @param url the url
   * @return the string
   */
  public String createSignedUrl(String url){
    validateOAuthSignature(url);
    OAuthConsumer consumer = new DefaultOAuthConsumer(oauthConfig.getOauthKey(), oauthConfig.getOauthSecret());
    consumer.setSigningStrategy(new QueryStringSigningStrategy());
    String signedUrl = null;
    try {
      signedUrl = consumer.sign(url.substring(0, url.indexOf("?")));
    } catch (OAuthMessageSignerException
          | OAuthExpectationFailedException
          | OAuthCommunicationException  e) {
      throw new UnauthorizedUserException("Failed to create signed url.", e);
    }
    return signedUrl;

  }


  /**
   * Validate oauth signature.
   *
   * @param url the url
   */
  private void validateOAuthSignature(String url){

    List<NameValuePair> queryParamPairs = URLEncodedUtils.parse(URI.create(url), Charset.defaultCharset().toString());
    String oauthConsumerKey = null;
    String oauthSignature = null;

    for(NameValuePair pair : queryParamPairs){
      if(pair.getName().equalsIgnoreCase(OAUTH_CONSUMER_KEY)){
        oauthConsumerKey = pair.getValue();
      }
      else if(pair.getName().equalsIgnoreCase(OAUTH_SIGNATURE)){
        oauthSignature = pair.getValue();
      }
    }

    if(!oauthConsumerKey.equals(oauthConfig.getOauthKey())){
      throw new UnauthorizedUserException("The oauth consumer key "+oauthConsumerKey+ " retrieved from the url is not valid.");
    }

    if(!oauthSignature.equals(HmacUtils.hmacSha1(oauthConfig.getOauthKey(), oauthConfig.getOauthSecret()).toString())){
      throw new UnauthorizedUserException("The oauth signature " + oauthSignature + " retrieved from the url is not valid.");
    }
  }

}
