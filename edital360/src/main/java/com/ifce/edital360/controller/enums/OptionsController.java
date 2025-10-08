package com.ifce.edital360.controller.enums;

import com.ifce.edital360.model.enums.RequirementType;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/options")
public class OptionsController {

    @Operation(
            summary = "Listar Níveis de Escolaridade",
            description = "Retorna uma lista com todos os valores possíveis para o enum de escolaridade (RequirementType). Ideal para popular campos de seleção (dropdowns) no frontend."
    )
    @ApiResponse(responseCode = "200", description = "Lista de escolaridades retornada com sucesso.")
    @GetMapping("/escolaridade")
    public List<String> getEducationLevels() {
        return Arrays.stream(RequirementType.values())
                .map(Enum::name)
                .collect(Collectors.toList());
    }
}