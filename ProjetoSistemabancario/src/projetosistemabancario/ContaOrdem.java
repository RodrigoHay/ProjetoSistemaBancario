/*
 * Cria e movimenta a Conta Ordem
 */
package projetosistemabancario;

import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Scanner;

/**
 * @author Rodrigo Hay
 */
public class ContaOrdem extends ContaBase {

    Scanner stdIn = new Scanner(System.in);
    ConexaoBD contaBD = ConexaoBD.getInstancy();
    CartaoDebito cartaoDebito = new CartaoDebito();

    private int cliente_id;
    private int conta_id;
    private Double juros = 0.0;
    private int periodo = 0;
    private Double saldo = 0.0;
    private String tipoDeConta = "ORDEM";
    private int indexCliente;
    String resposta;

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Método que cria o cliente chama o método criar conta ordem - somente é utilizado esse método no momento da criação de um novo cliente
    @Override
    public void CriarConta() throws SQLException {
        contaBD.setColunaTabela("conta_id");
        contaBD.alteraBD("INSERT INTO conta(cliente_id, juros, periodo, saldo, tipo_de_conta) VALUES ('"
                + getIndexCliente() + "','" + this.getJuros() + "','" + this.getPeriodo() + "','" + this.getSaldo() + "','"
                + this.getTipoDeConta() + "');");
        setIndexCliente(contaBD.getIndex("SELECT * FROM conta"));
        cartaoDebito.setValor_plafon(0.0);
        cartaoDebito.setIndexCliente(this.getIndexCliente());
        cartaoDebito.CriarCartao();
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Faz a listagem de contas

    @Override
    public void ListarContaCliente() throws SQLException {
        System.out.println("Insira o NOME do cliente:");
        resposta = stdIn.nextLine();
        contaBD.setColunaTabela("cliente_id");
        if (contaBD.verificaExistenciaInfo("SELECT * FROM cliente WHERE nome = '" + resposta + "'") == true) {
            contaBD.setInfo1("conta_id");
            contaBD.setInfo2("tipo_de_conta");
            contaBD.getDados("SELECT cliente.cliente_id, conta.conta_id, conta.tipo_de_conta FROM conta, cliente WHERE cliente.cliente_id = conta.cliente_id AND nome = '" + resposta + "'");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }   //FALTA FAZER O VIEW
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Mostra saldo conta Ordem

    @Override
    public void MostraSaldo() throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO do cliente:");
        resposta = stdIn.nextLine();
        contaBD.setColunaTabela("cartao_id");
        if (contaBD.verificaExistenciaInfo("SELECT * FROM cartoes WHERE cartao_id = '" + resposta + "'") == true) {
            contaBD.setInfo1("saldo");
            contaBD.setInfo2("");
            contaBD.getDados("SELECT cartoes.cartao_id, conta.conta_id, conta.saldo FROM conta, cartoes WHERE cartoes.conta_id = conta.conta_id and cartoes.conta_id = " + resposta);
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }  //FALTA FAZER O VIEW
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void GravaMovimento() {

    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Getters e Setters 
    public int getCliente_id() {
        return cliente_id;
    }

    public void setCliente_id(int cliente_id) {
        this.cliente_id = cliente_id;
    }

    public int getConta_id() {
        return conta_id;
    }

    public void setConta_id(int conta_id) {
        this.conta_id = conta_id;
    }

    public Double getJuros() {
        return juros;
    }

    public void setJuros(Double juros) {
        this.juros = juros;
    }

    public int getPeriodo() {
        return periodo;
    }

    public void setPeriodo(int periodo) {
        this.periodo = periodo;
    }

    public Double getSaldo() {
        return saldo;
    }

    public void setSaldo(Double saldo) {
        this.saldo = saldo;
    }

    public String getTipoDeConta() {
        return tipoDeConta;
    }

    public void setTipoDeConta(String tipoDeConta) {
        this.tipoDeConta = tipoDeConta;
    }

    public int getIndexCliente() {
        return indexCliente;
    }

    public void setIndexCliente(int indexCliente) {
        this.indexCliente = indexCliente;
    }

}
