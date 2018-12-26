package com.yukhlin.validation;

import com.yukhlin.model.Customer;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Set;

public class CustomerTest {

    private static ValidatorFactory vf;
    private static Validator validator;

    @BeforeClass
    public static void init() {
        vf = Validation.buildDefaultValidatorFactory();
        validator = vf.getValidator();
    }

    @AfterClass
    public static void close() {
        vf.close();
    }

    @Test
    public void shouldRaiseNoConstraintViolation() {
        Customer customer = new Customer("John", "Smith", "jsmith@gmail.com");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        Assert.assertEquals(0, violations.size());
    }

    @Test
    public void shouldRaiseConstraintViolationCauseInvalidEmail() {
        Customer customer = new Customer("John", "Smith", "DummyEmail");
        Set<ConstraintViolation<Customer>> violations = validator.validate(customer);

        Assert.assertEquals(1, violations.size());
        Assert.assertEquals("DummyEmail", violations.iterator().next().getInvalidValue());
    }
}