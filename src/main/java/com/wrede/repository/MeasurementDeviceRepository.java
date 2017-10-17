package com.wrede.repository;

import com.wrede.domain.MeasurementDevice;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(collectionResourceRel = "measurementDevices", path = "measurementDevices")
public interface MeasurementDeviceRepository extends PagingAndSortingRepository<MeasurementDevice, Long> {
}
