package dev.auan.cadastrodeninjas.Missoes;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/missoes")
public class MissaoController {

    private MissaoService missaoService;

    public MissaoController(MissaoService missaoService) {
        this.missaoService = missaoService;
    }

    // GET -- mandar requisicao para listar missoes
    @GetMapping("/listar")
    public List<MissaoModel> listarMissao() {
        return missaoService.listarMissoes();
    }

    // POST -- mandar requisicao para criar missoes
    @PostMapping("/criar")
    public MissaoModel criarMissao(@RequestBody MissaoModel missao) {
        return missaoService.criarMissao(missao);
    }

    // PUT -- mandar requisicao para alterar missoes
    @PutMapping("/alterar")
    public String alterarMissao() {
        return "Missao alterada com sucesso";
    }

    // DELETE -- mandar requisicao para deletar missoes
    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable long id) {
        missaoService.deletarMissaoPorId(id);
    }

    @GetMapping("/listar/{id}")
    public MissaoModel buscarMissaoPorId(@PathVariable long id) {
        return missaoService.buscarMissaoPorId(id);
    }

}
