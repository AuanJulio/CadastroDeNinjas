package dev.auan.cadastrodeninjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.auan.cadastrodeninjas.Ninjas.NinjaDTO;
import dev.auan.cadastrodeninjas.Ninjas.NinjaModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class MissaoDTO {

    private Long id;

    private String nome;

    private String dificuldade;

    @JsonIgnore
    private List<NinjaModel> ninja;

}
