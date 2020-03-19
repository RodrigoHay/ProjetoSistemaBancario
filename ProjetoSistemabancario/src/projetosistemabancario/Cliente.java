/*
 Verifica se cliente existe
 Cria novo cliente
 */
package projetosistemabancario;

import java.sql.SQLException;
import java.time.Clock;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

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
    public String criarClienteComando;

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
            try {
                CriarClienteBD();
            } catch (SQLException ex) {
                System.out.println("Falha ao tentar gravar no banco de dados");
                System.out.println(ex.getMessage());
            }
        } else {
            System.out.println("Cadastro cancelado.");
        }
    }

    //Faz a conexão com o banco de dados e envio das informações
    public void CriarClienteBD() throws SQLException {
        ConexaoBD clienteBD = ConexaoBD.getInstancy();
        clienteBD.alteraBD("INSERT INTO cliente(nome, cartaoCidadao, telefone, email, profissão) VALUES ("
                + this.getNomeCliente() + "," + this.getNumeroCC() + "," + this.getTelefone() + "," + this.getEmailCliente() + "," + this.getProfissao() + ");");

//        testeConexao.getDados();
    }

    //INSERT INTO country(country_id, country) VALUES(5, 'Brasil')";
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
