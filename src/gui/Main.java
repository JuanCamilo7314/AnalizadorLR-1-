package gui;

import control.ControlException;
import control.ControlSistema;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;

public class Main {

    public static void main(String[] args) {
        ControlSistema controlSistema = new ControlSistema();
        File archivo = controlSistema.buscarArchivo();
        try {
            JSONArray gramatica = controlSistema.mostrarGramatica(archivo);
            gramatica=controlSistema.leerGramatica(gramatica);
            gramatica=controlSistema.generarx(gramatica);
            System.out.println(gramatica);
        } catch (ControlException ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage());
        }
    }
    
}
