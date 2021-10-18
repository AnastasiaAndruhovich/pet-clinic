package by.andruhovich.petclinic.service.springdatajpa;

import by.andruhovich.petclinic.model.Owner;
import by.andruhovich.petclinic.repository.OwnerRepository;
import by.andruhovich.petclinic.repository.PetRepository;
import by.andruhovich.petclinic.repository.PetTypeRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class OwnerSDJpaServiceTest {

    @Mock
    OwnerRepository ownerRepository;

    @Mock
    PetRepository petRepository;

    @Mock
    PetTypeRepository petTypeRepository;

    @InjectMocks
    OwnerSDJpaService service;

    Owner resultOwner;

    private static final String LAST_NAME = "Smith";

    @BeforeEach
    void setUp() {
        resultOwner = Owner.builder().id(1L).lastName(LAST_NAME).build();
    }

    @Test
    void findByLastName() {
        when(ownerRepository.findByLastName(LAST_NAME)).thenReturn(resultOwner);

        Owner smith = service.findByLastName(LAST_NAME);

        assertEquals(LAST_NAME, smith.getLastName());

        verify(ownerRepository).findByLastName(any());
    }

    @Test
    void findAll() {
        Set<Owner> returnOwners = Stream.of(Owner.builder().id(1L).build(), Owner.builder().id(2L).build()).collect(Collectors.toSet());

        when(ownerRepository.findAll()).thenReturn(returnOwners);

        Set<Owner> owners = service.findAll();

        assertEquals(returnOwners.size(), owners.size());
    }

    @Test
    void findById() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.of(resultOwner));

        Owner owner = service.findById(1L);

        assertNotNull(owner);
    }

    @Test
    void findByIdNotFound() {
        when(ownerRepository.findById(anyLong())).thenReturn(Optional.empty());

        Owner owner = service.findById(1L);

        assertNull(owner);
    }

    @Test
    void save() {
        when(ownerRepository.save(resultOwner)).thenReturn(resultOwner);

        Owner owner = service.save(resultOwner);

        assertNotNull(owner);

        verify(ownerRepository).save(any());
    }

    @Test
    void delete() {
        service.delete(resultOwner);

        verify(ownerRepository, times(1)).delete(any());
    }

    @Test
    void deleteById() {
        service.deleteById(1L);

        verify(ownerRepository).deleteById(anyLong());
    }
}