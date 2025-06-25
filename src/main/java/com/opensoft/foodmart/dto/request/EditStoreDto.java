package com.opensoft.foodmart.dto.request;

import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class EditStoreDto {
   @NotNull()
   private String storeId;
   
   @NotNull()
   private Boolean active;
   
   @NotNull()
   private String storeName;
}
