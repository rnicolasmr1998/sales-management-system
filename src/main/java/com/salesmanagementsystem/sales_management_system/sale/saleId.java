package com.salesmanagementsystem.sales_management_system.sale;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class saleId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected saleId() {
   }

   public saleId(UUID id) {
       super(id);
   }
}