package com.salesmanagementsystem.sales_management_system.sale;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class SaleId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected SaleId() {
   }

   public SaleId(UUID id) {
       super(id);
   }
}