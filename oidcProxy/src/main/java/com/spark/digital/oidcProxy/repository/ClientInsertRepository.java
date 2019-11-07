package com.spark.digital.oidcProxy.repository;

import com.spark.digital.oidcProxy.entity.Client;
import org.junit.jupiter.api.Test;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceException;
import javax.transaction.Transactional;
import java.util.Collection;

@Repository
@Transactional
public class ClientInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertClientList(Client client) {
        entityManager.createNativeQuery("INSERT INTO client (id, name, username, secret, logo_url) VALUES (?,?,?,?,?)")
                .setParameter(1, client.getId())
                .setParameter(2, client.getName())
                .setParameter(3, client.getUsername())
                .setParameter(4, client.getSecret())
                .setParameter(5, client.getLogo_url())
                .executeUpdate();
    }

    @Transactional
    public void insertClient(Client client) {
        this.entityManager.persist(client);
    }
}