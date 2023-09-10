package packBanco.atividades;
import packBanco.conta.Conta;

public class Deposito {
    public static boolean depositar(Conta conta, String documento, Double valor, int senha){
        if (conta.getDocumento() == documento && conta.getSenha() == senha && valor > 0){
            conta.setValorDeDinheiroEmConta(conta.getValorDeDinheiroEmConta() + valor);
            return true;
        } else {
            return false;
        }
    }
}
