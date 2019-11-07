package com.spark.digital.oidcProxy.repository;

import com.spark.digital.oidcProxy.entity.Client;
import com.spark.digital.oidcProxy.entity.Layout;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LayoutRepository extends JpaRepository<Layout, Long> {

    @Query("SELECT l FROM Layout l WHERE LOWER(l.name) = LOWER(:name)")
    Layout findClientByName(@Param("name") String name);

    @Query("SELECT l FROM Layout l WHERE LOWER(l.id) = LOWER(:id)")
    Layout findClientById(@Param("id") String id);
}
