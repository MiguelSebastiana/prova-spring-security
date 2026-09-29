package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;

import java.time.LocalDate;

public record RespostaCreateDTO (

    @Schema(
           description = "Mensagem de resposta",
           example = "Recebido, sera realizado em 4 dias"
    )
    String mensagem,

    @Schema(
            description = "Id do chamado",
            example = "1"
    )
    ChamadoEntity chamado,

    @Schema(
            description = "Id do autor da resposta",
            example = "1"
    )
    UsuarioEntity autor
){}
