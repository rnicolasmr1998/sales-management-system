package com.salesmanagementsystem.sales_management_system.supplier;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

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