package com.wrede.repository;

import com.wrede.domain.Picture;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "pictures", path = "pictures")
public interface PictureRepository extends PagingAndSortingRepository<Picture, Long> {
}
