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

    @GetMapping("/listar")
    public List<MissaoDTO> listarMissao() {
        return missaoService.listarMissoes();
    }

    @PostMapping("/criar")
    public MissaoDTO criarMissao(@RequestBody MissaoDTO missao) {
        return missaoService.criarMissao(missao);
    }

    @PutMapping("/alterar/{id}")
    public MissaoDTO alterarMissao(@PathVariable long id, @RequestBody MissaoDTO missao) {
        return missaoService.atualizarMissao(id, missao);
    }

    @DeleteMapping("/deletar/{id}")
    public void deletarMissao(@PathVariable long id) {
        missaoService.deletarMissaoPorId(id);
    }

    @GetMapping("/listar/{id}")
    public MissaoDTO buscarMissaoPorId(@PathVariable long id) {
        return missaoService.buscarMissaoPorId(id);
    }

}
