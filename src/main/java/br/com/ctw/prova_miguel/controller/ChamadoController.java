package br.com.ctw.prova_miguel.controller;

import br.com.ctw.prova_miguel.dto.ChamadoCreateDTO;
import br.com.ctw.prova_miguel.dto.ChamadoResponseDTO;
import br.com.ctw.prova_miguel.dto.RespostaCreateDTO;
import br.com.ctw.prova_miguel.dto.StatusUpdateDTO;
import br.com.ctw.prova_miguel.service.ChamadoService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/chamados")
public class ChamadoController {

    private final ChamadoService chamadoService;

    @GetMapping
    public ResponseEntity<List<ChamadoResponseDTO>> listAll(){
        return ResponseEntity.ok(chamadoService.findAll());
    }

    @PostMapping
    public ResponseEntity<ChamadoResponseDTO> create(
            @RequestBody @Valid ChamadoCreateDTO request
            ){
        return ResponseEntity.ok(chamadoService.create(request));
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChamadoResponseDTO> findById(
            @PathVariable Long id
    ){
        return ResponseEntity.ok(chamadoService.findById(id));
    }

    @PatchMapping("/{id}/status")
    public ResponseEntity<ChamadoResponseDTO> updateStatusById(
            @PathVariable Long id,
            @RequestBody @Valid StatusUpdateDTO updateRequest
    ){
        return ResponseEntity.ok(chamadoService.updateStatusById(id, updateRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(
            @PathVariable Long id
    ){
        chamadoService.delete(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/respostas")
    public ResponseEntity<ChamadoResponseDTO> addResponse(
            @PathVariable Long id,
            @RequestBody @Valid RespostaCreateDTO respostaCreate
    ){
      return ResponseEntity.ok(chamadoService.addResponse(id, respostaCreate));
    }

}
