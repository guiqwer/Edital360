package com.ifce.edital360.dto.cadastro.validation;

import com.ifce.edital360.dto.cadastro.CadastroCompletoRequest;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.lang.reflect.Method;

public class PasswordMatchesValidator implements ConstraintValidator<PasswordMatches, Object> {

    private String passwordField;
    private String confirmPasswordField;


    @Override
    public void initialize(PasswordMatches constraintAnnotation) {
        this.passwordField = constraintAnnotation.passwordField();
        this.confirmPasswordField = constraintAnnotation.confirmPasswordField();
    }

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        try {
            Method passwordMethod = obj.getClass().getMethod(passwordField);
            Method confirmPasswordMethod = obj.getClass().getMethod(confirmPasswordField);
            String password = (String) passwordMethod.invoke(obj);
            String confirmPassword = (String) confirmPasswordMethod.invoke(obj);

            boolean matches = password != null && password.equals(confirmPassword);

            if (!matches) {
                context.disableDefaultConstraintViolation();
                context.buildConstraintViolationWithTemplate("Senhas não conferem")
                        .addPropertyNode(confirmPasswordField)
                        .addConstraintViolation();
            }

            return matches;
        } catch (Exception e) {
            return false;
        }
    }

}
