package com.opensoft.foodmart.domain;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

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
public class Customer extends BaseFoodDomain {
  @Column()
  private String firstName;
  
  @Column
  private String lastName;
  
  @Column
  private String email;
  
  @Column
  String phone;
  
  @Column
  String countryCode;
  
  @OneToOne()
  @JoinColumn(name="address_id",nullable=true)
  @OnDelete(action = OnDeleteAction.SET_NULL)
  CustomerAddress address;
}
