package br.com.ctw.prova_miguel.mapper;

import br.com.ctw.prova_miguel.dto.ChamadoCreateDTO;
import br.com.ctw.prova_miguel.dto.ChamadoResponseDTO;
import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ChamadoMapper {

    public ChamadoResponseDTO toReponse(ChamadoEntity entity){
        return new ChamadoResponseDTO(
                entity.getId(),
                entity.getTitulo(),
                entity.getDescricao(),
                entity.getStatus(),
                entity.getPrioridade(),
                entity.getCliente(),
                entity.getTecnico(),
                entity.getRespostas(),
                entity.getDataCriacao(),
                entity.getDataAtualizacao()
        );
    }

    public ChamadoEntity toEntity(ChamadoCreateDTO request){
        return ChamadoEntity.builder()
                .titulo(request.titulo())
                .descricao(request.descricao())
                .status(request.status())
                .cliente(request.cliente())
                .tecnico(request.tecnico())
                .respostas(request.respostas())
                .build();
    }
}
