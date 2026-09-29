package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;

public record StatusUpdateDTO (
    @Schema(
            description = "Estado atual do chamado",
            example = "Em andamento (ainda nao foi concluido)"
    )
    Status status
){}
