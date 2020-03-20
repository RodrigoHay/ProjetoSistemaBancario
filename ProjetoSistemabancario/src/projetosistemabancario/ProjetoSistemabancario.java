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
        Scanner stdIn = new Scanner(System.in);
        System.out.println("Inserir opção 1-criar, 2-alterar, 0-cancelar");
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
                default:
                    System.out.println("Opção incorreta.");
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

}
