package com.opensoft.foodmart.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensoft.foodmart.annotations.CustomController;

import io.swagger.v3.oas.annotations.tags.Tag;

@CustomController
@RequestMapping("order")
@Tag(name="Order")
public class OrderController {
  @PostMapping()
  public Object createOrder() {
	  return null;
  }
  
}
