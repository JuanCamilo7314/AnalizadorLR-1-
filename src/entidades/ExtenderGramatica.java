package entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Juan Camilo Uni Lara
 */
public class ExtenderGramatica {

    public JSONArray extender(JSONArray gramatica) {

        String letra = buscarLetraExtendible(gramatica);
        for (int i = 0; i < 1; i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            JSONObject extendido = new JSONObject();
            if (i == 0) {
                extendido.put("noTerminal", letra);
                extendido.put("producciones", jsonObject1.get("noTerminal"));
            }
            gramatica.add(i, extendido);
        }
        gramatica=inicializar(gramatica);
        return gramatica;
    }
    
    public String buscarLetraExtendible(JSONArray gramatica){
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
        return convertedChar+"";
    }

    public JSONArray inicializar(JSONArray gramatica){
         for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            jsonObject1.put("producciones", "."+jsonObject1.get("producciones"));
            gramatica.set(i,jsonObject1);
        }
        return gramatica;
    }
}
