package dev.auan.cadastrodeninjas.Missoes;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    @GetMapping("/listar")
    public ResponseEntity<List<MissaoDTO>> listarMissao() {
        List<MissaoDTO> missoes = missaoService.listarMissoes();
        return ResponseEntity.ok(missoes);
    }

    @PostMapping("/criar")
    public ResponseEntity<String> criarMissao(@RequestBody MissaoDTO missao) {
        MissaoDTO missaoNova = missaoService.criarMissao(missao);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body("Missão criada com sucesso: " + missaoNova.getNome() + " (ID): " + missaoNova.getId());
    }

    @PutMapping("/alterar/{id}")
    public ResponseEntity<?> alterarMissao(@PathVariable long id, @RequestBody MissaoDTO missao) {

        MissaoDTO missaoAtualizada = missaoService.atualizarMissao(id, missao);

        if (missaoAtualizada != null) {
            return ResponseEntity.ok(missaoAtualizada);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

    @DeleteMapping("/deletar/{id}")
    public ResponseEntity<String> deletarMissao(@PathVariable long id) {

        if (missaoService.buscarMissaoPorId(id) != null) {
            missaoService.deletarMissaoPorId(id);
            return ResponseEntity.ok("Missão com ID " + id + " deletada com sucesso!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não encontrada.");
        }
    }

    @GetMapping("/listar/{id}")
    public ResponseEntity<?> buscarMissaoPorId(@PathVariable long id) {
        MissaoDTO missao = missaoService.buscarMissaoPorId(id);

        if (missao != null) {
            return ResponseEntity.ok(missao);
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Missão com ID " + id + " não foi encontrada.");
        }
    }

}
