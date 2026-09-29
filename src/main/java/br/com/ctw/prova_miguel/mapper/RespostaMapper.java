package br.com.ctw.prova_miguel.mapper;

import br.com.ctw.prova_miguel.dto.ChamadoResponseDTO;
import br.com.ctw.prova_miguel.dto.RespostaCreateDTO;
import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import br.com.ctw.prova_miguel.entity.RespostaEntity;
import org.springframework.stereotype.Component;

@Component
public class RespostaMapper {

    public RespostaEntity toEntity(RespostaCreateDTO request){
        return RespostaEntity.builder()
                .mensagem(request.mensagem())
                .chamado(request.chamado())
                .autor(request.autor())
                .build();
    }
}