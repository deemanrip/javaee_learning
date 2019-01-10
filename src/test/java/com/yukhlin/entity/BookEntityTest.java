package com.yukhlin.entity;

import com.yukhlin.model.Book;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.validation.ConstraintViolationException;

public class BookEntityTest {
    private static EntityManagerFactory emf = Persistence.createEntityManagerFactory("bookShopTest");
    private EntityManager em;
    private EntityTransaction tx;

    @Before
    public void initEntityManager() {
        em = emf.createEntityManager();
        tx = em.getTransaction();
    }

    @After
    public void closeEntityManager() {
        if (em != null) em.close();
    }

    @Test
    public void shouldFindBook() {
        Book book = em.find(Book.class, 1001L);
        Assert.assertEquals("Learning Java EE 7", book.getTitle());
    }

    @Test
    public void shouldCreateH2G2Book() {
        Book book = new Book("H2G2", 12.5F, "Test book",
                "1-84023-742-2", 354, false);

        tx.begin();
        em.persist(book);
        tx.commit();

        Assert.assertNotNull("Id can't be null", book.getId());

        book = em.createNamedQuery("findBookH2G2", Book.class).getSingleResult();
        Assert.assertEquals("Test book", book.getDescription());
    }

    @Test(expected = ConstraintViolationException.class)
    public void shouldRaiseConstraintViolationCauseNullTitle() {
        Book book = new Book(null, 12.5F, "Test book",
                "1-84023-742-2", 354, false);
        em.persist(book);
    }
}