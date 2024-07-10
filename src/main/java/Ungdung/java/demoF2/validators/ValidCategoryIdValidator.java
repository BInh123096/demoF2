package Ungdung.java.demoF2.validators;
import Ungdung.java.demoF2.entities.Category;
import Ungdung.java.demoF2.validators.annotations.ValidCategoryId;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
public class ValidCategoryIdValidator implements
        ConstraintValidator<ValidCategoryId, Category> {
    @Override

    public boolean isValid(Category category,
                           ConstraintValidatorContext context) {
        return category != null && category.getId() != null;
    }
}