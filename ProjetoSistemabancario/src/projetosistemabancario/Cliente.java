/*
 Verifica se cliente existe
 Cria novo cliente
 */
package projetosistemabancario;

import java.util.Scanner;

/**
 * @author Rodrigo Hay
 */
public class Cliente {
        Scanner stdIn = new Scanner(System.in);

    // Dados pessoais dos clientes
    String nomeCliente;
    int numeroCC;
    String moradaCliente;
    int telefone;
    String emailCliente;
    String profissao;
    String respostaTexto;
    int respostaNumero;

//    public Cliente(String nomeCliente, int numeroCC, String moradaCliente, int telefone, String emailCliente, String profissao) {
////        this.nomeCliente = nomeCliente;
////        this.numeroCC = numeroCC;
////        this.
//    }

    public void CriaCliente() {
        //Questionário para criação do cliente
        System.out.println("Nome do cliente:");
        respostaTexto = stdIn.nextLine();
        System.out.println(respostaTexto);
    }
}
