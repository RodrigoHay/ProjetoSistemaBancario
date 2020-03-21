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
 *
 * @author Rodrigo Hay
 */
public class ProjetoSistemabancario {

    public static void main(String[] args) {
        String resposta = "";
        Cliente cliente = new Cliente();
        ContaOrdem contaOrdem = new ContaOrdem();
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Inserir opção 1-criar, 2-alterar, 3-Listar conta Ordem, 4-Saldo conta Ordem, 0-cancelar");
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
                default:
                    System.out.println("Opção incorreta.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
