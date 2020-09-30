package com.redistraining.receiver;

import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.session.Session;
import org.springframework.session.SessionRepository;
import org.springframework.session.data.redis.RedisSessionRepository;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Controller {

  @Autowired
  SessionRepository sessionRepository;

  @GetMapping("user/{id}")
  public User getUserById (@PathVariable Long id){
    User user = new User();
    user.setId(id);
    user.setName("Walduś");
    return user;
  }

  @GetMapping("user/session/{id}")
  public User getUserByIdWithSession (@PathVariable Long id, HttpServletRequest request, @CookieValue("JSESSIONID") String fooCookie){
    User user = new User();
    user.setId(id);
    user.setName("Walduś");
    //System.out.println(request.getCookies()[0].getName());
    //System.out.println(request.getCookies()[0].getValue());
    //System.out.println(request.getSession().getId());
    System.out.println(fooCookie);
    Session session = sessionRepository.findById(fooCookie);
    Set<String> atr = session.getAttributeNames();
    System.out.println(atr.size());
    //String atr = session.getAttribute("Poka");
    System.out.println("poka " + atr);
    return user;
  }



}
