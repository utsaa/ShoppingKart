package com.gl.BuyingAndPaymentService.repositories;

import com.gl.BuyingAndPaymentService.dto.Customer;
import com.gl.BuyingAndPaymentService.dto.Item;
import com.gl.BuyingAndPaymentService.dto.ItemDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {

    @Lock(LockModeType.WRITE)
    @Transactional
    @Modifying
    @Query("update Customer c set c.cartItems=?2 where c.id=?1")
    void updateCartItems(Long customerId, List<Item> cartItems);

    @Lock(LockModeType.WRITE)
    @Transactional
    @Modifying
    @Query("update Customer c set c.itemDetails=?2 where c.id=?1")
    void updateItemDetails(Long customerId, List<ItemDetail> itemDetailList);
}
