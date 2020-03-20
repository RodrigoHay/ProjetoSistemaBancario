/*
 *Classe base para criação das contas a ordem, poupança e a prazo
 */
package projetosistemabancario;

import java.sql.SQLException;

/**
 * @author Rodrigo Hay
 */
public abstract class ContaBase {

    public abstract void CriarConta()throws SQLException;

    public abstract void ListarContaCliente();

    public abstract void MostraSaldo();

    public abstract void GravaMovimento();

}
