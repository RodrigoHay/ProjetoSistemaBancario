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
    int contadorSwitch = 1;
    boolean cadastroClinteCompleto = false;

//    public Cliente(String nomeCliente, int numeroCC, String moradaCliente, int telefone, String emailCliente, String profissao) {
////        this.nomeCliente = nomeCliente;
////        this.numeroCC = numeroCC;
////        this.
//    }
    public void CriaCliente() {
        //Questionário para criação do cliente

        do {
            switch (contadorSwitch) {
                case 1: //NOME

                    System.out.println("Digite o NOME DO CLIENTE ou 0 para cancelar:");
                    respostaTexto = stdIn.nextLine();
                    if (respostaTexto.equals("0")) {
                        break;
                    } else {
                        setNomeCliente(respostaTexto);
                    }
                case 2: //CARTÃO CIDADÃO
                    System.out.println("Digite o NÚMERO DO CARTÃO CIDADÃO ou 0 para cancelar:");
                    respostaNumero = stdIn.nextInt();
                    if (respostaNumero == 0) {
                        break;
                    } else {
                        setNumeroCC(respostaNumero);
                    }
                case 3: //TELEFONE
                    System.out.println("Digite o NÚMERO TELEFÔNICO ou 0 para cancelar:");
                    respostaNumero = stdIn.nextInt();
                    if (respostaNumero == 0) {
                        break;
                    } else {
                        setTelefone(respostaNumero);
                    }
                case 4://MORADA
                    System.out.println("Digite a MORADA ou 0 para cancelar:");
                    respostaTexto = stdIn.nextLine();
                    if (respostaTexto.equals("0")) {
                        break;
                    } else {
                        setMoradaCliente(respostaTexto);
                    }
                case 5: //EMAIL
                    System.out.println("Digite a E-MAIL ou 0 para cancelar:");
                    respostaTexto = stdIn.nextLine();
                    if (respostaTexto.equals("0")) {
                        break;
                    } else {
                        setEmailCliente(respostaTexto);
                    }
                case 6: //PROFISSÃO
                    System.out.println("Digite a PROFISSÃO ou 0 para cancelar:");
                    respostaTexto = stdIn.nextLine();
                    if (respostaTexto.equals("0")) {
                        break;
                    } else {
                        setEmailCliente(respostaTexto);
                        cadastroClinteCompleto = true;
                    }
                default:
                    System.out.println("Operação cancelada.");
            }
        } while (!cadastroClinteCompleto);

    }

    // Getters e Setters #####################################################################################################################################
    public String getNomeCliente() {
        return nomeCliente;
    }

    public void setNomeCliente(String nomeCliente) {
        this.nomeCliente = nomeCliente;
    }

    public int getNumeroCC() {
        return numeroCC;
    }

    public void setNumeroCC(int numeroCC) {
        this.numeroCC = numeroCC;
    }

    public String getMoradaCliente() {
        return moradaCliente;
    }

    public void setMoradaCliente(String moradaCliente) {
        this.moradaCliente = moradaCliente;
    }

    public int getTelefone() {
        return telefone;
    }

    public void setTelefone(int telefone) {
        this.telefone = telefone;
    }

    public String getEmailCliente() {
        return emailCliente;
    }

    public void setEmailCliente(String emailCliente) {
        this.emailCliente = emailCliente;
    }

    public String getProfissao() {
        return profissao;
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

}
