package com.redis.saver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
public class Controller {

  private final  RestClient restClient;

  @GetMapping("/user/{id}")
  public User getUserById (@PathVariable Long id){
    return restClient.getUserById(id);
  }

  @GetMapping("/user/session/{id}")
  public User getUserByIdWithSession (@PathVariable Long id, HttpServletRequest request){

    HttpSession session = request.getSession();
    session.setAttribute("Poka","dupe");
    System.out.println(session.getId());
    return restClient.getUserWithCookies(id, session);
  }

}
