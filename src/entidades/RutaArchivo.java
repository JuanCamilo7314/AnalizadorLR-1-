package entidades;

import java.io.File;
import javax.swing.JFileChooser;

/**
 *
 * @author Juan Camilo Uni Lara
 */
public class RutaArchivo {

    public File buscarArchivo() {
        JFileChooser ventanacargar = new JFileChooser();
        ventanacargar.showOpenDialog(ventanacargar);
        File archivo = ventanacargar.getSelectedFile();
        return archivo;
    }
}
