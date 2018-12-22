package com.yukhlin.service_test;

import com.yukhlin.service.BookService;
import com.yukhlin.model.Book;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;
import org.junit.Assert;
import org.junit.Test;

public class BookServiceTest {

    @Test
    public void shouldCheckNumberIsMOCK() {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        BookService bookService = container.select(BookService.class).get();
        Book book = bookService.createBook("Java Core", 11.6f, "Good book about Java");
        Assert.assertTrue(book.getNumber().startsWith("MOCK"));

        weld.shutdown();
    }
}