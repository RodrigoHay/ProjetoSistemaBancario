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
    private String numeroCC;
    private String moradaCliente;
    private int telefone;
    private String emailCliente;
    private String profissao;
    private int clienteAtivo = 1;
    private String resposta;
    private int respostaConvertidaNumero;
    private int step = 1;
    private boolean cadastroClienteCompleto = false;
    public String criarClienteComando;
    ConexaoBD clienteBD = ConexaoBD.getInstancy(); // Instância do objeto
    int indexCliente;

    public void CriaCliente() {
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Questionário para criação do cliente
        if (step == 1) //NOME
        {
            System.out.println("Digite o NOME DO CLIENTE ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setNomeCliente(resposta);
            }
        }
        if (step == 2) //CARTÃO CIDADÃO
        {
            System.out.println("Digite o CARTÃO CIDADÃO ou 0 para cancelar:");
            resposta = stdIn.nextLine();
            if (resposta.equals("0")) {
                step = 0;
            } else {
                setNumeroCC(resposta);
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
                cadastroClienteCompleto = true;
            }
        }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Verifica se cadastro foi concluído ou cancelado
        if (cadastroClienteCompleto == true) {
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
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Conecta ao banco de dados e cria cliente

    public void CriarClienteBD() throws SQLException {
        clienteBD.alteraBD("INSERT INTO cliente(nome, cartaoCidadao, telefone, email, profissao, cliente_ativo) VALUES ('"
                + this.getNomeCliente() + "','" + this.getNumeroCC() + "','" + this.getTelefone() + "','" + this.getEmailCliente() + "','"
                + this.getProfissao() + "','" + this.getClienteAtivo() + "');");

        clienteBD.getIndex("SELECT * FROM cliente"); //Pede o ultimo index
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Conecta ao banco de dados e altera dados do cliente

    public void alterarClienteBD() throws SQLException {
        String mudarDado = "";
        System.out.println("Digite o NOME DO CLIENTE:");
        resposta = stdIn.nextLine();
        if (clienteBD.verificaExistenciaInfo("SELECT * FROM cliente WHERE nome = '" + resposta + "'") == true) {
            indexCliente = clienteBD.getIndex("SELECT * FROM cliente");
            //Indica qual informação deseja alterar  
            System.out.println("Qual informação deseja alterar/Corrigir?");
            System.out.println("1 - Nome:");
            System.out.println("2 - Número do cartão cidadão:");
            System.out.println("3 - Morada:");
            System.out.println("4 - Telefone:");
            System.out.println("5 - E-mail:");
            System.out.println("6 - Profissão:");
            resposta = stdIn.nextLine();
            switch (resposta) {
                case "1":
                    System.out.println("Indique o novo Nome:");
                    resposta = stdIn.nextLine();
                    mudarDado = "nome";
                    break;
                case "2":
                    System.out.println("Indique o novo Número do Cartão Cidadão:");
                    resposta = stdIn.nextLine();
                    mudarDado = "cartaoCidadao";
                    break;
                case "3":
                    System.out.println("Indique a nova Morada:");
                    resposta = stdIn.nextLine();
                    mudarDado = "morada";
                    break;
                case "4":
                    System.out.println("Indique o novo Telefone:");
                    resposta = stdIn.nextLine();
                    mudarDado = "telefone";
                    break;
                case "5":
                    System.out.println("Indique o novo E-mail:");
                    resposta = stdIn.nextLine();
                    mudarDado = "email";
                    break;
                case "6":
                    System.out.println("Indique a nova Profissão:");
                    resposta = stdIn.nextLine();
                    mudarDado = "profissao";
                    break;
                default:
                    System.out.println("opção inválida.");
            }

            clienteBD.alteraBD("UPDATE cliente SET " + mudarDado + " = '" + resposta + "' WHERE cliente_id = " + indexCliente);
            indexCliente = 0;
            System.out.println("Dado alterado com sucesso.");

        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Getters e Setters 

    public String getNomeCliente() {
        return nomeCliente.toUpperCase();
    }

    public void setNomeCliente(String nomeCliente) { //Verifica se cliente existe / se ok faz o set do nome
        try {
            if (clienteBD.verificaExistenciaInfo("SELECT * FROM cliente WHERE nome = '" + nomeCliente + "'") == false) {
                this.nomeCliente = nomeCliente;
                step = 2;
            } else {
                System.out.println("Cliente já existe no Sistema. Não é possivel completar o cadastro.");
                step = 0;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String getNumeroCC() {
        return numeroCC.toUpperCase();
    }

    public void setNumeroCC(String numeroCC) {
        this.numeroCC = numeroCC;
    }

    public String getMoradaCliente() {
        return moradaCliente.toUpperCase();
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
        return profissao.toUpperCase();
    }

    public void setProfissao(String profissao) {
        this.profissao = profissao;
    }

    public int getClienteAtivo() {
        return clienteAtivo;
    }

}
