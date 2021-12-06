package control;

import entidades.Gramatica;
import entidades.ArreglarGramatica;
import java.io.File;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * clase encargada de ejecutar todos las acciones del programa.
 *
 * @author Yenny, Camilo, Emanuel.
 * @version 1.0
 */
public class ControlSistema {

    private Gramatica gramatica;
    private ArreglarGramatica gramaticaArreglada;
   

    public ControlSistema() {
        this.gramatica = new Gramatica();
        this.gramaticaArreglada = new ArreglarGramatica();
    }

    /**
     * Metodo encargado de obtener la gramatica de un archivo.
     *
     * @param archivo hace referencia al archivo seleccionado.
     * @return JSONArray de la gramatica seleccionada.
     * @throws ControlException
     */
    public JSONArray mostrarGramatica(File archivo) throws ControlException {
        return this.gramatica.cargarArchivo(archivo);
    }

    /**
     * Metodo encargado de transformar la gramatica cuando esta tiene NO Terminales
     * con mas de 2 letras (AB -> adAB) por (X->abX)
     *
     * @param archivo hace referencia al archivo seleccionado.
     * @return JSONArray de la gramatica seleccionada y transformada.
     * @throws ControlException
     */
    public JSONArray mostrarGramaticaLista(File archivo) throws ControlException {
        gramaticaArreglada.eliminarNoTerminalMayorAUno(this.gramatica.cargarArchivo(archivo));
        return gramaticaArreglada.getGramaticaArreglada();
    }
}
