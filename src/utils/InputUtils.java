package utils;

import account.ContaInvestimento;
import enums.StatusConta;
import enums.TipoConta;
import enums.TipoCriptoativo;
import register.Corretora;
import register.Endereco;
import register.Usuario;
import transaction.Transacao;

import java.math.BigDecimal;
import java.util.Objects;
import java.util.Scanner;

public class InputUtils {
    public static void transactionTaxInput(double taxaInput, Scanner scanner, Transacao transacao) {
        do {
            try {
                System.out.println("Qual a taxa da transação?");
                taxaInput = scanner.nextDouble();
                scanner.nextLine();
                transacao.setTaxaTransacao(taxaInput);
            } catch (Exception e) {
                System.out.println("Digite um valor válido :)");
                scanner.nextLine();
            }
        } while (taxaInput < 0);
    }

    public static void transactionHashInput(Scanner scanner, Transacao transacao) {
        String hashTransacaoInput;
        do {
            System.out.println("Qual hash da transação?");
            hashTransacaoInput = scanner.nextLine();
            transacao.setHashTransacao(hashTransacaoInput);
        } while (Objects.equals(hashTransacaoInput, ""));
    }

    public static void transactionDestinyAddressInput(Scanner scanner, Transacao transacao) {
        String enderecoDestinoInput;
        do {
            System.out.println("Qual o endereco de destino da transação?");
            enderecoDestinoInput = scanner.nextLine();
            transacao.setContaDestino(enderecoDestinoInput);
        } while (enderecoDestinoInput == "");
    }

    public static void transactionOriginAddressInput(Scanner scanner, Transacao transacao) {
        String enderecoOrigemInput;
        do {
            System.out.println("Qual o endereco de origem da transação?");
            enderecoOrigemInput = scanner.nextLine();
            transacao.setContaOrigem(enderecoOrigemInput);
        } while (Objects.equals(enderecoOrigemInput, ""));
    }

    public static void transactionDescriptionInput(Scanner scanner, Transacao transacao) {
        String descricaoTransacaoInput;
        do {
            System.out.println("Qual a descrição da transação?");
            descricaoTransacaoInput = scanner.nextLine();
            transacao.setDescricao(descricaoTransacaoInput);
        } while (Objects.equals(descricaoTransacaoInput, ""));
    }

    public static void transactionAmountInput(Scanner scanner, Transacao transacao) {
        BigDecimal montanteInput = null;
        do {
            try {
                System.out.println("Qual o montante da transação?");
                montanteInput = scanner.nextBigDecimal();
                scanner.nextLine();
                transacao.setMontante(montanteInput);
            } catch (Exception e) {
                System.out.println("Digite um valor válido :)");
                scanner.nextLine();
            }
        } while (montanteInput == null);
    }

    public static void accountStatusInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        int statusContaInput;
        do {
            System.out.println("Digite o status da sua conta: \n" +
                    "1 - Ativo\n" +
                    "2 - Inativo\n");
            statusContaInput = scanner.nextInt();

            if (statusContaInput == 1) {
                contaInvestimento.setStatusConta(StatusConta.ATIVA);
            } else if (statusContaInput == 2) {
                contaInvestimento.setStatusConta(StatusConta.INATIVA);
            } else {
                statusContaInput = -1;
            }
        } while (statusContaInput == -1);
    }

    public static void accountAddressInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        String endereco_carteira;
        do {
            System.out.println("Digite o endereço da carteira da sua conta: ");
            endereco_carteira = scanner.next();
            contaInvestimento.setEnderecoCarteira(endereco_carteira);
        } while (Objects.equals(endereco_carteira, ""));
    }

    public static void accountCryptoTypeInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        int tipoMoedaInput;
        do {
            System.out.println("Digite o tipo de moeda da sua conta: ");

            TipoCriptoativo[] tipoMoedas = TipoCriptoativo.values();

            for (int i = 1; i < tipoMoedas.length; i++) {
                TipoCriptoativo cripto = tipoMoedas[i];
                System.out.println("Index: " + i + ", Criptoativo: " + cripto.getNome() + ", Símbolo: " + cripto.getSimbolo());
            }
            tipoMoedaInput = scanner.nextInt();

            if (tipoMoedaInput >= 0 && tipoMoedaInput <= TipoCriptoativo.values().length) {
                contaInvestimento.setTipoCriptoativo(tipoMoedas[tipoMoedaInput]);
            } else {
                tipoMoedaInput = -1;
            }
        } while (tipoMoedaInput == -1);
    }

    public static void accountTypeInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        int tipoContaInput;
        do {
            System.out.println("Digite o tipo da sua conta:\n" +
                    "1 - Conta Corrente" +
                    "2 - Conta Poupança ");
            tipoContaInput = scanner.nextInt();

            TipoConta tipoConta;
            if (tipoContaInput == 1) {
                tipoConta = TipoConta.CONTA_CORRENTE;
            } else if (tipoContaInput == 2) {
                tipoConta = TipoConta.CONTA_POUCANCA;
            } else {
                tipoConta = null;
                tipoContaInput = -1;
            }
            if (tipoConta != null) {
                contaInvestimento.setTipoConta(tipoConta);
            }
        } while (tipoContaInput != 1);
    }

    public static void accountPasswordInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        String senhaContaInput;
        do {
            System.out.println("Digite a senha da sua conta: ");
            senhaContaInput = scanner.nextLine();
            contaInvestimento.setSenhaConta(senhaContaInput);
        } while (Objects.equals(senhaContaInput, ""));
    }

    public static void accountNumberInput(Scanner scanner, ContaInvestimento contaInvestimento) {
        String nrContaInput;
        do {
            System.out.println("Digite o número da sua conta: ");
            nrContaInput = scanner.nextLine();
            contaInvestimento.setNrConta(nrContaInput);
        } while (Objects.equals(nrContaInput, ""));
    }

    public static void brokerAddressInput(Scanner scanner, Corretora corretora) {
        String enderecoCarteiraCorretoraInput;
        do {
            System.out.println("Qual o endereço de carteira da sua corretora?");
            enderecoCarteiraCorretoraInput = scanner.nextLine();
            corretora.setEnderecoCarteiraCorretora(enderecoCarteiraCorretoraInput);
        } while (Objects.equals(enderecoCarteiraCorretoraInput, ""));
    }

    public static void brokerCriptoActivesInput(Scanner scanner, Corretora corretora) {
        int tiposCriptoativosInput;
        do {
            System.out.println("Qual são os tipos de criptoativos suportados pela sua corretora?");

            TipoCriptoativo[] criptoativos = TipoCriptoativo.values();
            for (int i = 0; i < criptoativos.length; i++) {
                TipoCriptoativo cripto = criptoativos[i];
                System.out.println("Index: " + i + ", Criptoativo: " + cripto.getNome() + ", Símbolo: " + cripto.getSimbolo());
            }

            System.out.println("Qual são os tipos de criptoativos suportados pela sua corretora?");
            tiposCriptoativosInput = scanner.nextInt();

            if (tiposCriptoativosInput >= 0 && tiposCriptoativosInput <= TipoCriptoativo.values().length) {
                corretora.setTiposCriptoativosSuportados(criptoativos[tiposCriptoativosInput]);
            } else {
                tiposCriptoativosInput = -1;
            }

        } while (tiposCriptoativosInput == -1);
    }

    public static void brokerEmailInput(Scanner scanner, Corretora corretora) {
        String emailCorretoraInput;
        do {
            System.out.println("Qual o email da sua corretora?");
            emailCorretoraInput = scanner.nextLine();
            corretora.setEmail(emailCorretoraInput);
        } while (Objects.equals(emailCorretoraInput, ""));
    }

    public static void brokerTelNumberInput(Scanner scanner, Corretora corretora) {
        String telefoneCorretoraInput;
        do {
            System.out.println("Qual o telefone da sua corretora?");
            telefoneCorretoraInput = scanner.nextLine();
            corretora.setTelefone(telefoneCorretoraInput);
        } while (Objects.equals(telefoneCorretoraInput, ""));
    }

    public static void brokerCNPJInput(Scanner scanner, Corretora corretora) {
        String cnpjCorretoraInput;
        do {
            System.out.println("Qual o CNPJ da sua corretora?");
            cnpjCorretoraInput = scanner.nextLine();
            corretora.setCnpj(cnpjCorretoraInput);
        } while (Objects.equals(cnpjCorretoraInput, ""));
    }

    public static void brokerNameInput(Scanner scanner, Corretora corretora) {
        String nomeCorretoraInput;
        do {
            System.out.println("Qual o nome da sua corretora?");
            nomeCorretoraInput = scanner.nextLine();
            corretora.setNomeCorretora(nomeCorretoraInput);
        } while (Objects.equals(nomeCorretoraInput, ""));
    }

    public static void addressCountryInput(Scanner scanner, Endereco endereco) {
        String paisInput;
        do {
            System.out.println("Qual o país que você reside?");
            paisInput = scanner.nextLine();
            endereco.setPais(paisInput);
        } while (Objects.equals(paisInput, ""));
    }

    public static void addressZipCodeInput(Scanner scanner, Endereco endereco) {
        String cepInput;
        do {
            System.out.println("Qual o CEP do seu endereço?");
            cepInput = scanner.nextLine();
            endereco.setCep(cepInput);
        } while (Objects.equals(cepInput, ""));
    }

    public static void addressStateCodeInput(Scanner scanner, Endereco endereco) {
        String cdEstadoInput;
        do {
            System.out.println("Qual o cd do estado do seu endereço?");
            cdEstadoInput = scanner.nextLine();
            endereco.setCdEstado(cdEstadoInput);
        } while (Objects.equals(cdEstadoInput, ""));
    }

    public static void addressCityInput(Scanner scanner, Endereco endereco) {
        String cidadeInput;
        do {
            System.out.println("Qual a cidade do seu endereço?");
            cidadeInput = scanner.nextLine();
            endereco.setCidade(cidadeInput);
        } while (Objects.equals(cidadeInput, ""));
    }

    public static void addressNeighborhoodInput(Scanner scanner, Endereco endereco) {
        String bairroInput;
        do {
            System.out.println("Qual o bairro do seu endereço?");
            bairroInput = scanner.nextLine();
            endereco.setBairro(bairroInput);
        } while (Objects.equals(bairroInput, ""));
    }

    public static void addressExtraInfoInput(Scanner scanner, Endereco endereco) {
        String complementoInput;
        do {
            System.out.println("Qual o complemento do seu endereço?");
            complementoInput = scanner.nextLine();
            endereco.setComplemento(complementoInput);
        } while (Objects.equals(complementoInput, ""));
    }

    public static void addressNumberInput(int numeroInput, Scanner scanner, Endereco endereco) {
        do {
            try {
                System.out.println("Qual o número do seu endereço?");
                numeroInput = scanner.nextInt();
                scanner.nextLine();
                endereco.setNumero(numeroInput);
            } catch (Exception e) {
                System.out.println("Digite um valor válido :)");
                scanner.nextLine();
            }
        } while (numeroInput < 0);
    }

    public static void addressStreetInput(Scanner scanner, Endereco endereco) {
        String ruaInput;
        do {
            System.out.println("Em qual rua você mora?");
            ruaInput = scanner.nextLine();
            endereco.setRua(ruaInput);
        } while (Objects.equals(ruaInput, ""));
    }

    public static void userTelNumberInput(Scanner scanner, Usuario usuario) {
        String telefoneUsuarioInput;
        do {
            System.out.println("Qual o seu número de telefone?");
            telefoneUsuarioInput = scanner.nextLine();
            usuario.setTelefone(telefoneUsuarioInput);
        } while (Objects.equals(telefoneUsuarioInput, ""));
    }

    public static void userCPFCNPJInput(Scanner scanner, Usuario usuario) {
        String cpfCnpjUsuarioInput;
        do {
            System.out.println("Qual o seu CPF ou CNPJ?");
            cpfCnpjUsuarioInput = scanner.nextLine();
            usuario.setCpfCnpj(cpfCnpjUsuarioInput);
        } while (Objects.equals(cpfCnpjUsuarioInput, ""));
    }

    public static void userPasswordInput(Scanner scanner, Usuario usuario) {
        String senhaUsuarioInput;
        do {
            System.out.println("Qual a sua senha?");
            senhaUsuarioInput = scanner.nextLine();
            usuario.setSenha(senhaUsuarioInput);
        } while (Objects.equals(senhaUsuarioInput, ""));
    }

    public static void userEmailInput(Scanner scanner, Usuario usuario) {
        String emailUsuarioInput;
        do {
            System.out.println("Qual o seu email?");
            emailUsuarioInput = scanner.nextLine();
            usuario.setEmail(emailUsuarioInput);
        } while (Objects.equals(emailUsuarioInput, ""));
    }

    public static void userNameInput(Scanner scanner, Usuario usuario) {
        String nomeCompletoUsuarioInput;
        do {
            System.out.println("Qual o seu nome completo?");
            nomeCompletoUsuarioInput = scanner.nextLine();
            usuario.setNomeCompleto(nomeCompletoUsuarioInput);
        } while (Objects.equals(nomeCompletoUsuarioInput, ""));
    }
}