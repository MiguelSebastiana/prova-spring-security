package br.com.ctw.prova_miguel.entity;

import br.com.ctw.prova_miguel.entity.enums.Prioridade;
import br.com.ctw.prova_miguel.entity.enums.Status;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "tb_chamado")
@Builder
public class ChamadoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "titulo", nullable = false, length = 150)
    private String titulo;

    @Column(name = "descricao", nullable = false, columnDefinition = "TEXT")
    private String descricao;

    @Column(name = "prioridade", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Prioridade prioridade;

    @Column(name = "status", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private Status status;

    @ManyToOne
    @JoinColumn(name = "cliente_id", nullable = false)
    private UsuarioEntity cliente;

    @ManyToOne
    @JoinColumn(name = "tecnico_id")
    private UsuarioEntity tecnico;

    @Column(name = "data_criacao", nullable = false, updatable = false)
    private LocalDate dataCriacao;

    @Column(name = "data_atualizacao", nullable = false)
    private LocalDate dataAtualizacao;

    @OneToMany(mappedBy = "chamado", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<RespostaEntity> respostas = new ArrayList<>();

}
