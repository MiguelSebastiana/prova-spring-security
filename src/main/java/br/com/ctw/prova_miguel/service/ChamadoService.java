package br.com.ctw.prova_miguel.service;

import br.com.ctw.prova_miguel.dto.ChamadoCreateDTO;
import br.com.ctw.prova_miguel.dto.ChamadoResponseDTO;
import br.com.ctw.prova_miguel.dto.RespostaCreateDTO;
import br.com.ctw.prova_miguel.dto.StatusUpdateDTO;
import br.com.ctw.prova_miguel.entity.ChamadoEntity;
import br.com.ctw.prova_miguel.entity.RespostaEntity;
import br.com.ctw.prova_miguel.mapper.ChamadoMapper;
import br.com.ctw.prova_miguel.mapper.RespostaMapper;
import br.com.ctw.prova_miguel.repository.ChamadoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class ChamadoService {

    private final ChamadoMapper chamadoMapper;
    private final ChamadoRepository chamadoRepository;
    private final RespostaMapper respostaMapper;

    public List<ChamadoResponseDTO> findAll(){
        return chamadoRepository.findAll();
    }

    public ChamadoResponseDTO create(ChamadoCreateDTO request){

        ChamadoEntity chamado = chamadoMapper.toEntity(request);

        return chamadoMapper.toReponse(
                chamadoRepository.save(chamado)
        );
    }

    public ChamadoResponseDTO findById(Long id){
        return chamadoMapper.toReponse(
                chamadoRepository.findById(id).orElseThrow()
        );
    }

    public ChamadoResponseDTO updateStatusById(Long id, StatusUpdateDTO updateRequest){

        ChamadoEntity chamado = chamadoRepository.findById(id).orElseThrow();

        chamado.setStatus(updateRequest.status());

        return chamadoMapper.toReponse(
                chamadoRepository.save(chamado)
        );
    }

    public void delete(Long id){
        chamadoRepository.deleteById(id);
    }

    public ChamadoResponseDTO addResponse(Long id, RespostaCreateDTO respostaCreate){

        ChamadoEntity chamado = chamadoRepository.findById(id).orElseThrow();
        List<RespostaEntity> respostas = chamado.getRespostas();
        respostas.add(respostaMapper.toEntity(respostaCreate));
        chamado.setRespostas(respostas);

        return chamadoMapper.toReponse(
                chamadoRepository.save(chamado)
        );
    }
}
