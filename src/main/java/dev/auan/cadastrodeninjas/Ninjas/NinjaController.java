package dev.auan.cadastrodeninjas.Ninjas;

import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ninjas")
public class NinjaController {

    private NinjaService ninjaService;

    public NinjaController(NinjaService ninjaService) {
        this.ninjaService = ninjaService;
    }

    @GetMapping("/boasVindas")
    public String boasVindas() {
        return "Primeira mensagem na rota!";
    }

    // Adicionar ninja (CREATE)
    @PostMapping("/criar")
    public String criarNinja() {
        return "Ninja criado";
    }

    // Mostrar todos os ninjas (READ)
    @GetMapping("/listar")
    public List<NinjaModel> mostrarTodosNinjas() {
        return ninjaService.listarNinjas();
    }

    // Mostrar ninja por ID (READ)
    @GetMapping("/listar/{id}")
    public NinjaModel mostrarNinjasPorID(@PathVariable long id) {
        return ninjaService.listarNinjasPorID(id);
    }

    // Alterar dados dos ninjas (UPDATE)
    @PutMapping("/alterarID")
    public String alterarNinjaPorID() {
        return "Alterar ninja por ID";
    }

    // Deletar ninja (DELETE)
    @DeleteMapping("/deletarID")
    public String deletarNinjaPorID() {
        return "Deletar ninja por ID";
    }

}
