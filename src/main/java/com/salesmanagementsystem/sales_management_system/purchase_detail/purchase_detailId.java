package com.salesmanagementsystem.sales_management_system.purchase_detail;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class purchase_detailId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected purchase_detailId() {
   }

   public purchase_detailId(UUID id) {
       super(id);
   }
}