package com.apple.shop;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;

@Service
@RequiredArgsConstructor
public class ItemService {

  private final ItemRepository itemRepository;
  public void saveItem(String title, Integer price){
    
    
    Item item = new Item();
    item.setTitle(title);
    item.setPrice(price);
    itemRepository.save(item);
    
  }

  public void editItem(String title,
                       Integer price,
                       Long id){
    Item item = new Item();
    item.setId(id);
    item.setTitle(title);
    item.setPrice(price);
    itemRepository.save(item);
  }
 }