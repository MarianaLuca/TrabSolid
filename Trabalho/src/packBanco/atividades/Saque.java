package packBanco.atividades;

import packBanco.conta.Conta;

import java.util.Objects;

public class Saque {
    public static boolean sacar(Conta conta, String documento, Double valor, int senha){
        if (Objects.equals(conta.getDocumento(), documento) && conta.getSenha() == senha && conta.getValorDeDinheiroEmConta() >= valor){
            conta.setValorDeDinheiroEmConta(conta.getValorDeDinheiroEmConta() - valor);
            return true;
        } else {
            return false;
        }
    }
}
