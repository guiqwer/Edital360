package com.ifce.edital360.validation;


import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = NoticeDatesValidator.class)
@Documented
public @interface ValidNoticeDates {
    String message() default "Datas do edital são inconsistentes.";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
