package Ungdung.java.demoF2.validators;

import Ungdung.java.demoF2.services.UserService;
import Ungdung.java.demoF2.validators.annotations.ValidUsername;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import lombok.RequiredArgsConstructor;
@RequiredArgsConstructor
public class ValidUsernameValidator implements ConstraintValidator<ValidUsername, String> {
    private final UserService userService;

    // No-argument constructor
    public ValidUsernameValidator() {
        this.userService = null; // Or initialize to some default if necessary
    }

    @Override
    public boolean isValid(String username, ConstraintValidatorContext context) {
        return userService == null || userService.findByUsername(username).isEmpty();
    }
}
