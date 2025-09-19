package dev.auan.cadastrodeninjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MissaoService {

    private MissaoRepository missaoRepository;

    public MissaoService(MissaoRepository missaoRepository) {
        this.missaoRepository = missaoRepository;
    }

    public List<MissaoModel> listarMissoes() {
        return missaoRepository.findAll();
    }

    public MissaoModel criarMissao(MissaoModel missao) {
        return missaoRepository.save(missao);
    }

    public void deletarMissaoPorId(long id) {
        missaoRepository.deleteById(id);
    }

    public MissaoModel buscarMissaoPorId(long id) {
        Optional<MissaoModel> missaoPorId = missaoRepository.findById(id);
        return missaoPorId.orElse(null);
    }

}
