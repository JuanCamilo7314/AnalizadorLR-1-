package entidades;

import control.ControlException;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.swing.JOptionPane;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Clase que se encarga de cargar el json y leerlo
 *
 * @author Yenny, Camilo, Emanuel.
 * @version 1.0
 */
public class Gramatica {

    private JSONArray gramatica;

    public Gramatica() {
        this.gramatica = new JSONArray();
    }

    /**
     * Método que se encarga de cargar el archivo seleccionado y tranformalo a
     * un JSONArray
     *
     * @param archivo archivo seleccionado.
     * @return gramatica cargada en JSONArray
     * @throws ControlException
     */
    public JSONArray cargarArchivo(File archivo) throws ControlException {

        if (validarArchivo(archivo)) {
            llenarArray(archivo);
            return this.getGramatica();
        } else {
            throw new ControlException("No se ha seleccionado un archivo");
        }
    }

    /**
     * Metodo para validar si el archivo seleccionado existe
     *
     * @param archivo archivo seleccionado
     * @return boolean resultado de la validacion.
     */
    private boolean validarArchivo(File archivo) {
        if (archivo != null) {
            return true;
        }
        return false;
    }

    /**
     * Metodo encargado de llenar el JSONArray con la gramatica.
     *
     * @param archivo archivo seleccionado
     */
    private void llenarArray(File archivo) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(new FileReader(archivo.getPath()));
            JSONObject jsonObject = (JSONObject) obj;
            

            JSONArray array = (JSONArray) jsonObject.get("gramatica");
            

            for (int i = 0; i < array.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) array.get(i);
                
                this.setGramatica(array);

            }
        } catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null, "El archivo no existe");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "El archivo tiene valores no permitidos");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    public JSONArray getGramatica() {
        return gramatica;
    }

    public void setGramatica(JSONArray gramatica) {
        this.gramatica = gramatica;
    }

}
