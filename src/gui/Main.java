package gui;

import control.ControlException;
import control.ControlSistema;
import java.io.File;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class Main {

    public static void main(String[] args) {
        ControlSistema controlSistema = new ControlSistema();
        File archivo = controlSistema.buscarArchivo();
        try {
            JSONArray gramatica = controlSistema.mostrarGramatica(archivo);
            gramatica = controlSistema.leerGramatica(gramatica);
            gramatica = controlSistema.generarx(gramatica);
            System.out.println(gramatica);
            List<Integer> estados = new ArrayList<Integer>();

            for (int i = 0; i < gramatica.size(); i++) {
                JSONObject objetoGramatica = (JSONObject) gramatica.get(i);
                estados.add((int)objetoGramatica.get("estado"));
            }
            Set<Integer> hashSet = new HashSet<Integer>(estados);
            estados.clear();
            estados.addAll(hashSet);

            for (int i = 0; i < estados.size(); i++) {
                System.out.println("--------------------");
                System.out.println(" Estado : I - " + estados.get(i));
                System.out.println();
                for (int j = 0; j < gramatica.size(); j++) {
                    JSONObject objetoGramatica1 = (JSONObject) gramatica.get(j);
                    if (estados.get(i)==((int)objetoGramatica1.get("estado"))) {
                        if (objetoGramatica1.get("transicion").equals("")) {
                            System.out.println(objetoGramatica1.get("noTerminal") + "  -->  "
                                    + objetoGramatica1.get("producciones") + " , " +
                                    objetoGramatica1.get("primeros")+"  "+
                                    controlSistema.buscarAceptar(objetoGramatica1.get("noTerminal")+"", objetoGramatica1.get("producciones")+""));
                        } else {
                            System.out.println(objetoGramatica1.get("noTerminal") + "  -->  "
                                    + objetoGramatica1.get("producciones") + " , " + objetoGramatica1.get("primeros")
                                    + " Transiciona con: " + objetoGramatica1.get("transicion"));
                        }
                    }

                }
            }
        } catch (ControlException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }

}
