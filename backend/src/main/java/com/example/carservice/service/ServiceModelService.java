package com.example.carservice.service;

import com.example.carservice.model.ServiceModel;
import java.util.List;

public interface ServiceModelService {
    ServiceModel add(ServiceModel service);

    ServiceModel update(ServiceModel service);

    ServiceModel updateStatus(Long id, ServiceModel.ServiceStatus status);

    List<ServiceModel> getAllByMasterIdAndStatus(Long masterId, ServiceModel.ServiceStatus status);
}