package packBanco.cliente;

import java.util.ArrayList;
import java.util.List;

public class PessoaFisica {
    private String nome;
    private Long cpf;

    static List<PessoaFisica> pessoaFisicas = new ArrayList<>();
    public PessoaFisica(String nome, Long cpf) {
        this.nome = nome;
        this.cpf = cpf;
    }

    public String getNome() {
        return nome;
    }

    public Long getCpf() {
        return cpf;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setCpf(Long cpf) {
        this.cpf = cpf;
    }
}
