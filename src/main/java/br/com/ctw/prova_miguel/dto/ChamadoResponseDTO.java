package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.RespostaEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import br.com.ctw.prova_miguel.entity.enums.Prioridade;
import br.com.ctw.prova_miguel.entity.enums.Status;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import org.springframework.cglib.core.Local;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public record ChamadoResponseDTO (

     @Schema(
             description = "Id identificador do chamado",
             example = "1"
     )
     Long id,

     @Schema(
             description = "Titulo do chamado",
             example = "Problema no ar-condicionado",
             maxLength = 150
     )
     String titulo,

     @Schema(
             description = "Descricao do chamado",
             example = "Ar-condicionado parou de funcionar"
     )
     String descricao,

     @Schema(
             description = "Estado atual do chamado",
             example = "Em andamento (ainda nao foi concluido)"
     )
     Status status,

     @Schema(
             description = "Prioridade do chamado",
             example = "ALTA"
     )
     Prioridade prioridade,

     @Schema(
             description = "Id do cliente responsavel por abrir o chamado",
             example = "1"
     )
     UsuarioEntity cliente,

     @Schema(
             description = "Id do tecnico responsavel por resolver o chamado",
             example = "2"
     )
     UsuarioEntity tecnico,

     @Schema(
             description = "Respostas do chamado",
             example = "Seu chamado foi recebido, ira ser resolvido no prazo de 3 semanas"
     )
     List<RespostaEntity> respostas,

     @Schema(
             description = "Data de criação do chamado",
             example = "28/09/2026"
     )
     LocalDate dataCriacao,

     @Schema(
             description = "Data de atualizao do chamado",
             example = "29/09/2026"
     )
     LocalDate dataAtualizacao
){}
