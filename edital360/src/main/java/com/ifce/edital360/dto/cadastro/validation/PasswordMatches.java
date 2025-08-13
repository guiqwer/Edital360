package com.ifce.edital360.dto.cadastro.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Documented
@Constraint(validatedBy = PasswordMatchesValidator.class)
@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
public @interface PasswordMatches {
    String message() default "As senhas não conferem";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};

    String passwordField() default "senha";
    String confirmPasswordField() default "confirmarSenha";
}