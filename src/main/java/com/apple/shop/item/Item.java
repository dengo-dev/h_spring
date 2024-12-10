package com.apple.shop.item;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Item {
  
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  public Long id;
  private String title;
  private Integer price;
  
}