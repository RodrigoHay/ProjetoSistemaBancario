/*
 Verifica se cliente existe
 Cria novo cliente
 */
package projetosistemabancario;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Rodrigo Hay
 */
public class Cliente {

    Scanner stdIn = new Scanner(System.in);

    // Dados pessoais dos clientes
    private String nomeCliente;
    private int numeroCC;
    private String moradaCliente;
    private int telefone;
    private String emailCliente;
    private String profissao;
    private String resposta;
    private int respostaConvertidaNumero;
    private int step = 1;
    private boolean cadastroClinteCompleto = false;

    public void CriaCliente() {

        //Questionário para criação do cliente
        if (step == 1) //NOME
        {
            System.out.println("Digite o NOME DO CLIENTE ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setNomeCliente(resposta);
                step = 2;
            }
        }
        if (step == 2) //CARTÃO CIDADÃO
        {
            System.out.println("Digite o CARTÃO CIDADÃO ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                respostaConvertidaNumero = Integer.parseInt(resposta);
                setNumeroCC(respostaConvertidaNumero);
                step = 3;
            }
        }
        if (step == 3) //TELEFONE
        {
            System.out.println("Digite o NÚMERO TELEFÔNICO ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                respostaConvertidaNumero = Integer.parseInt(resposta);
                setTelefone(respostaConvertidaNumero);
                step = 4;
            }
        }
        if (step == 4) //MORADA
        {
            System.out.println("Digite a MORADA ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setMoradaCliente(resposta);
                step = 5;
            }
        }
        if (step == 5) //E-MAIL
        {
            System.out.println("Digite o E-MAIL ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setEmailCliente(resposta);
                step = 6;
            }
        }
        if (step == 6) //PROFISSÃO
        {
            System.out.println("Digite o PROFISSÃO ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setProfissao(resposta);
                cadastroClinteCompleto = true;
            }
        }

        //Verifica se cadastro foi concluído ou cancelado
        if (cadastroClinteCompleto == true) {
            System.out.println("Dados pessoais completos");
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    //Faz a conexão com o banco de dados e envio das informações
   	public static void ClienteTeste() throws SQLException {
		ConnectionSingleton testeConexao = ConnectionSingleton.getInstancy();
		
		testeConexao.alteraBD("DELETE FROM country WHERE country_id= 5");
		
		System.out.println("Imprimindo");
		testeConexao.getDados();
		System.out.println("Foram impressos");
		
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
