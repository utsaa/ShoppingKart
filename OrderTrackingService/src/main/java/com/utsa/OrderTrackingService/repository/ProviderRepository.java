package com.utsa.OrderTrackingService.repository;

import com.utsa.OrderTrackingService.dto.Item;
import com.utsa.OrderTrackingService.dto.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {


}
