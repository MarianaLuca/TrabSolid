package packBanco.conta;

import java.io.IOException;
import java.util.Objects;

public abstract class Conta {
    private String nome;
    private long cpf;
    private String documento;
    private int codigoDaAgencia;
    private int senha;
    private Double valorDeDinheiroEmConta;
    private int numeroDaConta;
    private int contaDestino;

    public Conta(String nome, long cpf, String documento, int codigoDaAgencia, int senha,
                 Double valorDeDinheiroEmConta, int numeroDaConta) {
        this.nome = nome;
        this.cpf = cpf;
        this.documento = documento;
        this.codigoDaAgencia = codigoDaAgencia;
        this.senha = senha;
        this.valorDeDinheiroEmConta = valorDeDinheiroEmConta;
        this.numeroDaConta = numeroDaConta;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public long getCpf() {
        return cpf;
    }

    public void setCpf(long cpf) {
        this.cpf = cpf;
    }

    public String getDocumento() {
        return documento;
    }

    public void setDocumento(String documento) {
        this.documento = documento;
    }

    public int getCodigoDaAgencia() {
        return codigoDaAgencia;
    }

    public void setCodigoDaAgencia(int codigoDaAgencia) {
        this.codigoDaAgencia = codigoDaAgencia;
    }

    public int getSenha() {
        return senha;
    }

    public void setSenha(int senha) {
        this.senha = senha;
    }

    public Double getValorDeDinheiroEmConta() {
        return valorDeDinheiroEmConta;
    }

    public void setValorDeDinheiroEmConta(Double valorDeDinheiroEmConta) {
        this.valorDeDinheiroEmConta = valorDeDinheiroEmConta;
    }

    public int getNumeroDaConta() {
        return numeroDaConta;
    }

    public void setNumeroDaConta(int numeroDaConta) {
        this.numeroDaConta = numeroDaConta;
    }
    public void depositar(double valor) {
        if (valor <= getValorDeDinheiroEmConta()) {
            throw new IllegalArgumentException("Valor Inválido");
        } else {
            valorDeDinheiroEmConta = valorDeDinheiroEmConta + valor;
        }
    }


    public boolean fazerDeposito(Double valorDeposito, String documento, int senha) {
        if (valorDeposito < getValorDeDinheiroEmConta() || !Objects.equals(getDocumento(), documento) || this.senha != senha) {
            return false;
        } else {
            valorDeDinheiroEmConta = valorDeDinheiroEmConta + valorDeposito;
            return true;
        }
    }

    public void sacar(){}

    public boolean fazerSaque(Double valorSaque,String documento, int senha) {
        if (valorSaque < getValorDeDinheiroEmConta() || this.senha != senha || !Objects.equals(getDocumento(), documento) || this.valorDeDinheiroEmConta < valorSaque) {
            return false;
        } else {
            valorDeDinheiroEmConta = valorDeDinheiroEmConta - valorSaque;
            return true;
        }
    }

    public boolean fazerTransferencia(Conta destino,String documento, Double valorTransferencia, int senha) {
        if (valorTransferencia < getValorDeDinheiroEmConta() || this.senha != senha || !Objects.equals(getDocumento(), documento) || this.valorDeDinheiroEmConta < valorTransferencia) {
            return false;
        } else {
            valorDeDinheiroEmConta = valorDeDinheiroEmConta - valorTransferencia;
            destino.valorDeDinheiroEmConta = destino.valorDeDinheiroEmConta + valorTransferencia;
            return true;
        }
    }


    public abstract void sacar(Double valorSacado) throws IOException;
}
