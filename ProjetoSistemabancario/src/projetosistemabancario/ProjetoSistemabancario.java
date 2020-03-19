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

/**
 *
 * @author Rodrigo Hay
 */
public class ProjetoSistemabancario {

    public static void main(String[] args) {
        Cliente cliente = new Cliente();
        cliente.CriaCliente();
    }
    
}
