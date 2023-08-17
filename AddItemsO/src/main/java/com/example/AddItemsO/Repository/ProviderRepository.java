package com.example.AddItemsO.Repository;

import com.example.AddItemsO.dto.Item;
import com.example.AddItemsO.dto.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.LockModeType;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ProviderRepository extends JpaRepository<Provider, Long> {

    @Transactional
    @Modifying
    @Query("update Provider p set p.providedItems=?2 where p.id=?1")
    void updateProvidedItems(Long id, List<Item> items);
}
