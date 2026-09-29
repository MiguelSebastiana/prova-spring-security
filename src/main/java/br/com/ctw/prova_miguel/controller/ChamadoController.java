package br.com.ctw.prova_miguel.controller;

import br.com.ctw.prova_miguel.dto.ChamadoCreateDTO;
import br.com.ctw.prova_miguel.dto.ChamadoResponseDTO;
import br.com.ctw.prova_miguel.dto.RespostaCreateDTO;
import br.com.ctw.prova_miguel.dto.StatusUpdateDTO;
import br.com.ctw.prova_miguel.service.ChamadoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Tag(
        name = "EndPoints para a realização de CRUD da entidade chamados",
        description = "Api com todos os métodos CRUD de chamados, com a autorização personilizada para cada endPoint"
)

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @Operation(
            summary = "Realiza a busca completa dos chamados",
            description = "Faz a busca por chamados, se for executado como cliente, retornara apenas os propios chamdos, caso seja executado por outro" +
                    "tipo de usuario, fará a busca de todos os chamados do banco de dados"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Lista de chamados obtida com sucesso "
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido"
            )
    })
    /**
     * Realiza a busca completa por chamados
     *
     * Faz a busca por chamados, se for executado como cliente, retornara apenas os propios chamdos, caso seja executado por outro
     * tipo de usuario, fará a busca de todos os chamados do banco de dados
     *
     * @return List<ChamadoResponseDTo> retorna uma lista contendo todos os chamados em forma de response
     */
    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listAll(){
        return ResponseEntity.ok(chamadoService.findAll());
    }

    @Operation(
            summary = "Realiza a criação de um chamado",
            description = "Faz a criação de um chamado"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Chamado criado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Erro de validação nos campos do DTO de entrada."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido"
            )
    })
    /**
     * Realiza a criação de um chamado
     *
     * Faz a criação de um chamado
     *
     * @param request recebe um request como parametro e transforma ele em entity para realizar a criação
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> create(
            @RequestBody @Valid ChamadoCreateDTO request
            ){
        return ResponseEntity.ok(chamadoService.create(request));
    }

    @Operation(
            summary = "Realiza a busca por id de um chamado",
            description = "Faz a busca de um chamado, com base no id fornecido"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Chamado localizado com sucesso"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "O perfil ROLE_CLIENTE tentou acessar um chamado que pertence a outro cliente."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    /**
     * Realiza a busca por id de um chamado
     *
     * Faz a busca de um chamado, com base no id fornecido
     *
     * @param id Recebe um id para realizar a busca do chamado desejado
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(chamadoService.findById(id));
    }

    @Operation(
            summary = "Realiza a alteração de um status com base no id",
            description = "Faz a atualização de um status com base no id fornecido, caso seja executado por um cliente a alteração nao sera realizada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Status alterado com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Transição de status inválida ou chamado já fechado"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido"
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Usuário com perfil ROLE_CLIENTE tentou executar a operação."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    /**
     * Realiza a alteração de um status com base no id
     *
     * Faz a atualização de um status com base no id fornecido, caso seja executado por um cliente a alteração nao sera realizada
     *
     * @param id Recebe um id para realizar a atualização do chamado desejado
     * @param updateRequest recebe um request de atualização de status
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    @PatchMapping("/{id}/status")
    public ResponseEntity<ChamadoResponseDTO> updateStatusById(
            @PathVariable Long id,
            @RequestBody @Valid StatusUpdateDTO updateRequest
    ){
        return ResponseEntity.ok(chamadoService.updateStatusById(id, updateRequest));
    }

    @Operation(
            summary = "Deleta um chamado com base no id",
            description = "Remove um chamado do banco de dados com base no id fornecido, caso a ação seja executado por um usuario que nao seja admin, " +
                    "a requisição ira falhar e nenhuma entidade sera deletada"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "204",
                    description = "Chamado removido com sucesso (sem corpo de resposta)."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Tentativa efetuada por usuários com perfis ROLE_CLIENTE ou ROLE_TECNICO."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    /**
     * Deleta um chamado com base no id
     *
     * Remove um chamado do banco de dados com base no id fornecido, caso a ação seja executado por um usuario que nao seja admin
     * a requisição ira falhar e nenhuma entidade sera deletada
     *
     * @param id Recebe um id para realizar a remoção do chamado desejado
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        chamadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(
            summary = "Adiciona uma resposta a um chamado",
            description = "Realiza a adição de uma resposta a um chamado, se a ação for feita por um cliente a requisição ira falhar"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "201",
                    description = "Resposta registrada com sucesso."
            ),
            @ApiResponse(
                    responseCode = "400",
                    description = "Tentativa de responder a um chamado com status FECHADO."
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Token ausente ou inválido."
            ),
            @ApiResponse(
                    responseCode = "403",
                    description = "Usuário com perfil ROLE_CLIENTE tentou registrar uma resposta técnica."
            ),
            @ApiResponse(
                    responseCode = "404",
                    description = "Chamado não encontrado"
            )
    })
    /**
     * Adiciona uma resposta a um chamado
     *
     * Realiza a adição de uma resposta a um chamado, se a ação for feita por um cliente a requisição ira falhar
     *
     * @param id Recebe um id para realizar a adição no chamado desejado
     * @param respostaCreate recebe um request de criação de resposta
     * @return ChamadoResponseDTO retorna um chamado em forma de response
     */
    @PostMapping("/{id}/respostas")
    public ResponseEntity<ChamadoResponseDTO> addResponse(
            @PathVariable Long id,
            @RequestBody @Valid RespostaCreateDTO respostaCreate
    ){
      return ResponseEntity.ok(chamadoService.addResponse(id, respostaCreate));
    }

}
