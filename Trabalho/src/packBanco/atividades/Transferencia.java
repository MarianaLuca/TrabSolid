package packBanco.atividades;

import packBanco.conta.Conta;

import java.util.Objects;

public class Transferencia {
    public static boolean transferir(Conta envio, Conta destino, String documento, Double valor, int senha) {
        if (envio.getSenha() == senha && Objects.equals(envio.getDocumento(), documento) && envio.getSenha() == senha && envio.getValorDeDinheiroEmConta() >= valor) {
            Saque.sacar(envio, documento, valor, senha);
            Deposito.depositar(destino, documento, valor, destino.getSenha());
            return true;
        } else {
            return false;
        }
    }
}
