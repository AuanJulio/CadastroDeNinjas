package dev.auan.cadastrodeninjas.Missoes;

import com.fasterxml.jackson.annotation.JsonIgnore;
import dev.auan.cadastrodeninjas.Ninjas.NinjaModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_missao")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class MissaoModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String dificuldade;

    // @OneToMany Uma missao pode ter varios ninjas
    @JsonIgnore
    @OneToMany(mappedBy = "missao")
    private List<NinjaModel> ninja;

}
