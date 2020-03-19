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
 *
 * @author Rodrigo Hay
 */
public class ConexaoBD {

//	+--------------------------------------------------------------------------------------------------------------------------------------+
//  		Faz a conexão utilizando Singleton Pattern
//	+--------------------------------------------------------------------------------------------------------------------------------------+
    // Variaveis
    private Connection conexao;
    private String DB_URL = "jdbc:mysql://localhost/projetosistemabancario";
    private String USER = "root";
    private String PASS = "";
    private static ConexaoBD instancy;
    ArrayList<String> bd = new ArrayList<String>();
    static Statement stmt = null;

    private ConexaoBD() {
        try {
            this.conexao = DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = conexao.createStatement();

        } catch (Exception e) {
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());
        }
    }

    
// Insere/Altera dados no banco de dados
    public static void alteraBD(String alteraBancoDeDados) throws SQLException { // Altera os dados conforme a string de comando (SELECT * FROM country) por exemplo
        stmt.executeUpdate(alteraBancoDeDados);
    }
    
    
    //Retorna os dados e insere num ArrayList 
//    public void getDados() throws SQLException {
//
//        ResultSet rs = stmt.executeQuery("SELECT * FROM country");
//
//        while (rs.next()) {
//            
//            bd.add(rs.getString("country_id"));
//            bd.add(rs.getString("country"));
//        }
//    }
//
//    //Desponibiliza a arraylist com as informações
//    public ArrayList<String> getBD() {
//        return bd;
//    }

    
    
    
    
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
}