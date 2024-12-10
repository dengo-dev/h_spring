package com.apple.shop.member;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MyUserDetailsService implements UserDetailsService {
  
  private final MemberRepository memberRepository;
  
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
      
      Optional<Member> result = memberRepository.findAllByUsername(username);
      if (result.isEmpty()) {
        throw new UsernameNotFoundException("이런 아이디가 없습니다.");
      }
      Member user = result.get();
      List<GrantedAuthority> authorities = new ArrayList<>();
      authorities.add(new SimpleGrantedAuthority("일반유저"));
      
      
      return new User(user.getUsername(), user.getPassword(),authorities);
      
    }
    
  }


