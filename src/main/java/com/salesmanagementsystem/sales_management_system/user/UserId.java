package com.salesmanagementsystem.sales_management_system.user;

import java.util.UUID;

import io.github.wimdeblauwe.jpearl.AbstractEntityId;

public class UserId extends AbstractEntityId<UUID> {

/**
 * Default constructor for JPA
*/
protected UserId() {

}

public UserId(UUID id) {
    super(id);
}
}