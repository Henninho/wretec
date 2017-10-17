package com.wrede.repository;

import com.wrede.domain.PaintSupplier;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "paintSuppliers", path = "paintSuppliers")
public interface PaintSupplierRepository extends PagingAndSortingRepository<PaintSupplier, Long> {
}
