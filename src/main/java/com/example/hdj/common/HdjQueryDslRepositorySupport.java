package com.example.hdj.common;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.transaction.annotation.Transactional;

@Transactional
public abstract class HdjQueryDslRepositorySupport extends QuerydslRepositorySupport {


    public HdjQueryDslRepositorySupport(Class<?> domainClass) {
        super(domainClass);
    }

    @Override
    @PersistenceContext
    public void setEntityManager(EntityManager entityManager) {
        super.setEntityManager(entityManager);
    }
}