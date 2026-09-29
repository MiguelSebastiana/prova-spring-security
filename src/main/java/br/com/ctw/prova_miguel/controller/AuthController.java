package br.com.ctw.prova_miguel.controller;

import br.com.ctw.prova_miguel.entity.RespostaEntity;
import br.com.ctw.prova_miguel.entity.UsuarioEntity;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(
        name = "EndPoint para autentificação do usuario",
        description = "EndPoint responsavel pela realização do login e autentificação do usuario"
)
@RestController
@RequestMapping("/api/v1/auth")
public class AuthController {

    @Operation(
            summary = "Realiza o login e faz a autentificação do usuario",
            description = "Realiza o login e faz a autentificação do usuario com base no email e senha fornecidos"
    )
    @ApiResponses({
            @ApiResponse(
                    responseCode = "200",
                    description = "Autenticação realizada com sucesso"
            ),
            @ApiResponse(
                    responseCode = "401",
                    description = "Credenciais inválidas (e-mail ou senha incorretos)."
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
    @PostMapping("/login")
    public ResponseEntity<Void> login(){
        return ResponseEntity.noContent().build();
    }

}
