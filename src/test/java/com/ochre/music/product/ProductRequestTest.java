package com.ochre.music.product;

import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validation;
import jakarta.validation.Validator;
import org.junit.jupiter.api.Test;

import java.util.Calendar;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class ProductRequestTest {

    private final Validator validator = Validation.buildDefaultValidatorFactory().getValidator();

    @Test
    public void when_Product_Request_Is_Valid_then_Empty_Errors(){

        ProductRequest request = createRequest();
        Set<ConstraintViolation<ProductRequest>> errors = validator.validate(request);
        System.out.println(errors);
        assertTrue(errors.isEmpty(), "Should not have validation errors");
    }

    @Test
    public void when_Product_Request_Is_Not_Valid_then_Show_Errors(){

        ProductRequest request = new ProductRequest();
        Set<ConstraintViolation<ProductRequest>> errors = validator.validate(request);
        System.out.println(errors);
        assertEquals(1, errors.size());
    }

    private static ProductRequest createRequest(){
        Calendar date = Calendar.getInstance();
        date.set(Calendar.YEAR, 2026);
        return new ProductRequest("Title");
    }
}
