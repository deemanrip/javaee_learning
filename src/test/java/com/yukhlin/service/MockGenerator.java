package com.yukhlin.service;

import com.yukhlin.logger.Loggable;
import com.yukhlin.number_generator.NumberGenerator;
import com.yukhlin.number_generator.qualifiers.ThirteenDigits;

import javax.enterprise.inject.Alternative;
import javax.inject.Inject;
import java.util.Random;
import java.util.logging.Logger;

@Alternative
@ThirteenDigits
public class MockGenerator implements NumberGenerator {

    @Inject
    private Logger logger;

    @Loggable
    @Override
    public String generateNumber() {
        String mock = "MOCK-" + Math.abs(new Random().nextInt());
        logger.info("Mock is generated: " + mock);

        return mock;
    }
}