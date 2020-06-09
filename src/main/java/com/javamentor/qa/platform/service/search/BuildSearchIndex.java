package com.javamentor.qa.platform.service.search;

import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.jpa.Search;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
@Transactional
public class BuildSearchIndex implements ApplicationListener<ApplicationReadyEvent> {

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        try {
            FullTextEntityManager fullTextEntityManager =
                    Search.getFullTextEntityManager(entityManager);
            fullTextEntityManager.createIndexer().startAndWait();
        }
        catch (InterruptedException e) {
            System.out.println("An error occurred trying to build the search index: " + e.toString());
        }
    }
}
