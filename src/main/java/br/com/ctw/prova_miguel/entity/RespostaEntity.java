package br.com.ctw.prova_miguel.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_resposta")
@Builder
public class RespostaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "mensagem", nullable = false, columnDefinition = "TEXT")
    private String mensagem;

    @ManyToOne
    @JoinColumn(name = "chamado_id", nullable = false)
    private ChamadoEntity chamado;

    @ManyToOne
    @JoinColumn(name = "autor_id", nullable = false)
    private UsuarioEntity autor;

    @Column(name = "data_cricao", nullable = false, updatable = false)
    private LocalDate dataCriacao;
}
