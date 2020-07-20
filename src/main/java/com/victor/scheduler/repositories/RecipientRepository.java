package com.victor.scheduler.repositories;

import com.victor.scheduler.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Collection;
import java.util.List;

public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    String[] findAllByEmailIsNotNull();

    boolean existsByEmail(String email);

    @Query(value = "SELECT s.email FROM Recipient s")
    String[] allEmail();
}
