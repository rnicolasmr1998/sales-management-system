package com.salesmanagementsystem.sales_management_system.purchase;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class PurchaseId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected PurchaseId() {
   }

   public PurchaseId(UUID id) {
       super(id);
   }
}