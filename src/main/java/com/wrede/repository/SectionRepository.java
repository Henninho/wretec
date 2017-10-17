package com.wrede.repository;

import com.wrede.domain.Section;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "sections", path = "sections")
public interface SectionRepository extends PagingAndSortingRepository<Section, Long> {
}
