package com.opensoft.foodmart.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.opensoft.foodmart.annotations.CustomController;
import com.opensoft.foodmart.services.ItemService;

import io.swagger.v3.oas.annotations.tags.Tag;

@CustomController
@RequestMapping("item")
@Tag(name="Items")
public class ItemsController {
	@Autowired
	private ItemService itemService;

	@GetMapping()
	public Object getItems(
			@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize
			) {
		return this.itemService.getAllItems(PageRequest.of(pageNumber,pageSize));
	}

	@GetMapping("/store")
	public Object getStoreItems(
			@RequestParam("pageNumber") Integer pageNumber,
			@RequestParam("pageSize") Integer pageSize,
			@RequestParam("store_id") String storeId
			) {
		return this.itemService.getProductsByStoreId(PageRequest.of(pageNumber,pageSize), storeId);
	}


}
