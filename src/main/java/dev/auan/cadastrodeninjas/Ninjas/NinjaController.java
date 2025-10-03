package dev.auan.cadastrodeninjas.Ninjas;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private final NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService, NinjaRepository ninjaRepository) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    @Operation(summary = "Mensagem de boas vindas", description = "Essa rota da uma mensagem de boas vindas para quem acessa ela")
    public String boasVindas() {
        return "Primeira mensagem na rota!";
    }

    @PostMapping("/criar")
    @Operation(summary="Cria um novo ninja.", description = "Rota cria um novo ninja e insere no banco de dados.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Ninja criado com sucesso."),
            @ApiResponse(responseCode = "400", description = "Erro na criação do ninja.")
    })
    public ResponseEntity<String> criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        NinjaDTO ninja = ninjaService.criarNinja(ninjaDTO);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Ninja criado com sucesso: " + ninja.getNome() + " (ID): " + ninja.getId());
    }

    @GetMapping("/listar")
    @Operation(summary="Lista todos os ninjas.", description = "Rota lista todos os ninjas cadastrados.")
    public ResponseEntity<List<NinjaDTO>> mostrarTodosNinjas() {
        List<NinjaDTO> ninjas = ninjaService.listarNinjas();
        return ResponseEntity.ok(ninjas);
    }

    @GetMapping("/listar/{id}")
    @Operation(summary="Lista o ninja por ID.", description = "Rota lista um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja encontrado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado.")
    })
    public ResponseEntity<?> mostrarNinjasPorID(@PathVariable long id) {

        NinjaDTO ninja = ninjaService.listarNinjasPorID(id);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não encontrado.");
        }

    }

    @PutMapping("/alterar/{id}")
    @Operation(summary="Altera o ninja por ID.", description = "Rota altera um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja alterado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possível alterar.")
    })
    public ResponseEntity<?> atualizarNinja(
            @Parameter(description = "Usuário manda o ID no caminho da requisição.")
            @PathVariable long id,
            @Parameter(description = "Usuário manda os dados do Ninja a ser atualizado no corpo da requisição.")
            @RequestBody NinjaDTO ninjaDTO) {

        NinjaDTO ninja = ninjaService.atualizarNinja(id, ninjaDTO);

        if (ninja != null) {
            return ResponseEntity.ok(ninja);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Ninja com ID " + id + " não foi encontrado.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    @Operation(summary="Deleta o ninja por ID.", description = "Rota deleta um ninja pelo ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ninja deletado com sucesso."),
            @ApiResponse(responseCode = "404", description = "Ninja não encontrado, não foi possível deletar.")
    })
    public ResponseEntity<String> deletarNinjaPorID(@Parameter(description = "Usuário manda o ID no caminho da requisição.") @PathVariable long id) {

        if (ninjaService.listarNinjasPorID(id) != null) {
            ninjaService.deletarNinjaPorId(id);
            return ResponseEntity.ok("Ninja com ID " + id + " deletado com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("O ninja com ID " + id + " não foi encontrado.");
        }

    }

}
