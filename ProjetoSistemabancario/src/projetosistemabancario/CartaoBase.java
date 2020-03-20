/*
 *Classe base para criação dos cartões de débito e crédito
 */
package projetosistemabancario;

import java.sql.SQLException;

/**
 *
 * @author Rodrigo Hay
 */
public abstract class CartaoBase {

    public abstract void CriarCartao() throws SQLException;

    public abstract void ListarCartoes();
}
