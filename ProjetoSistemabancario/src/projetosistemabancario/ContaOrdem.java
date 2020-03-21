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
    View view = new View();
    Movimentos movimentos = new Movimentos();

    private int cliente_id;
    private int conta_id;
    private Double juros = 0.0;
    private int periodo = 0;
    private Double saldo = 0.0;
    private String tipoDeConta = "ORDEM";
    private int indexCliente;
    String resposta;
    private String respostaID;
    private String respostaDeposito;
    private String respostaLevantamento;
    private String respostaTransferencia;
    private String valorLevantamento;
    private String valorDeposito;
    private String deposito_levantamento;

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Método que cria o cliente chama o método criar conta ordem - somente é utilizado esse método no momento da criação de um novo cliente
    @Override
    public void CriarConta() throws SQLException {
        contaBD.alteraBD("INSERT INTO conta(cliente_id, juros, periodo, saldo, tipo_de_conta) VALUES ('"
                + getIndexCliente() + "','" + this.getJuros() + "','" + this.getPeriodo() + "','" + this.getSaldo() + "','"
                + this.getTipoDeConta() + "');");
        setIndexCliente(contaBD.getIndex("SELECT * FROM conta", "conta_id"));
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
        String fraseQuery = "SELECT cliente_id FROM cliente WHERE nome = '" + resposta + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "cliente_id") == true) {
            contaBD.setInfo1("conta_id");
            contaBD.setInfo2("tipo_de_conta");
            contaBD.getDados("SELECT cliente.cliente_id, conta.conta_id, conta.tipo_de_conta FROM conta, cliente WHERE cliente.cliente_id = conta.cliente_id AND nome = '" + resposta + "'");
        } else {
            System.out.println("Cliente não encontrado.");
        }
        view.Imprimir();
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Mostra saldo conta Ordem

    @Override
    public void MostraSaldo() throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO do cliente:");
        resposta = stdIn.nextLine();
        String fraseQuery = "SELECT cartao_id FROM cartoes WHERE cartao_id = '" + resposta + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id") == true) {
            contaBD.setInfo1("saldo");
            contaBD.setInfo2("");
            contaBD.getDados("SELECT cartoes.cartao_id, conta.conta_id, conta.saldo FROM conta, cartoes WHERE cartoes.conta_id = conta.conta_id and cartoes.cartao_id = " + resposta);
        } else {
            System.out.println("Cliente não encontrado.");
        }
        view.Imprimir();
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void GravaMovimento() throws SQLException {
        contaBD.alteraBD("INSERT INTO conta(cliente_id, juros, periodo, saldo, tipo_de_conta) VALUES ('"
                + getIndexCliente() + "','" + this.getJuros() + "','" + this.getPeriodo() + "','" + this.getSaldo() + "','"
                + this.getTipoDeConta() + "');");
        setIndexCliente(contaBD.getIndex("SELECT * FROM conta", "conta_id"));
        cartaoDebito.setValor_plafon(0.0);
        cartaoDebito.setIndexCliente(this.getIndexCliente());
        movimentos.GravaMovimento();
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Define a movimentação (deposito/levantamento/transferencia)
    public void Atividade(String atividade) throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO:");
        respostaID = stdIn.nextLine();
        String fraseQuery = "SELECT cartao_id FROM cartoes WHERE cartao_id = '" + respostaID + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id") == true) { //Verifica se existe cartão 
            if (atividade.equals("deposito")) {
                respostaDeposito = respostaID;
                deposito_levantamento = "cartao_id";
                System.out.println("Valor do deposito na Conta Ordem:");
                this.valorDeposito = stdIn.nextLine();
                DepositoContaOrdem();
            }
            if (atividade.equals("levantamento")) {
                respostaLevantamento = respostaID;
                deposito_levantamento = "cartao_id";
                System.out.println("Valor do levantamento da Conta Ordem:");
                this.valorLevantamento = stdIn.nextLine();
                LevantaContaOrdem();
            }
            if (atividade.equals("transferencia")) {
                System.out.println("Insira o NÚMERO DA CONTA onde será CREDITADO:");
                respostaTransferencia = stdIn.nextLine();
                fraseQuery = "SELECT conta_id FROM conta WHERE conta_id = '" + respostaTransferencia + "'";
                if (contaBD.verificaExistenciaInfo(fraseQuery, "conta_id") == true) {
                    deposito_levantamento = "conta.conta_id";
                    respostaDeposito = respostaTransferencia;
                    respostaLevantamento = respostaID;
                    System.out.println("Valor da transferencia:");
                    this.valorDeposito = stdIn.nextLine();
                    this.valorLevantamento = this.valorDeposito;
                    LevantaContaOrdem();
                    DepositoContaOrdem();
                } else {
                    System.out.println("Conta não encontrada.");
                }
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Depósito na conta Ordem

    public void DepositoContaOrdem() throws SQLException {

        Double valorD = Double.parseDouble(valorDeposito);
        contaBD.setInfo1("saldo");
        contaBD.setInfo2("conta_id");
        contaBD.getDados("SELECT conta.saldo, conta.conta_id FROM cartoes, conta WHERE conta.conta_id = cartoes.conta_id and " + deposito_levantamento + " = '" + respostaDeposito + "'"); //Retorna o saldo e o id da conta
        valorD = valorD + Double.parseDouble(contaBD.getBD().get(0));
        int indexConta = Integer.parseInt(contaBD.getBD().get(1));
        contaBD.alteraBD("UPDATE conta SET saldo = " + valorD + " WHERE conta_id = " + indexConta); //Faz o update do novo valor na conta

    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Levantamento conta Ordem

    public void LevantaContaOrdem() throws SQLException {

        Double valorL = Double.parseDouble(valorLevantamento);
        contaBD.setInfo1("saldo");
        contaBD.setInfo2("conta_id");
        contaBD.getDados("SELECT conta.saldo, conta.conta_id FROM cartoes, conta WHERE conta.conta_id = cartoes.conta_id and cartao_id = '" + respostaLevantamento + "'"); //Retorna o saldo e o id da conta
        if (Double.parseDouble(contaBD.getBD().get(0)) >= valorL) {
            valorL = Double.parseDouble(contaBD.getBD().get(0)) - valorL;
            int indexConta = Integer.parseInt(contaBD.getBD().get(1));
            contaBD.alteraBD("UPDATE conta SET saldo = " + valorL + " WHERE conta_id = " + indexConta); //Faz o update do novo valor na conta
        } else {
            System.out.println("Saldo insuficiente.");
        }
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
