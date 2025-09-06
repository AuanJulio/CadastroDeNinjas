package dev.auan.cadastrodeninjas.Ninjas;

import dev.auan.cadastrodeninjas.Missoes.MissaoModel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "tb_cadastro")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class NinjaModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @Column(unique = true)
    private String email;

    private int idade;

    // @ManyToOne Um ninja tem uma unica missao
    @ManyToOne
    @JoinColumn(name = "missao_id") // Foreign Key/Chave Estrangeira
    private MissaoModel missao;

}
