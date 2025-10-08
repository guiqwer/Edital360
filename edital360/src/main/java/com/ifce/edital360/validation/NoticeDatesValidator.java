package com.ifce.edital360.validation;

import com.ifce.edital360.dto.edital.NoticeCreateDto;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class NoticeDatesValidator implements ConstraintValidator<ValidNoticeDates, NoticeCreateDto> {

    @Override
    public boolean isValid(NoticeCreateDto dto, ConstraintValidatorContext context) {
        if (dto.getInitialDate() == null || dto.getEndDate() == null || dto.getExamDate() == null)
            return false;

        boolean valid = true;

        if (dto.getInitialDate().isAfter(dto.getEndDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A data inicial de inscrição deve ser anterior à data final de inscrição.")
                    .addPropertyNode("initialDate")
                    .addConstraintViolation();
            valid = false;
        }

        if (dto.getExamDate().isBefore(dto.getEndDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A data do exame deve ser após a data final de inscrição.")
                    .addPropertyNode("examDate")
                    .addConstraintViolation();
            valid = false;
        }

        if(dto.getExemption().getExemptionEndDate().isAfter(dto.getInitialDate())) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("A data final da isenção deve ser antes da data inicial de inscrição.")
                    .addPropertyNode("exemption.exemptionEndDate")
                    .addConstraintViolation();
            valid = false;
        }

        return valid;
    }
}
