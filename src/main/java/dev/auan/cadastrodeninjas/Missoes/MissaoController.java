package dev.auan.cadastrodeninjas.Missoes;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private final MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    @Operation(summary = "Lista todas as missões.", description = "Rota lista todas as missões cadastradas.")
    public ResponseEntity<List<MissaoDTO>> listarMissao() {
        List<MissaoDTO> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @PostMapping("/criar")
    @Operation(summary = "Cria uma nova missão.", description = "Rota cria uma nova missão e insere no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Missão criada com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação da missão.")
    })
    public ResponseEntity<String> criarMissao(
            @Parameter(description = "Usuário passa os dados para criação da missão no corpo da requisição.")
            @RequestBody MissaoDTO missao) {
        MissaoDTO missaoNova = missaoService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + missaoNova.getNome() + " (ID): " + missaoNova.getId());
    }

    @PutMapping("/alterar/{id}")
    @Operation(summary = "Altera missão por ID.", description = "Rota altera missão por ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão alterada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, não foi possível alterar.")
    })
    public ResponseEntity<?> alterarMissao(
            @Parameter(description = "Usuário passa o ID da missão no caminho da requisição.")
            @PathVariable long id,
            @Parameter(description = "Usuário passa os dados para alterar missão no corpo da requisição.")

            @RequestBody MissaoDTO missao) {

        MissaoDTO missaoAtualizada = missaoService.atualizarMissao(id, missao);

        if (missaoAtualizada != null) {
            return ResponseEntity.ok(missaoAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary = "Deleta missão por ID.", description = "Rota deleta missão pelo seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão deletada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada, não foi possível deletar.")
    })
    public ResponseEntity<String> deletarMissao(
            @Parameter(description = "Usuário passa o ID da missão a ser excluída no caminho da requisição.")
            @PathVariable long id) {

        if (missaoService.buscarMissaoPorId(id) != null) {
            missaoService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão com ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada.");
        }
    }

    @GetMapping("/listar/{id}")
    @Operation(summary = "Lista missão por ID.", description = "Rota lista uma missão por seu ID.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Missão encontrada com sucesso."),
            @ApiResponse(responseCode = "404", description = "Missão não encontrada.")
    })
    public ResponseEntity<?> buscarMissaoPorId(
            @Parameter(description = "Usuário passa o ID da missão a ser listada no caminho da requisição.")
            @PathVariable long id) {
        MissaoDTO missao = missaoService.buscarMissaoPorId(id);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

}
