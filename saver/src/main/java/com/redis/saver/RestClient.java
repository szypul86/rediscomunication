package com.redis.saver;

import javax.servlet.http.HttpSession;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class RestClient {

  public User getUserById(Long id){
    RestTemplate rt = new RestTemplate();
    return rt.getForObject("http://localhost:9090/api/user/" + id, User.class);
  }

  public User getUserWithCookies (long id, HttpSession session) {
    RestTemplate rt = new RestTemplate();
    HttpHeaders requestHeaders = new HttpHeaders();
    requestHeaders.add("Cookie", "JSESSIONID=" + session.getId());
    HttpEntity requestEntity = new HttpEntity(null, requestHeaders);
    ResponseEntity rssResponse = rt.exchange(
        "http://localhost:9090/api/user/session/" + id,
        HttpMethod.GET,
        requestEntity,
        User.class);
    User rss = (User) rssResponse.getBody();
    return rss;
  }
}
