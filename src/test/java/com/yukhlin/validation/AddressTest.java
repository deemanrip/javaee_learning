package com.yukhlin.validation;

import com.yukhlin.model.Address;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class AddressTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidZipCode() {
        Address address = new Address("233 street", "New York", "NY", "DummyZip", "USA");
        Set<ConstraintViolation<Address>> violations = validator.validate(address);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("invalid zip code", violations.iterator().next().getMessage());
        Assert.assertEquals("{com.yukhlin.validation.constraints.ZipCode.message}", violations.iterator().next().getMessageTemplate());
        Assert.assertEquals("DummyZip", violations.iterator().next().getInvalidValue());
    }

    @AfterClass
    public static void close() {
        vf.close();
    }
}