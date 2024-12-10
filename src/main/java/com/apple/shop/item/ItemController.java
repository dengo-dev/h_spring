package com.apple.shop.item;


import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
public class ItemController {
  
  private final ItemRepository itemRepository;
  private final ItemService itemService;
  
  @GetMapping("/list")
  public String list(Model model) {
    
    List<Item> result = itemRepository.findAll();
    
    model.addAttribute("items", result);
    return "list.html";
  }
  
  @GetMapping("/write")
  public String writer() {
    return "write.html";
    
  }
  
  @PostMapping("/add")
  public String addPost(@RequestParam String title,
                        @RequestParam Integer price) {
    itemService.saveItem(title, price);
    
    return "redirect:/list";
  }
  
  
  @GetMapping("/detail/{id}")
  public String detail(@PathVariable Long id, Model model) {
    
    
    Optional<Item> result = itemRepository.findById(id);
    if (result.isPresent()) {
      model.addAttribute("a", result.get());
      return "detail.html";
    } else {
      return "redirect:/list";
    }
  }
  
  @GetMapping("/edit/{id}")
  public String edit(@PathVariable Long id, Model model) {
    
    Optional<Item> result = itemRepository.findById(id);
    if (result.isPresent()) {
      model.addAttribute("data", result.get());
      return "edit.html";
    } else {
      return "redirect:/list";
    }
  }
  
  @PostMapping("/edit")
  String editItem(@RequestParam String title,
                  @RequestParam Integer price,
                  @RequestParam Long id) {
    
    itemService.editItem(title, price, id);
    
    return "redirect:/list";
    
  }
  
  @DeleteMapping("/item")
  ResponseEntity<String> deleteItem(@RequestParam Long id){
    itemRepository.deleteById(id);
    return ResponseEntity.status(200).body("삭제완료");
  }
  
  
  
  
  @GetMapping("/test1")
  public String test(@RequestParam String name,
                     @RequestParam Integer age) {
    System.out.println(name);
    System.out.println(age);
    return "redirect:/list";
  }
  
  
  
  
  @GetMapping("/test2")
  String test2(){
    String s = new BCryptPasswordEncoder().encode("문자");
    System.out.println(s);
    return "redirect:/list";
  }
}
