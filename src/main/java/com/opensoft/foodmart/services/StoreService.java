package com.opensoft.foodmart.services;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.opensoft.foodmart.domain.Store;
import com.opensoft.foodmart.domain.StoreKyc;
import com.opensoft.foodmart.domain.User;
import com.opensoft.foodmart.dto.request.EditStoreDto;
import com.opensoft.foodmart.dto.request.StoreKycDto;
import com.opensoft.foodmart.repository.StoreKycRepository;
import com.opensoft.foodmart.repository.StoreRepository;
import com.opensoft.foodmart.repository.UserRepository;

@Service
public class StoreService {
	@Autowired
	private StoreRepository storeRepository;
    @Autowired
    private StoreKycRepository kycRepository;
    
	public Object createStore() {
		return null;
	}
	
	public Object getStores() {
		try {
			List<Store> storeList = this.storeRepository.findAll();
			var stores = storeList.stream()
					.map((s)->{
						return Map.of(
								"id",s.getId(),
								"storeNumber",s.getStoreNumber(),
								"storeName",s.getName(),
								"permitNumber",s.getPermitNumber()
								);

					}).collect(Collectors.toList());
		}catch(Exception ex) {
			ex.printStackTrace();
		}

		return null;
	}

	public Object editStore(EditStoreDto req) {
		Optional<Store> storeOpt =  this.storeRepository.findById(req.getStoreId());
		if(storeOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					Map.of(
							"success",false,
							"message","Unknown store"
							));
		}

		Store store = storeOpt.get();
		store.setActive(req.getActive());
		store.setName(req.getStoreName());

		try {
			this.storeRepository.save(store);

			return ResponseEntity.status(HttpStatus.OK).body(
					Map.of(
							"success",true,
							"message","Store details updated"
							));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					Map.of(
							"success",false,
							"message","Oops!Error processing request"
							));
		}
	}
	
	public Object deleteStore(String storeId) {
		Optional<Store> storeOpt =  this.storeRepository.findById(storeId);
		if(storeOpt.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
					Map.of(
							"success",false,
							"message","Unknown store"
							));
		}
		
		Store store = storeOpt.get();
		try {
			this.storeRepository.delete(store);
			return ResponseEntity.status(HttpStatus.OK).body(
					Map.of(
							"success",true,
							"message","Store deleted"
							));
		}catch(Exception ex) {
			ex.printStackTrace();
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(
					Map.of(
							"success",false,
							"message","Oops!Error processing request"
							));
		}
	}

	
	public Object uploadStoreKYC(StoreKycDto req) {
		User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		
		Store store = user.getStore();
		if(store == null) {
			return ResponseEntity.status(HttpStatus.FORBIDDEN).body(Map.of("success",false,"message","Invalid operation"));
		}
		
		
		StoreKyc kycBuild =  StoreKyc.builder().backIdImage(req.getBackIDImage())
				.frontIdImage(req.getFrontIDImage()).selfieImage(req.getSelfieImage())
				.build();
		
		try {
			StoreKyc kyc = this.kycRepository.save(kycBuild);
			this.kycRepository.flush();
			
			return ResponseEntity.status(HttpStatus.OK).body(Map.of("success",true,"message","Documents uploaded successfully"));
		}catch(Exception ex) {
			
		}
		return null;
	}

}
