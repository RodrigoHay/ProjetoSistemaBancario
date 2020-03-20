/*
 * Classe cria cartões de débito
 */
package projetosistemabancario;

import java.sql.SQLException;

/**
 *
 * @author Rodrigo Hay
 */
public class CartaoDebito extends CartaoBase {

    ConexaoBD cartaoBD = ConexaoBD.getInstancy();
    
    private int indexCliente;
    private Double valor_plafon;

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    @Override
    public void CriarCartao() throws SQLException {
        cartaoBD.setColunaTabela("conta_id");
        cartaoBD.alteraBD("INSERT INTO cartoes(conta_id, credito_debito, valor_plafon) VALUES ('"
                + this.getIndexCliente() + "',\"C\",'" + this.getValor_plafon() + "');");
        setIndexCliente(cartaoBD.getIndex("SELECT * FROM conta"));
    }

    
    @Override
    public void ListarCartoes() {

    }

//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
//-----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
// Getters e Setters 
    public int getIndexCliente() {
        return indexCliente;
    }

    public void setIndexCliente(int indexCliente) {
        this.indexCliente = indexCliente;
    }

    public Double getValor_plafon() {
        return valor_plafon;
    }

    public void setValor_plafon(Double valor_plafon) {
        this.valor_plafon = valor_plafon;
    }

    
    
    
}
