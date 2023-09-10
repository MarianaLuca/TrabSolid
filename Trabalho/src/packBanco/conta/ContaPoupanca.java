package packBanco.conta;

import packBanco.TelaInicial;

import java.io.IOException;

public class ContaPoupanca extends Conta {
    public ContaPoupanca(String nome, long cpf, String documento, int codigoDaAgencia, int senha, Double valorDeDinheiroEmConta, int numeroDaConta) {
        super(nome, cpf, documento, codigoDaAgencia, senha, valorDeDinheiroEmConta, numeroDaConta);
    }

    @Override
    public void sacar(Double valorSacado){
        if (valorSacado <= 0){
            System.out.println("+---------------------------------------------------------+");
            throw new IllegalArgumentException("|                 Valor de saque invalido                 |");
        } else if (valorSacado > getValorDeDinheiroEmConta()) {
            System.out.println("+---------------------------------------------------------+");
            throw new IllegalArgumentException("|              Valor nao disponivel em conta              |");
        } else {
            System.out.println("Sacando");
            setValorDeDinheiroEmConta(getValorDeDinheiroEmConta() - valorSacado);
        }
        try {
            TelaInicial.menuDeOpcoes();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}