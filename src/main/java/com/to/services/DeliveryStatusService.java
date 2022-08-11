package com.to.services;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.to.entities.DeliveryStatus;
import com.to.repositories.DeliveryStatusRepository;

@Service
@Transactional
public class DeliveryStatusService {

	@Autowired
	DeliveryStatusRepository deliveryStatusRepository;
	
	//method for getting the all delivery status from the db
	public List<DeliveryStatus> getAllDeliveryStatus(){
		return deliveryStatusRepository.findAll();
	}

}
