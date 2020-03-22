/* 
  +----------------------------------------------------------------------------------------+
  |Sistema de Gestão Bancária                                                              |
  |##########################                                                              |
  |                                                                                        |
  |Versão simplificada do projeto.                                                         |
  |O Banco possui apenas 1 agência (Que é o proprio banco).                                |
  |Está implementado a Agência, Clientes, Contas, Cartões, Periodo e a Interface.          |
  |Os dados estarão salvos em um banco de dados SQL.                                       |
  +----------------------------------------------------------------------------------------+
 */
package projetosistemabancario;

import java.util.Scanner;

/**
 * @author Rodrigo Hay
 */
public class ProjetoSistemabancario {

    public static void main(String[] args) {
        String resposta = "";
        Cliente cliente = new Cliente();
        ContaOrdem contaOrdem = new ContaOrdem();
        ContaPoupanca contaPoupanca = new ContaPoupanca();
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Inserir opção 1-criar, 2-alterar, 3-Listar conta Ordem, 4-Saldo conta Ordem, 5-Deposito conta Ordem");
        System.out.println("6-Levantamento, 7-Transferencia, 8-Criar conta Poupança - 0-cancelar");
        resposta = stdIn.nextLine();
        try {
            switch (resposta) {
                case "0":
                    System.out.println("Cancelado");
                    break;
                case "1":
                    System.out.println("Cadastro do novo cliente.");
                    cliente.CriaCliente();
                    break;
                case "2":
                    System.out.println("Alterar cliente.");
                    cliente.alterarClienteBD();
                    break;
                case "3":
                    System.out.println("Listar conta Ordem.");
                    contaOrdem.ListarContaCliente();
                    break;
                case "4":
                    System.out.println("Saldo conta Ordem.");
                    contaOrdem.MostraSaldo();
                    break;
                case "5":
                    System.out.println("Depósito na conta Ordem.");
                    //contaOrdem.DepositoContaOrdem();
                    contaOrdem.Atividade("deposito");
                    break;
                case "6":
                    System.out.println("Levantamento na conta Ordem.");
                    contaOrdem.Atividade("levantamento");
                    break;
                case "7":
                    System.out.println("Transferência da conta Ordem.");
                    contaOrdem.Atividade("transferencia");
                    break;
                case "8":
                    System.out.println("Cria conta POUPANÇA.");
                    contaPoupanca.CriarConta();
                    break;
                default:
                    System.out.println("Opção incorreta.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
