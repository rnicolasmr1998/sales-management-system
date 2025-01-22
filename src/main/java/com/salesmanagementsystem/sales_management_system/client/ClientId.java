package com.salesmanagementsystem.sales_management_system.client;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

import java.util.UUID;

public class ClientId extends AbstractEntityId<UUID> {

   /**
   * Default constructor for JPA
   */
   protected ClientId() {
   }

   public ClientId(UUID id) {
       super(id);
   }
}