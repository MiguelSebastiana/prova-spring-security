package br.com.ctw.prova_miguel.dto;

import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import jakarta.persistence.*;

import java.time.LocalDate;

public record RespostaCreateDTO (
    String mensagem,
    ChamadoEntity chamado,
    UsuarioEntity autor
){}
