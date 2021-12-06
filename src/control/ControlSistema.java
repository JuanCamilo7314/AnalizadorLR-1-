package control;

import entidades.Gramatica;
import entidades.ArreglarGramatica;
import entidades.ExtenderGramatica;
import entidades.GenerarAutomata;
import entidades.RutaArchivo;
import java.io.File;
import org.json.simple.JSONArray;


/**
 * clase encargada de ejecutar todos las acciones del programa.
 *
 * @author Yenny, Camilo, Emanuel.
 * @version 1.0
 */
public class ControlSistema {

    private Gramatica gramatica;
    private ArreglarGramatica gramaticaArreglada;
    private RutaArchivo rutaArchivo;
    private ExtenderGramatica extenderGramatica;
    private GenerarAutomata generarAutomara;
   

    public ControlSistema() {
        this.gramatica = new Gramatica();
        this.gramaticaArreglada = new ArreglarGramatica();
        this.rutaArchivo = new RutaArchivo();
        this.extenderGramatica = new ExtenderGramatica();
        this.generarAutomara = new GenerarAutomata();
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
    
    public File buscarArchivo(){
        File archivo = this.rutaArchivo.buscarArchivo();
        return archivo;
    }
    
    public JSONArray leerGramatica(JSONArray gramatica){
        
        return extenderGramatica.extender(gramatica);
    }
    
    public JSONArray generarx(JSONArray gramatica){
        return generarAutomara.obtenerAutomata(gramatica);
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
