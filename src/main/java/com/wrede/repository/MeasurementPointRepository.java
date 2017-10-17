package com.wrede.repository;

import com.wrede.domain.MeasurementPoint;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "measurementPoints", path = "measurementPoints")
public interface MeasurementPointRepository extends PagingAndSortingRepository<MeasurementPoint, Long> {
}
