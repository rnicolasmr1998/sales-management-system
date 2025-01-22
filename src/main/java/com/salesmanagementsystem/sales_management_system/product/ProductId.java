package com.salesmanagementsystem.sales_management_system.product;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class ProductId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected ProductId() {
   }

   public ProductId(UUID id) {
       super(id);
   }
}