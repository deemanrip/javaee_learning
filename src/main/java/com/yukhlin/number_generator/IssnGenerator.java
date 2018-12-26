package com.yukhlin.number_generator;

import com.yukhlin.logger.Loggable;
import com.yukhlin.number_generator.qualifiers.EightDigits;

import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@EightDigits
public class IssnGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String issn = "8-" + Math.abs(new Random().nextInt());
        logger.info("ISSN is generated: " + issn);

        return issn;
    }
}