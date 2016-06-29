package org.appdirect.challenge.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.appdirect.challenge.service.OAuthService;
import org.appdirect.challenge.service.SubscriptionService;
import org.appdirect.challenge.web.exception.ResponseInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.annotation.JsonView;

@RestController
public class SubscriptionController {

  @Autowired
  private OAuthService oauthService;

  @Autowired
  private SubscriptionService subscriptionService;

  /**
   * Creates the subscription.
   *
   * @param url the url
   * @return the response entity
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "create", method = RequestMethod.GET)
  @JsonView(ResponseInfo.SubscriptionSuccessResponse.class)
  public ResponseEntity<ResponseInfo> createSubscription(@RequestParam(value = "eventUrl", required = true) String url) throws MalformedURLException, IOException {
    String signedUrl = oauthService.createSignedUrl(url);
    return new ResponseEntity<ResponseInfo>(subscriptionService.createSubscription(new JSONObject(IOUtils.toString(new URL(signedUrl).openConnection().getInputStream()))),HttpStatus.OK);
  }

  /**
   * Change subscription.
   *
   * @param url the url
   * @return the response entity
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "change", method = RequestMethod.GET)
  @JsonView(ResponseInfo.SuccessResponse.class)
  public ResponseEntity<ResponseInfo> changeSubscription(@RequestParam(value = "eventUrl", required = true) String url) throws MalformedURLException, IOException{
    String signedUrl = oauthService.createSignedUrl(url);
    return new ResponseEntity<ResponseInfo>(subscriptionService.changeSubscription(new JSONObject(IOUtils.toString(new URL(signedUrl).openConnection().getInputStream()))),HttpStatus.OK);
  }

  /**
   * Cancel subscription.
   *
   * @param url the url
   * @return the response entity
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "cancel", method = RequestMethod.GET)
  @JsonView(ResponseInfo.SuccessResponse.class)
  public ResponseEntity<ResponseInfo> cancelSubscription(@RequestParam(value = "eventUrl", required = true) String url) throws MalformedURLException, IOException{
    String signedUrl = oauthService.createSignedUrl(url);
    return new ResponseEntity<ResponseInfo>(subscriptionService.cancelSubscription(new JSONObject(IOUtils.toString(new URL(signedUrl).openConnection().getInputStream()))),HttpStatus.OK);
  }
}
