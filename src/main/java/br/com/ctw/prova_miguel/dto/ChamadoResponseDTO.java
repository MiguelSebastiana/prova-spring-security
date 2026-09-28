package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.RespostaEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import br.com.ctw.prova_miguel.entity.enums.Status;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

public record ChamadoResponseDTO (
     Long id,
     String titulo,
     String descricao,
     Status status,
     UsuarioEntity cliente,
     UsuarioEntity tecnico,
     List<RespostaEntity> respostas
){}
