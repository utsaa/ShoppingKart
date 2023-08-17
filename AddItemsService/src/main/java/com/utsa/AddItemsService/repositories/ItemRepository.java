package com.utsa.AddItemsService.repositories;

import com.utsa.AddItemsService.dto.Item;
import io.lettuce.core.GeoArgs;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long>{


@Query("select u from Item u where u.name=:name")
    List<Item> getItemByName(@Param("name") String name);

@Query("select u from Item u where u.provider.name=:name")
    List<Item> getItemByProvider(@Param("name") String name);


    @Query("select u from Item u where u.name=:name")
    List<Item> getItemByNameSortPrice(@Param("name") String name, Sort sort);
}
