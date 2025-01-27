package com.salesmanagementsystem.sales_management_system.saledetail;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class SaleDetailId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected SaleDetailId() {
   }

   public SaleDetailId(UUID id) {
       super(id);
   }
}