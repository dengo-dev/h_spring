package com.apple.shop;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Item {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private long id;
  private String title;
  private Integer price;
  
  public void setId(long id) {
    this.id = id;
  }
  
  public void setTitle(String title) {
    this.title = title;
  }
  
  public void setPrice(Integer price) {
    this.price = price;
  }
}
