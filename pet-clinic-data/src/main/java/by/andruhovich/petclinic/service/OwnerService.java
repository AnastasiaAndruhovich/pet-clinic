package by.andruhovich.petclinic.service;

import by.andruhovich.petclinic.model.Owner;

public interface OwnerService extends CrudService<Owner, Long> {
    Owner findByLastName(String lastName);
}
