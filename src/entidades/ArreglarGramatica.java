package entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * clase encargada de transformar la gramatica cuando se encuentra un 
 * No Terminal con mas de una letra.
 *
 * @author Yenny, Camilo, Emanuel.
 * @version 1.0
 */
public class ArreglarGramatica {

    public JSONArray gramaticaArreglada;

    public ArreglarGramatica() {
        this.gramaticaArreglada = new JSONArray();
    }

    /**
     * Metodo Encargado de extraer los No Terminales con mas de una letra.
     *
     * @param gramatica gramatica seleccionada.
     */
    public void eliminarNoTerminalMayorAUno(JSONArray gramatica) {
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if ((jsonObject1.get("noTerminal") + "").length() > 1) {
                if (!((jsonObject1.get("noTerminal")
                        + "").charAt((jsonObject1.get("noTerminal")
                                + "").length() - 1) + "").equals("'")) {
                    gramatica = cambiarNoTerminal(gramatica,
                            (jsonObject1.get("noTerminal") + ""));
                }
            }
        }
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            
        }
        gramaticaArreglada = gramatica;
    }

    /**
     * Metodo encargado de cambiar no terminales con mas de una letra.
     *
     * @param gramatica gramatica seleccionada
     * @param noTerminal NO Terminal a cambiar dentro de la gramatica
     * @return JSONArray gramatica con el No Terminal cambiado.
     */
    public JSONArray cambiarNoTerminal(JSONArray gramatica, String noTerminal) {
        String letra = "";
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if ((jsonObject1.get("noTerminal") + "").equals(noTerminal)) {
                letra = validarLetra(gramatica);
                jsonObject1.put("noTerminal", letra);
                gramatica.set(i, jsonObject1);
            } else if ((jsonObject1.get("noTerminal") + "").equals(noTerminal + "'")) {
                jsonObject1.put("noTerminal", letra + "'");
                gramatica.set(i, jsonObject1);
            }
        }
        for (int i = 0; i < gramatica.size(); i++) {
            gramatica = cambiarProducciones(noTerminal, letra, gramatica);
        }
        return gramatica;
    }

    /**
     * Metodo encargado de cambiar las producciones donde se encuentre el No
     * Terminal con mas de 2 letras.
     *
     * @param noTerminal No Terminal que debe ser cambiado.
     * @param letra valor por el que debe cambiarse el No Terminal.
     * @param gramatica gramatica seleccionada.
     * @return JSONArray gramatica con producciones cambiadas.
     */
    public JSONArray cambiarProducciones(String noTerminal, String letra,
            JSONArray gramatica) {
        String producciones = "";
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            producciones = (jsonObject1.get("producciones")
                    + "").replaceAll(noTerminal, letra);
            jsonObject1.put("producciones", producciones);
            gramatica.set(i, jsonObject1);
        }
        return gramatica;
    }

    /**
     * Metodo encargado de Verificar que la letra no exista en los No terminales
     * de la gramatica.
     *
     * @param gramatica gramatica seleccionada
     * @return String letra seleccionada.
     */
    public String validarLetra(JSONArray gramatica) {
        char convertedChar = 0;
        int contador = 0;
        for (int j = 65; j < 91; j++) {
            convertedChar = (char) j;
            for (int i = 0; i < gramatica.size(); i++) {
                JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
                if (!(jsonObject1.get("noTerminal") + "").equals(convertedChar + "")) {
                    contador++;
                }
            }
            if (contador == gramatica.size()) {
                break;
            }
            contador = 0;

        }
        return (convertedChar + "");
    }

    public JSONArray getGramaticaArreglada() {
        return gramaticaArreglada;
    }

    public void setGramaticaArreglada(JSONArray gramaticaArreglada) {
        this.gramaticaArreglada = gramaticaArreglada;
    }

}
