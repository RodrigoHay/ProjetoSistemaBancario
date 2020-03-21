/*
 * Apresenta na tela os dados requeridos
 */
package projetosistemabancario;

import java.util.ArrayList;

/**
 * @author Rodrigo Hay
 */
public class View {

    ConexaoBD viewBD = ConexaoBD.getInstancy();
    ArrayList mostraBD = new ArrayList();
    String imprimir;

    public void Imprimir() {
        mostraBD.addAll(viewBD.getBD());

        for (int i = 0; i < mostraBD.size(); i++) {
            imprimir = (String) mostraBD.get(i);
            System.out.println("Dados do array " + imprimir);
        }

    }

}
