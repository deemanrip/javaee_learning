package com.yukhlin.validation;

import com.yukhlin.validation.constraints.ZipCode;
import com.yukhlin.validation.qualifiers.USA;

import javax.inject.Inject;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class ZipCodeValidator implements ConstraintValidator<ZipCode, String> {

    @Inject
    @USA
    private ZipCodeChecker zipCodeChecker;

    private Pattern zipPattern = Pattern.compile("\\d{5}(-\\d{5})?");

    @Override
    public void initialize(ZipCode constraintAnnotation) {

    }

    @Override
    public boolean isValid(String value, ConstraintValidatorContext context) {
        if(value == null) {
            return true;
        }

        Matcher matcher = zipPattern.matcher(value);
        if(!matcher.matches()) {
            return false;
        }

        return zipCodeChecker.isZipCodeValid(value);
    }
}
