package com.to.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.to.entities.DeliveryStatus;

public interface DeliveryStatusRepository extends JpaRepository<DeliveryStatus, Integer> {

}
