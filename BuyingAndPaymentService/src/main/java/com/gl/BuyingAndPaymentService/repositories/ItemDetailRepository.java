package com.gl.BuyingAndPaymentService.repositories;

import com.gl.BuyingAndPaymentService.dto.Item;
import com.gl.BuyingAndPaymentService.dto.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemDetailRepository extends JpaRepository<ItemDetail, Long> {
}
