package nz.co.spark.springrestsecurity.error.validator;

import java.util.Arrays;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class AuthorValidator implements ConstraintValidator<Author, String> {

    List<String> authors = Arrays.asList("Santideva", "Marie Kondo", "Martin Fowler", "Damon");

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {

        return authors.contains(value);

    }
}
