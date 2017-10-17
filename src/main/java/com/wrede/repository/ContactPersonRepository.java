package com.wrede.repository;

import com.wrede.domain.ContactPerson;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "contactPersons", path = "contactPersons")
public interface ContactPersonRepository extends PagingAndSortingRepository<ContactPerson, Long> {
}
