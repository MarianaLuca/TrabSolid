package packBanco;

import packBanco.atividades.Transferencia;
import packBanco.cliente.PessoaFisica;
import packBanco.conta.Conta;
import packBanco.conta.ContaCorrente;
import packBanco.conta.ContaPoupanca;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class TelaInicial {
    private static BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    static Scanner scan = new Scanner(System.in);
    private static final String NOME_DO_CLIENTE = "nome do cliente: ";
    private static final String DOCUMENTO_DO_CLIENTE = "documento do cliente: ";
    static List<PessoaFisica> pessoaFisicas = new ArrayList<>();
    static List<ContaCorrente> contaCorrentes = new ArrayList<>();
    static List<ContaPoupanca> contaPoupancas = new ArrayList<>();
    static HashMap<String, List<Conta>> contas = new HashMap<>();

    public static void menuDeOpcoes() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                          Menu                           |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|      [1] -> Cadastrar Cliente (Pessoa Fisica)           |");
        System.out.println("|      [2] -> Cadastrar Conta Corrente                    |");
        System.out.println("|      [3] -> Cadastrar Conta Poupanca                    |");
        System.out.println("|      [4] -> Efetuar Deposito                            |");
        System.out.println("|      [5] -> Efetuar Saque                               |");
        System.out.println("|      [6] -> Efetuar Transferencia                       |");
        System.out.println("|      [7] -> Consultar Cliente                           |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|      [0] -> Sair                                        |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Selecione a opção desejada: < |");
        String opcao = reader.readLine();

        switch (opcao) {
            case "1":
                cadastrarCliente();
                break;
            case "2":
                criarContaCorrente();
                break;
            case "3":
                criarContaPoupanca();
                break;
            case "4":
                depositar();
                break;
            case "5":
                sacar();
                break;
            case "6":
                trasferir();
                break;
            case "7":
                verificarCliente();
                break;
            case "0":
                System.out.println("+--------------------------------+");
                System.out.println("|            Saindo...           |");
                System.out.println("+--------------------------------+");
                System.exit(0);
                break;
            default:
                System.out.println("+---------------------------------------------+");
                System.out.println("|             Opcao invalida                  |");
                System.out.println("+---------------------------------------------+");
                menuDeOpcoes();
                break;
        }
    }

    public static void cadastrarCliente() throws IOException {
        Long cpf;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                   Cadastrar Cliente                     |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o nome: < |");
        String nomeCliente = reader.readLine();
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o CPF: < |");
        cpf = Long.valueOf(reader.readLine());

        if (String.valueOf(cpf).length() != 11) {
            System.out.println("+-----------------------------------------------------+");
            System.out.println("|  * CPF inválido, o mesmo deve possuir 11 digitos *  |");
            System.out.println("+-----------------------------------------------------+");
            menuDeOpcoes();
        }
        PessoaFisica pessoaFisica1 = new PessoaFisica(nomeCliente, cpf);
        pessoaFisicas.add(pessoaFisica1);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|              *  Cadastro Confirmado  *                  |");
        System.out.println("+---------------------------------------------------------+");
        menuDeOpcoes();
    }

    public static void criarContaCorrente() throws IOException {
        Random randomCorrente = new Random();
        int numeroDaContaCorrente = randomCorrente.nextInt(1000);

        String nomeQueSeraCadastradoNaContaCorrente;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Cadastrar Conta Corrente                 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o nome: < |");
        nomeQueSeraCadastradoNaContaCorrente = reader.readLine();

        boolean nomeEncontrado = false;
        PessoaFisica clienteEncontrado = null;

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaCorrente)) {
                nomeEncontrado = true;
                clienteEncontrado = pessoaFisica;
                break;
            }
        }

        if (nomeEncontrado) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                  *  Nome encontrado  *                  |");
            System.out.println("+---------------------------------------------------------+");

            ContaCorrente contaCorrente1 = new ContaCorrente(clienteEncontrado.getNome(), clienteEncontrado.getCpf(), setDocumento(DOCUMENTO_DO_CLIENTE), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaCorrente);
            contaCorrentes.add(contaCorrente1);

            for (Conta conta : contaCorrentes) {
                if (conta.getNome().equals(nomeQueSeraCadastradoNaContaCorrente)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                Conta Corrente Cadastrada                |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| Nome: " + conta.getNome());
                    System.out.println("| CPF: " + conta.getCpf());
                    System.out.println("| Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                  *  Nome não encontrado  *              |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }
    }

    public static void criarContaPoupanca() throws IOException {
        Random randomPoupanca = new Random();
        int numeroDaContaPoupanca = randomPoupanca.nextInt(1000);

        String nomeQueSeraCadastradoNaContaPoupanca;
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Cadastrar Conta Poupanca                 |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o nome: < |");
        nomeQueSeraCadastradoNaContaPoupanca = reader.readLine();

        boolean nomeEncontrado = false;
        PessoaFisica clienteEncontrado = null;

        for (PessoaFisica pessoaFisica : pessoaFisicas) {
            if (pessoaFisica.getNome().equals(nomeQueSeraCadastradoNaContaPoupanca)) {
                nomeEncontrado = true;
                clienteEncontrado = pessoaFisica;
                break;
            }
        }

        if (nomeEncontrado) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                  *  Nome encontrado  *              |");
            System.out.println("+---------------------------------------------------------+");
            ContaPoupanca contaPoupanca1 = new ContaPoupanca(clienteEncontrado.getNome(), clienteEncontrado.getCpf(), setDocumento(DOCUMENTO_DO_CLIENTE), 0001, setSenha(), setValorInicialDepositadoEmConta(), numeroDaContaPoupanca);
            contaPoupancas.add(contaPoupanca1);

            for (Conta conta : contaPoupancas) {
                if (conta.getNome().equals(nomeQueSeraCadastradoNaContaPoupanca)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                 Conta Poupanca Cadastrada!!!            |");
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| Nome: " + conta.getNome());
                    System.out.println("| CPF: " + conta.getCpf());
                    System.out.println("| Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| Valor: " + conta.getValorDeDinheiroEmConta());
                    menuDeOpcoes();
                }
            }
            menuDeOpcoes();
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                  *  Nome não encontrado  *              |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }
    }

    private static Conta selecionarTipoConta(List<Conta> contas, String tipo) {
        for (Conta c : contas) {
            if ((tipo.equalsIgnoreCase("corrente") && c instanceof ContaCorrente) || (tipo.equalsIgnoreCase("poupanca") && c instanceof ContaPoupanca)) {
                return c;
            }
        }
        return null;
    }

    public static void depositar() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Realizar Deposito                    |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > DIgite o nome: < |");
        String nomeCliente = reader.readLine();

        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o numeero do documento: <|");
        String documentoCliente = reader.readLine();

        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                Selecione o tipo de conta:               |");
        System.out.println("|                <------------------------>               |");
        System.out.println("|                   [C] - Corrente                        |");
        System.out.println("|                   [P] - Poupanca                        |");
        System.out.println("+---------------------------------------------------------+");

        String tipoConta = reader.readLine();

        Conta conta = null;
        if (tipoConta.equalsIgnoreCase("P")) {
            for (ContaPoupanca contaPoupanca : contaPoupancas) {
                if (contaPoupanca.getNome().equals(nomeCliente)) {

                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| Digite o código da agencia:");
                    int codAgencia = Integer.parseInt(reader.readLine());

                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| Digite o numero da conta:");
                    int numeroDaConta = Integer.parseInt(reader.readLine());

                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                }
            }
        } else if (tipoConta.equalsIgnoreCase("C")) {
            Conta conta0 = null;
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Digite o código da agencia:");
            int codigoAgencia = Integer.parseInt(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Digite o numero da conta:");
            int numeroDaConta = Integer.parseInt(reader.readLine());

            for (Conta conta1 : contaCorrentes) {
                if (conta1.getCodigoDaAgencia() == codigoAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                    conta = conta1;
                    break;
                }
            }
        } else if (conta == null) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|              *  Conta não encontrada  *                 |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }

        System.out.println("+---------------------------------------------------------+");
        System.out.print("| DIgite o valor de deposito: ");
        Double valorSaque = Double.parseDouble(reader.readLine());

        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite a senha: ");
        int senha = Integer.parseInt(reader.readLine());

        if (conta.fazerDeposito(valorSaque, documentoCliente, senha)) {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                  *  Deposito Confirmado  *              |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|      *  Deposito nao realizado, tente novamente  *      |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }
    }

    public static void sacar() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Realizar Saque                       |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite o nome do cliente: ");
        String nomeCliente = scan.nextLine();
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > Digite o documento do cliente: ");
        String documentoCliente = scan.nextLine();

        System.out.println("+---------------------------------------------------------+");
        System.out.println("|               Qual é o tipo de conta?                   |");
        System.out.println("+               <---------------------->                  +");
        System.out.println("|                   C - corrente                          |");
        System.out.println("|                   P - poupanca                          |");
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| > ");
        String tipoConta = reader.readLine();
        Conta conta = null;

        if (tipoConta.equalsIgnoreCase("P")) {
            for (ContaPoupanca contaPoupanca : contaPoupancas) {
                if (contaPoupanca.getNome().equals(nomeCliente)) {

                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|  Digite o codigo da agencia: ");
                    int codAgencia = Integer.parseInt(reader.readLine());

                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| Digite o numero da conta: ");
                    int numeroDaConta = Integer.parseInt(reader.readLine());

                    for (Conta conta1 : contaPoupancas) {
                        if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                            conta = conta1;
                            break;
                        }
                    }
                }
            }
        } else if (tipoConta.equalsIgnoreCase("C")) {
            Conta conta0 = null;
            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Digite o codigo da agencia: ");
            int codAgencia = Integer.parseInt(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.println("| Digite o numero da conta: ");
            int numeroDaConta = Integer.parseInt(reader.readLine());

            for (Conta conta1 : contaCorrentes) {
                if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                    conta = conta1;
                    break;
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|               *  Conta não encontrada  *                |");
                    System.out.println("+---------------------------------------------------------+");
                    menuDeOpcoes();
                }
            }

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| DIgite o valor de saque: ");
            Double valorSaque = Double.parseDouble(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite a senha: ");
            int senha = scan.nextInt();

            if (conta.fazerSaque(valorSaque, documentoCliente, senha)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|                  *  Saque Confirmado  *                 |");
                System.out.println("+---------------------------------------------------------+");
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|          *  Saque nao realizado, tente novamente  *     |");
                System.out.println("+---------------------------------------------------------+");
                menuDeOpcoes();
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                 *  Documento inválido  *                |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }
    }

    public static void trasferir() throws IOException {

        try {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                 Realizar Transferencia                  |");
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o seu nome: ");
            String nomeCliente = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.println("|             Qual é o tipo da sua conta?                 |");
            System.out.println("+               <---------------------->                  +");
            System.out.println("|                   C - corrente                          |");
            System.out.println("|                   P - poupanca                          |");
            System.out.println("+---------------------------------------------------------+");
            String tipoConta = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o seu documento: ");
            String documentoCliente = reader.readLine();

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o seu cpf: ");
            long cpf = Long.parseLong(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digte o codigo da sua agencia: ");
            int codAgencia = Integer.parseInt(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o numero da sua conta: ");
            int numeroDaConta = Integer.parseInt(reader.readLine());

            if (tipoConta.equalsIgnoreCase("C")) {
                Conta conta = null;
                for (Conta conta1 : contaCorrentes) {
                    if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                        conta = conta1;
                        break;
                    }
                }
            } else if (tipoConta.equalsIgnoreCase("P")) {
                Conta conta = null;
                for (Conta conta1 : contaPoupancas) {
                    if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                        conta = conta1;
                        break;
                    }
                }
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|               *  Conta não encontrada  *                |");
                menuDeOpcoes();
            }

            System.out.println("+---------------------------------------------------------+");
            System.out.println("|            Qual é o tipo de conta do destinario?        |");
            System.out.println("+               <---------------------->                  +");
            System.out.println("|                   C - corrente                          |");
            System.out.println("|                   P - poupanca                          |");
            System.out.println("+---------------------------------------------------------+");
            String tipoContaDestinaria = reader.readLine();

            Conta conta;

            if (tipoContaDestinaria.equalsIgnoreCase("C")) {
                tipoContaDestinaria = "corrente";
                for (Conta conta1 : contaCorrentes) {
                    if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                        conta = conta1;
                        break;
                    }
                }
            } else if (tipoContaDestinaria.equalsIgnoreCase("P")) {
                tipoContaDestinaria = "poupanca";
                for (Conta conta1 : contaPoupancas) {
                    if (conta1.getCodigoDaAgencia() == codAgencia && conta1.getNumeroDaConta() == numeroDaConta) {
                        conta = conta1;
                        break;
                    }
                }
            }

//        System.out.println("+---------------------------------------------------------+");
//        System.out.print("| Digite o numero da conta destinataria: ");
//        int contaDestinaria = Integer.parseInt(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o cpf do destinario: ");
            long cpfDestinario = Long.parseLong(reader.readLine());

            List<Conta> listaContaOrigem = contas.getOrDefault(cpf, new ArrayList<>());
            List<Conta> listaContaDestino = contas.getOrDefault(cpfDestinario, new ArrayList<>());

            Conta destino = selecionarTipoConta(listaContaDestino, tipoContaDestinaria);
            Conta origem = selecionarTipoConta(listaContaOrigem, tipoConta);

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o valor da transferencia: ");
            Double valorSaque = Double.parseDouble(reader.readLine());

            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite a sua senha: ");
            int senha = Integer.parseInt(reader.readLine());

            assert destino != null;
            if (Transferencia.transferir(destino, origem, documentoCliente, valorSaque, senha)) {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|            *  Transferencia Confirmada! *               |");
                System.out.println("+---------------------------------------------------------+");
                menuDeOpcoes();
            } else {
                System.out.println("+---------------------------------------------------------+");
                System.out.println("|     * Transferencia nao realizada, tente novamente *    |");
                System.out.println("+---------------------------------------------------------+");
                menuDeOpcoes();
            }
            menuDeOpcoes();
        } catch (Exception ex){
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|     * Transferencia nao realizada, tente novamente *    |");
            System.out.println("+---------------------------------------------------------+");
            menuDeOpcoes();
        }
    }

    private static void verificarCliente() throws IOException {
        scan = new Scanner(System.in);
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|                    Verificar Cliente                    |");
        System.out.println("+---------------------------------------------------------+");
        System.out.println("|               Qual é o tipo de conta?                   |");
        System.out.println("+               <---------------------->                  +");
        System.out.println("|                   C - corrente                          |");
        System.out.println("|                   P - poupanca                          |");
        System.out.println("+---------------------------------------------------------+");

        String tipoConta = reader.readLine();

        if (tipoConta.equalsIgnoreCase("C")) {
            tipoConta = "Corrente";
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o nome do cliente: ");
            String nome = reader.readLine();
            for (Conta conta : contaCorrentes) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| - Nome: " + conta.getNome());
                    System.out.println("| - CPF: " + conta.getCpf());
                    System.out.println("| - Tipo de Conta: " + tipoConta);
                    System.out.println("| - Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| - Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| - Valor: " + conta.getValorDeDinheiroEmConta());
                    System.out.println("+---------------------------------------------------------+");
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                  *  Nome não encontrado  *              |");
                    System.out.println("+---------------------------------------------------------+");
                    menuDeOpcoes();
                }
            }
            menuDeOpcoes();
        } else if (tipoConta.equalsIgnoreCase("P")) {
            tipoConta = "Poupanca";
            System.out.println("+---------------------------------------------------------+");
            System.out.print("| Digite o nome do cliente: ");
            String nome = reader.readLine();
            for (Conta conta : contaPoupancas) {
                if (conta.getNome().equals(nome)) {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("| - Nome: " + conta.getNome());
                    System.out.println("| - CPF: " + conta.getCpf());
                    System.out.println("| - Tipo de Conta: " + tipoConta);
                    System.out.println("| - Agencia: " + conta.getCodigoDaAgencia());
                    System.out.println("| - Numero da Conta: " + conta.getNumeroDaConta());
                    System.out.println("| - Valor: " + conta.getValorDeDinheiroEmConta());
                    System.out.println("+---------------------------------------------------------+");
                } else {
                    System.out.println("+---------------------------------------------------------+");
                    System.out.println("|                  *  Nome não encontrado  *              |");
                    System.out.println("+---------------------------------------------------------+");
                }
            }
        } else {
            System.out.println("+---------------------------------------------------------+");
            System.out.println("|                   *  Conta Inválida  *                  |");
            System.out.println("+---------------------------------------------------------+");
        }
        menuDeOpcoes();
    }

    private static Long setCpf(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite o " + mensagem);
        return Long.parseLong(reader.readLine());
    }

    private static String setNome(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite o " + mensagem);
        return reader.readLine();
    }
    private static String setDocumento(String mensagem) throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite a " + mensagem);
        return reader.readLine();
    }
    private static int setSenha() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite a senha: ");
        return Integer.parseInt(reader.readLine());
    }
    private static Double setValorInicialDepositadoEmConta() throws IOException {
        System.out.println("+---------------------------------------------------------+");
        System.out.print("| Digite o valor incial da conta: ");
        return Double.parseDouble(reader.readLine());
    }
}
