package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.RespostaEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import br.com.ctw.prova_miguel.entity.enums.Status;

import java.util.List;

public record ChamadoCreateDTO(
    String titulo,
    String descricao,
    Status status,
    UsuarioEntity cliente,
    UsuarioEntity tecnico,
    List<RespostaEntity> respostas
){}
