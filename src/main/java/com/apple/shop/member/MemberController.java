package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class MemberController {
  
  private final MemberRepository memberRepository;
  private final PasswordEncoder passwordEncoder;
  
  @GetMapping("/register")
  String register() {
    return "register.html";
  }
  
  @PostMapping("/member")
  String addMember(@ModelAttribute Member member) {
//    BCryptPasswordEncoder pE = new BCryptPasswordEncoder();
    String password = passwordEncoder.encode(member.getPassword());
    member.setPassword(password);
    memberRepository.save(member);
    
    return "redirect:/list";
  }
  
  @GetMapping("/login")
  String login() {
    Optional<Member> oh = memberRepository.findAllByUsername("admin");
    System.out.println(oh.get().toString());
    return "login.html";
  }
  
  @GetMapping("/my-page")
  public String myPage(Authentication auth){
    System.out.println(auth);
    System.out.println(auth.getName());
    System.out.println(auth.isAuthenticated());
    System.out.println(auth.getAuthorities().contains(new SimpleGrantedAuthority("일반유저")));
    
    return "mypage.html";
  }
  
}
