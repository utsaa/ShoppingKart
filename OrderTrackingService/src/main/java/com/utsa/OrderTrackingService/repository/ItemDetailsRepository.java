package com.utsa.OrderTrackingService.repository;

import com.utsa.OrderTrackingService.dto.Item;
import com.utsa.OrderTrackingService.dto.ItemDetail;
import com.utsa.OrderTrackingService.dto.OrderDetails;
import org.hibernate.cache.spi.support.AbstractReadWriteAccess;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemDetailsRepository extends JpaRepository<ItemDetail, Long> {

}
