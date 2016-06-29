package org.appdirect.challenge.web;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.io.IOUtils;
import org.appdirect.challenge.service.OAuthService;
import org.appdirect.challenge.service.UserService;
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
public class UserController {

  @Autowired
  private OAuthService oauthService;

  @Autowired
  private UserService userService;

  /**
   * Assign user.
   *
   * @param url the url
   * @return the response entity
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "assign", method = RequestMethod.GET)
  @JsonView(ResponseInfo.SuccessResponse.class)
  public ResponseEntity<ResponseInfo> assignUser(@RequestParam(value = "url", required = true) String url) throws MalformedURLException, IOException{
    String signedUrl = oauthService.createSignedUrl(url);
    return new ResponseEntity<ResponseInfo>(userService.assignUser(new JSONObject(IOUtils.toString(new URL(signedUrl).openConnection().getInputStream()))),HttpStatus.OK);
  }

  /**
   * Unassign user.
   *
   * @param url the url
   * @return the response entity
   * @throws MalformedURLException the malformed url exception
   * @throws IOException Signals that an I/O exception has occurred.
   */
  @RequestMapping(value = "unassign", method = RequestMethod.GET)
  @JsonView(ResponseInfo.SuccessResponse.class)
  public ResponseEntity<ResponseInfo> unassignUser(@RequestParam(value = "url", required = true) String url) throws MalformedURLException, IOException{
    String signedUrl = oauthService.createSignedUrl(url);
    return new ResponseEntity<ResponseInfo>(userService.unassignUser(new JSONObject(IOUtils.toString(new URL(signedUrl).openConnection().getInputStream()))),HttpStatus.OK);
  }
}