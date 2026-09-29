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

    /**
     * Realiza a busca completa por chamados
     *
     * Faz a busca por chamados, se for executado como cliente, retornara apenas os propios chamdos, caso seja executado por outro
     * tipo de usuario, fará a busca de todos os chamados do banco de dados
     *
     * @return List<ChamadoResponseDTo> retorna uma lista contendo todos os chamados em forma de response
     */
    public List<ChamadoResponseDTO> findAll(){
        return chamadoRepository.findAll()
                .stream().map(chamadoMapper::toReponse).toList();
    }

    /**
     * Realiza a criação de um chamado
     *
     * Faz a criação de um chamado
     *
     * @param request recebe um request como parametro e transforma ele em entity para realizar a criação
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    public ChamadoResponseDTO create(ChamadoCreateDTO request){

        ChamadoEntity chamado = chamadoMapper.toEntity(request);

        return chamadoMapper.toReponse(
                chamadoRepository.save(chamado)
        );
    }

    /**
     * Realiza a busca por id de um chamado
     *
     * Faz a busca de um chamado, com base no id fornecido
     *
     * @param id Recebe um id para realizar a busca do chamado desejado
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    public ChamadoResponseDTO findById(Long id){
        return chamadoMapper.toReponse(
                chamadoRepository.findById(id).orElseThrow()
        );
    }

    /**
     * Realiza a alteração de um status com base no id
     *
     * Faz a atualização de um status com base no id fornecido, caso seja executado por um cliente a alteração nao sera realizada
     *
     * @param id Recebe um id para realizar a atualização do chamado desejado
     * @param updateRequest recebe um request de atualização de status
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    public ChamadoResponseDTO updateStatusById(Long id, StatusUpdateDTO updateRequest){

        ChamadoEntity chamado = chamadoRepository.findById(id).orElseThrow();

        chamado.setStatus(updateRequest.status());

        return chamadoMapper.toReponse(
                chamadoRepository.save(chamado)
        );
    }

    /**
     * Deleta um chamado com base no id
     *
     * Remove um chamado do banco de dados com base no id fornecido, caso a ação seja executado por um usuario que nao seja admin
     * a requisição ira falhar e nenhuma entidade sera deletada
     *
     * @param id Recebe um id para realizar a remoção do chamado desejado
     */
    public void delete(Long id){
        chamadoRepository.deleteById(id);
    }

    /**
     * Adiciona uma resposta a um chamado
     *
     * Realiza a adição de uma resposta a um chamado, se a ação for feita por um cliente a requisição ira falhar
     *
     * @param id Recebe um id para realizar a adição no chamado desejado
     * @param respostaCreate recebe um request de criação de resposta
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
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
