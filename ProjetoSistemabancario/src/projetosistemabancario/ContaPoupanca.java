/*
 * Cria a conta poupança
 */
package projetosistemabancario;

import java.sql.SQLException;
import java.util.Scanner;

/**
 * @author Rodrigo Hay
 */
public class ContaPoupanca extends ContaBase {

    ConexaoBD contaBD = ConexaoBD.getInstancy();
    Scanner stdIn = new Scanner(System.in);
    private String tipoDeConta = "POUPANCA";
    private Double juros = 5.0;
    private int periodo = 0;
    private Double saldo = 0.0;
    private String resposta;
    int indexCliente;

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Cria conta poupança
    @Override
    public void CriarConta() throws SQLException {
        System.out.println("Insira o NÚMERO DA CONTA ORDEM do cliente:");
        resposta = stdIn.nextLine();
        String fraseQuery = "SELECT * FROM conta WHERE conta_id = '" + resposta + "'";
        if (contaBD.verificaExistenciaInfo(fraseQuery, "conta_id") == true) {
            contaBD.alteraBD("INSERT INTO conta(cliente_id, juros, periodo, saldo, tipo_de_conta) VALUES ('"
                    + contaBD.getIndex(fraseQuery, "cliente_id") + "','" + this.juros + "','" + this.periodo + "','" + this.saldo + "','"
                    + this.tipoDeConta + "');");
        } else {
            System.out.println("Cliente não encontrado.");
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------

    @Override
    public void ListarContaCliente() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void MostraSaldo() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void GravaMovimento() throws SQLException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
