package by.andruhovich.petclinic.repository;

import by.andruhovich.petclinic.model.Pet;
import org.springframework.data.repository.CrudRepository;

public interface PetRepository extends CrudRepository<Pet, Long> {
}
