package dev.auan.cadastrodeninjas.Missoes;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MissaoService {

    private final MissaoRepository missaoRepository;

    private final MissaoMapper missaoMapper;

    public MissaoService(MissaoRepository missaoRepository, MissaoMapper missaoMapper) {
        this.missaoRepository = missaoRepository;
        this.missaoMapper = missaoMapper;
    }

    public List<MissaoDTO> listarMissoes() {
        List<MissaoModel> missoes = missaoRepository.findAll();
        return missoes.stream()
                .map(missaoMapper::map)
                .collect(Collectors.toList());
    }

    public MissaoDTO criarMissao(MissaoDTO missaoDTO) {
        MissaoModel missao = missaoMapper.map(missaoDTO);
        missao = missaoRepository.save(missao);
        return missaoMapper.map(missao);
    }

    public void deletarMissaoPorId(long id) {
        missaoRepository.deleteById(id);
    }

    public MissaoDTO buscarMissaoPorId(long id) {
        Optional<MissaoModel> missaoPorId = missaoRepository.findById(id);
        return missaoPorId.map(missaoMapper::map).orElse(null);
    }

    public MissaoDTO atualizarMissao(long id, MissaoDTO missaoDTO) {
        Optional<MissaoModel> missaoExistente = missaoRepository.findById(id);
        if (missaoExistente.isPresent()) {
            MissaoModel missaoAtualizada = missaoMapper.map(missaoDTO);
            missaoAtualizada.setId(id);
            MissaoModel missaoSalvo = missaoRepository.save(missaoAtualizada);
            return missaoMapper.map(missaoSalvo);
        }
        return null;
    }

}
