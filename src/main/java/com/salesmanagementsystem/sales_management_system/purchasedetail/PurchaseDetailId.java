package com.salesmanagementsystem.sales_management_system.purchasedetail;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class PurchaseDetailId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected PurchaseDetailId() {
   }

   public PurchaseDetailId(UUID id) {
       super(id);
   }
}