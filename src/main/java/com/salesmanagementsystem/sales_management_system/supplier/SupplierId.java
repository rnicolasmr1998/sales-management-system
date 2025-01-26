package com.salesmanagementsystem.sales_management_system.supplier;

import java.util.UUID;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

public class SupplierId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected SupplierId() {
   }
    
   public SupplierId(UUID id) {
       super(id);
   }
}