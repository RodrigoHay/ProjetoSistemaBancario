/*
 Faz a conexão com o banco de dados e retorna ou grava as informações / Utiliza Singleton
 */
package projetosistemabancario;

import java.sql.Statement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 * @author Rodrigo Hay
 */
public class ConexaoBD {

//Conexão ao Banco de Dados
    // Variaveis
    private Connection conexao;
    private String DB_URL = "jdbc:mysql://localhost/projetosistemabancario";
    private String USER = "root";
    private String PASS = "";
    private static ConexaoBD instancy;
    ArrayList<String> bd = new ArrayList<String>();
    static Statement stmt = null;
    private String colunaTabela;
    private String info1;
    private String info2;

    private ConexaoBD() {
        try {
            this.conexao = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conexao.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Insere/Altera dados no banco de dados

    public void alteraBD(String alteraBancoDeDados) throws SQLException {
        stmt.executeUpdate(alteraBancoDeDados);
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Retorna o index da ultima gravação

    public int getIndex(String fraseQuery) throws SQLException {

        ResultSet rs = stmt.executeQuery(fraseQuery);
        int index = 0;
        while (rs.next()) {
            index = Integer.parseInt(rs.getString(colunaTabela));
        }
        return index;
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    // Verifica se um cliente existe

    public boolean verificaExistenciaInfo(String fraseQuery) throws SQLException {
        boolean existeCliente = false;
        int verificaNome = 0;
        ResultSet rs = stmt.executeQuery(fraseQuery);
        while (rs.next()) {
            verificaNome = Integer.parseInt(rs.getString(colunaTabela));
        }
        if (verificaNome > 0) {
            existeCliente = true;
        }
        return existeCliente;
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    //Retorna os dados do banco de dados

    public void getDados(String fraseQuery) throws SQLException {

        ResultSet rs = stmt.executeQuery(fraseQuery);
        while (rs.next()) {
            if (!this.info1.equals("")) {
                bd.add(rs.getString(info1));
            }
            if (!this.info2.equals("")) {
                bd.add(rs.getString(info2));
            }
        }
    }
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Disponibiliza as informações do array  

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//Desponibiliza a arraylist com as informações
    public ArrayList<String> getBD() {
        return bd;
    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Getters e Setters #####################################################################################################################################
    public static ConexaoBD getInstancy() {
        if (instancy == null) {
            instancy = new ConexaoBD();
        }
        return instancy;
    }

    public Connection getConexao() {
        return this.conexao;

    }

    public String getColunaTabela() {
        return colunaTabela;
    }

    public void setColunaTabela(String colunaTabela) {
        this.colunaTabela = colunaTabela;
    }

    public String getInfo1() {
        return info1;
    }

    public void setInfo1(String info1) {
        this.info1 = info1;
    }

    public String getInfo2() {
        return info2;
    }

    public void setInfo2(String info2) {
        this.info2 = info2;
    }

}
