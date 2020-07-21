package com.victor.scheduler.repositories;

import com.victor.scheduler.models.Recipient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;


public interface RecipientRepository extends JpaRepository<Recipient, Long> {
    String[] findAllByEmailIsNotNull();

    boolean existsByEmail(String email);

    @Query(value = "SELECT s.email FROM Recipient s")
    String[] allEmail();
}

//File file = download(report rpo.uri) where report.name = property file