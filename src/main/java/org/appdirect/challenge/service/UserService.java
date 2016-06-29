package org.appdirect.challenge.service;

import org.appdirect.challenge.domain.entities.User;
import org.appdirect.challenge.domain.repository.UserRepository;
import org.appdirect.challenge.utils.JsonUtil;
import org.appdirect.challenge.web.exception.ResponseInfo;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.DataRetrievalFailureException;
import org.springframework.stereotype.Service;

@Service
public class UserService {

  @Autowired
  private UserRepository userRepository;

  /**
   * Assign user.
   *
   * @param jsonObj the json obj
   * @return the response info
   */
  public ResponseInfo assignUser(JSONObject jsonObj){
    User user = (User) JsonUtil.deserializeJson(jsonObj.getJSONObject("payload").get("user").toString(), User.class);
    if(userRepository.findByUuid(user.getUuid()) == null){
      userRepository.save(user);
    }else{
      throw new DataIntegrityViolationException("User with uuid "+user.getUuid()+" already exists");
    }
    return new ResponseInfo(true);
  }

  /**
   * Unassign user.
   *
   * @param jsonObj the json obj
   * @return the response info
   */
  public ResponseInfo unassignUser(JSONObject jsonObj){
    User user = (User) JsonUtil.deserializeJson(jsonObj.getJSONObject("payload").get("user").toString(), User.class);
    User retrievedUser = userRepository.findByUuid(user.getUuid());
    if(retrievedUser != null){
      userRepository.delete(retrievedUser);
    }else{
      throw new DataRetrievalFailureException("User with uuid "+user.getUuid()+" does not exist.");
    }
    return new ResponseInfo(true);

  }

}
