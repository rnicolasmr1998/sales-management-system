package com.salesmanagementsystem.sales_management_system.sale_detail;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class sale_detailId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected sale_detailId() {
   }

   public sale_detailId(UUID id) {
       super(id);
   }
}