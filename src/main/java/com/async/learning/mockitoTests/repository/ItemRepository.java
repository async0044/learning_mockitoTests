package com.async.learning.mockitoTests.repository;

import com.async.learning.mockitoTests.entity.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

    boolean existsById(Long id);
    boolean existsByTitle(String title);

    Optional<Item> findById(Long id);
    Optional<Item> findByTitle(String title);

}
