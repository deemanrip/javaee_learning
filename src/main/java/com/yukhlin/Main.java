package com.yukhlin;

import com.yukhlin.model.Book;
import com.yukhlin.service.BookService;
import org.jboss.weld.environment.se.Weld;
import org.jboss.weld.environment.se.WeldContainer;

public class Main {
    public static void main(String[] args) {
        Weld weld = new Weld();
        WeldContainer container = weld.initialize();

        BookService bookService = container.select(BookService.class).get();
        Book book = bookService.createBook("Java Core", 11.6f, "Good book about Java");
        System.out.println(book);

        weld.shutdown();
    }
}