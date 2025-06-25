package com.opensoft.foodmart.domain;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StoreKyc extends BaseFoodDomain implements Serializable {
   @OneToOne()
   @JoinColumn(name="store_id")
   private Store store;
   
   @Column()
   private String frontIdImage;
   
   @Column()
   private String backIdImage;
   
   @Column()
   private String selfieImage;
   
   @Column()
   private Boolean approved;
}
