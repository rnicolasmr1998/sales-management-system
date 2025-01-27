package com.salesmanagementsystem.sales_management_system.payment;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class PaymentId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected PaymentId() {
   }

   public PaymentId(UUID id) {
       super(id);
   }
}