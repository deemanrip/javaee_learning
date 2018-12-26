package com.yukhlin.number_generator;

import com.yukhlin.logger.Loggable;
import com.yukhlin.number_generator.qualifiers.ThirteenDigits;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@ThirteenDigits
public class IsbnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String isbn = "13-84356-" + Math.abs(new Random().nextInt());
        logger.info("ISBN is generated: " + isbn);

        return isbn;
    }
}