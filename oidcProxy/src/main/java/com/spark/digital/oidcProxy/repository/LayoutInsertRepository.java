package com.spark.digital.oidcProxy.repository;

import com.spark.digital.oidcProxy.entity.Client;
import com.spark.digital.oidcProxy.entity.Layout;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

@Repository
@Transactional
public class LayoutInsertRepository {

    @PersistenceContext
    private EntityManager entityManager;

    @Transactional
    public void insertLayoutCustom(Layout layout) {
        entityManager.createNativeQuery("INSERT INTO layout (id, name, primary_colour, secondary_colour, logo_url) VALUES (?,?,?,?,?)")
                .setParameter(1, layout.getId())
                .setParameter(2, layout.getName())
                .setParameter(3, layout.getPrimary_colour())
                .setParameter(4, layout.getSecondary_colour())
                .setParameter(5, layout.getLogo_url())
                .executeUpdate();
    }

    @Transactional
    public void insertLayout(Layout layout) {
        this.entityManager.persist(layout);
    }
}