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
    private Double valor = 0.0;

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Método que cria o cliente chama o método criar conta ordem - somente é utilizado esse método no momento da criação de um novo cliente
    @Override
    public void CriarConta() throws SQLException {
        contaBD.alteraBD("INSERT INTO conta(cliente_id, juros, periodo, saldo, tipo_de_conta) VALUES ('"
                + getIndexCliente() + "','" + this.getJuros() + "','" + this.getPeriodo() + "','" + this.getSaldo() + "','"
                + this.getTipoDeConta() + "');");
        //setIndexCliente(contaBD.getIndex("SELECT * FROM conta"));
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
        //contaBD.setColunaTabela("cliente_id");
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
        //contaBD.setColunaTabela("cartao_id");
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
        //contaBD.setColunaTabela("conta_id");
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
//Depósito na conta Ordem

    public void DepositoContaOrdem() throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO do cliente:");
        String respostaID = stdIn.nextLine();
        //contaBD.setColunaTabela("cartao_id");
        String fraseQuery = "SELECT cartao_id FROM cartoes WHERE cartao_id = '" + respostaID + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id") == true) { //Verifica se existe cliente
            System.out.println("Qual o valor?");
            resposta = stdIn.nextLine();
            valor = Double.parseDouble(resposta);
            //contaBD.setColunaTabela("saldo");
            contaBD.setInfo1("saldo");
            contaBD.setInfo2("conta_id");
            contaBD.getDados("SELECT conta.saldo, conta.conta_id FROM cartoes, conta WHERE conta.conta_id = cartoes.conta_id and cartao_id = '" + respostaID + "'"); //Retorna o saldo e o id da conta
            valor = valor + Double.parseDouble(contaBD.getBD().get(0));
            int indexConta = Integer.parseInt(contaBD.getBD().get(1));
            contaBD.alteraBD("UPDATE conta SET saldo = " + valor + " WHERE conta_id = " + indexConta); //Faz o update do novo valor na conta
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Levantamento conta Ordem

    public void LevantaContaOrdem() throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO do cliente:");
        String respostaID = stdIn.nextLine();
        //contaBD.setColunaTabela("cartao_id");
        String fraseQuery = "SELECT cartao_id FROM cartoes WHERE cartao_id = '" + respostaID + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id") == true) { //Verifica se existe cliente
            System.out.println("Qual o valor?");
            resposta = stdIn.nextLine();
            valor = Double.parseDouble(resposta);
            //contaBD.setColunaTabela("saldo");
            contaBD.setInfo1("saldo");
            contaBD.setInfo2("conta_id");
            contaBD.getDados("SELECT conta.saldo, conta.conta_id FROM cartoes, conta WHERE conta.conta_id = cartoes.conta_id and cartao_id = '" + respostaID + "'"); //Retorna o saldo e o id da conta
            if (Double.parseDouble(contaBD.getBD().get(0)) >= valor) {
                valor = Double.parseDouble(contaBD.getBD().get(0)) - valor;
                int indexConta = Integer.parseInt(contaBD.getBD().get(1));
                contaBD.alteraBD("UPDATE conta SET saldo = " + valor + " WHERE conta_id = " + indexConta); //Faz o update do novo valor na conta
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Transferência
    public void Transferencia() throws SQLException {
        System.out.println("Insira o CODIGO do CARTÃO da conta origem:");
        String respostaID1 = stdIn.nextLine();
        System.out.println("Insira o CODIGO da conta destino:");
        String respostaID2 = stdIn.nextLine();

        //contaBD.setColunaTabela("cartao_id");
        String fraseQuery = "SELECT cartao_id FROM cartoes WHERE cartao_id = '" + respostaID1 + "'";
        boolean cartao = contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id");
        //contaBD.setColunaTabela("cartao_id");
        fraseQuery = "SELECT conta_id FROM conta WHERE conta_id = '" + respostaID2 + "'";
        boolean conta = contaBD.verificaExistenciaInfo(fraseQuery, "cartao_id");
        if (cartao == true && conta == true) { //Verifica se existe cliente e conta
            System.out.println("Qual o valor?");
            resposta = stdIn.nextLine();
            valor = Double.parseDouble(resposta);
            contaBD.setInfo1("saldo");
            contaBD.setInfo2("conta_id");
            contaBD.getDados("SELECT conta.saldo, conta.conta_id FROM cartoes, conta WHERE conta.conta_id = cartoes.conta_id and cartao_id = '" + respostaID1 + "'"); //Retorna o saldo e o id da conta
            if (Double.parseDouble(contaBD.getBD().get(0)) >= valor) {
                valor = Double.parseDouble(contaBD.getBD().get(0)) - valor;
                int indexConta = Integer.parseInt(contaBD.getBD().get(1));
                System.out.println("valor soma: € " + (valor)); //APAGAR
                contaBD.alteraBD("UPDATE conta SET saldo = " + valor + " WHERE conta_id = " + indexConta); //Faz o update do novo valor na conta
                
                
                
                
            } else {
                System.out.println("Saldo insuficiente.");
            }
        } else {
            if (cartao) {
                System.out.println("Conta origem não encontrada.");
            }
            if (conta) {
                System.out.println("Conta destino não encontrada.");
            }
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
