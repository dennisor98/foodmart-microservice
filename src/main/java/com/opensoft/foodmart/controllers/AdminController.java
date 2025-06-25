package com.opensoft.foodmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.opensoft.foodmart.annotations.CustomController;
import com.opensoft.foodmart.dto.request.EditStoreDto;
import com.opensoft.foodmart.services.StoreService;

import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@CustomController
@RequestMapping("admin")
@Tag(name="BackOffice")
public class AdminController {
	@Autowired
	private StoreService storeService;
	
   @GetMapping("/stores")
   public Object getStores() {
	   return this.storeService.getStores();
   }
   
   @PutMapping("/store")
   public Object editStore(@Valid @RequestBody() EditStoreDto req) {
	   return null;
   }
   
   
}
