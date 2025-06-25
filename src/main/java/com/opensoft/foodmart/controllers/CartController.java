package com.opensoft.foodmart.controllers;

import org.springframework.web.bind.annotation.RequestMapping;

import com.opensoft.foodmart.annotations.CustomController;
import com.opensoft.foodmart.dto.request.CreateCartDto;

import io.swagger.v3.oas.annotations.tags.Tag;

@CustomController
@RequestMapping("admin")
@Tag(name="BackOffice")
public class CartController {
   public Object createCart(CreateCartDto req) {
	   return null;
   }
   
   
   
   
}
