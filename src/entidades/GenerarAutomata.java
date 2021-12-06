package entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Juan Camilo Uni Lara
 */
public class GenerarAutomata {

    JSONArray solucion = new JSONArray();
    JSONObject jsonEstados = new JSONObject();
    JSONArray gramaticaEstado = new JSONArray();
    JSONObject variablesEstado = new JSONObject();
    int estado = 0;

    public JSONArray obtenerAutomata(JSONArray gramatica) {

        JSONObject jsonObject1 = (JSONObject) gramatica.get(0);
        System.out.println(jsonObject1);
        if (this.solucion.size() == 0) {
            jsonEstados.put("estado", estado);

            variablesEstado.put("noTerminal",
                    jsonObject1.get("noTerminal"));
            variablesEstado.put("producciones",
                    jsonObject1.get("producciones"));
            variablesEstado.put("primeros", "$");
            gramaticaEstado.add(variablesEstado);
            jsonEstados.put("gramaticaEstado", gramaticaEstado);
            solucion.add(jsonEstados);
            
        }
 
        
        return solucion;
    }

    public void generarEstados(JSONArray gramatica, String letra, String inicial) {

        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);

            if (jsonObject1.get("noTerminal").equals(letra)) {
                variablesEstado.clear();
                variablesEstado.put("noTerminales", letra);
                variablesEstado.put("producciones", jsonObject1.get("producciones"));
                variablesEstado.put("primeros", "$");
            }
        }
    }
    
    public void generarPrimeros(JSONArray gramatica, String inicial){
        
    }
}
