package dev.auan.cadastrodeninjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService, NinjaRepository ninjaRepository) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Primeira mensagem na rota!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public NinjaDTO criarNinja(@RequestBody NinjaDTO ninjaDTO) {
        return ninjaService.criarNinja(ninjaDTO);
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaDTO> mostrarTodosNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaDTO mostrarNinjasPorID(@PathVariable long id) {
        return ninjaService.listarNinjasPorID(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterar/{id}")
    public NinjaDTO atualizarNinja(@PathVariable long id, @RequestBody NinjaDTO ninjaDTO) {
        return ninjaService.atualizarNinja(id, ninjaDTO);
    }

    // Deletar ninja (DELETE)
    @DeleteMapping("/deletar/{id}")
    public void deletarNinjaPorID(@PathVariable long id) {
        ninjaService.deletarNinjaPorId(id);
    }

}
