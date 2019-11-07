package com.spark.digital.oidcProxy.repository;

import com.spark.digital.oidcProxy.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client, Long> {
    /**
     * TBC
     */

    @Query("SELECT c FROM Client c WHERE LOWER(c.name) = LOWER(:name)")
    public Client findClientByName(@Param("name") String name);
}
