package com.yukhlin.service;

import com.yukhlin.logger.Loggable;
import com.yukhlin.model.Book;
import com.yukhlin.number_generator.NumberGenerator;
import com.yukhlin.number_generator.qualifiers.ThirteenDigits;

import javax.inject.Inject;

@Loggable
public class BookService {

    @Inject
    @ThirteenDigits
    private NumberGenerator numberGenerator;

    public Book createBook(String title, Float price, String description) {
        Book book = new Book(title, price, description);
        book.setNumber(numberGenerator.generateNumber());

        return book;
    }
}