package com.opensoft.foodmart.services;

import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.domain.OnSaleItem;
import com.opensoft.foodmart.domain.Store;
import com.opensoft.foodmart.repository.OnSaleItemRepository;
import com.opensoft.foodmart.repository.StoreRepository;

@Service
public class ItemService {
	private  OnSaleItemRepository itemsRepository; 
	@Autowired
	private StoreRepository storeRepository;

	public Object getAllItems(Pageable pageable) {
		try {
			Page<OnSaleItem> itemsPage =  this.itemsRepository.findAll(pageable);

			var items =  itemsPage.stream().map((i)->{
				return Map.of(
						"id",i.getId(),
						"name",i.getName(),
						"description",i.getDescription(),
						"frontImage",i.getImageLink(),
						"backImage",i.getImageLink(),
						"sideImage",i.getImageLink(),
						"availableQuantity",i.getAvailableQuantity(),
						"outOfStockThreshHold",i.getAlertThreshHold(),
						"active",i.getIsActive()
						);
			}).collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Request success","payload",items));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",true,"message","Oops!Error processing request"));
		}

	}
	
	
	public Object getProductsByStoreId(Pageable pageable,String storeId) {
		try {
			Optional<Store> storeOpt =  this.storeRepository.findById(storeId);

			if(storeOpt.isEmpty()) {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body(Map.of("success",false,"message","Unknown store"));
			}

			Store store = storeOpt.get();
			Page<OnSaleItem> itemsPage =  this.itemsRepository.findByStore(store, pageable);

			var items =  itemsPage.stream().map((i)->{
				return Map.of(
						"id",i.getId(),
						"name",i.getName(),
						"description",i.getDescription(),
						"frontImage",i.getImageLink(),
						"backImage",i.getImageLink(),
						"sideImage",i.getImageLink(),
						"availableQuantity",i.getAvailableQuantity(),
						"outOfStockThreshHold",i.getAlertThreshHold(),
						"active",i.getIsActive()
						);
			}).collect(Collectors.toList());

			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Request success","payload",items));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("success",true,"message","Oops!Error processing request"));
		}

	}
	
	
}
