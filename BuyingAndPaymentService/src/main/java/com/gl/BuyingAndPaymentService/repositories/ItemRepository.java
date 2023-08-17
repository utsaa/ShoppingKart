package com.gl.BuyingAndPaymentService.repositories;

import com.gl.BuyingAndPaymentService.dto.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {
}
