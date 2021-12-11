package entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Juan Camilo Uni Lara
 */
public class GenerarAutomata
{

    String primeros = "";
    JSONArray solucion = new JSONArray();
    JSONObject jsonEstados = new JSONObject();
    JSONArray gramaticaEstado = new JSONArray();

    int estado = 0;

    public JSONArray obtenerAutomata(JSONArray gramatica)
    {

        JSONObject jsonObject1 = (JSONObject) gramatica.get(0);
        System.out.println(jsonObject1);
        if (this.solucion.size() == 0)
        {
            JSONObject variablesEstado = new JSONObject();
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
        for (int i = 0; i < solucion.size(); i++)
        {
            JSONObject jsonObject2 = (JSONObject) solucion.get(0);
            JSONArray jsonObject3 = (JSONArray) jsonObject2.get("gramaticaEstado");
            for (int j = 0; j < jsonObject3.size(); j++)
            {
                JSONObject jsonObject4 = (JSONObject) jsonObject3.get(0);
                String producciones = jsonObject4.get("producciones") + "";
                int posicionPunto = producciones.indexOf(".");
                if (Character.isUpperCase(producciones.charAt(posicionPunto + 1)))
                {
                    generarEstados(gramatica, producciones.charAt(posicionPunto + 1) + "",
                            jsonObject4.get("noTerminal") + "");
                }
            }
        }
        return solucion;
    }

    public void generarEstados(JSONArray gramatica, String letra, String inicial)
    {

        for (int i = 0; i < gramatica.size(); i++)
        {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            System.out.println(jsonObject1.get("noTerminal") + "");
            if (letra.equals(jsonObject1.get("noTerminal") + ""))
            {

                JSONObject variablesEstado = new JSONObject();
                variablesEstado.put("noTerminales", letra);
                variablesEstado.put("producciones", jsonObject1.get("producciones"));
                variablesEstado.put("primeros", generarPrimeros(gramatica, inicial, letra));
                System.out.println(primeros);
                primeros = "";
            }
        }
    }

    public String generarPrimeros(JSONArray gramatica, String inicial, String letra)
    {

        for (int i = 0; i < gramatica.size(); i++)
        {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if (jsonObject1.get("noTerminal").equals(inicial))
            {
                String produccion = jsonObject1.get("producciones") + "";
                int posicionPunto = produccion.indexOf(".");
                System.out.println();
                if (produccion.length() == 2)
                {
                    for (int j = 0; j < this.solucion.size(); j++)
                    {
                        JSONObject jsonObject2 = (JSONObject) this.solucion.get(j);
                        JSONArray jsonObject3 = (JSONArray) jsonObject2.get("gramaticaEstado");
                        for (int m = 0; m < jsonObject3.size(); m++)
                        {
                            JSONObject jsonObject4 = (JSONObject) jsonObject3.get(0);
                            if (inicial.equals(jsonObject4.get("noTerminal") + ""))
                            {
                                primeros += jsonObject4.get("primeros") + "";
                                break;
                            }
                        }
                    }
                }
                else
                {
                    produccion = produccion.replace("." + letra, "");
                    if (Character.isLowerCase(produccion.charAt(1)))
                    {
                        for (int k = 0; k < produccion.length(); k++)
                        {
                            if (Character.isUpperCase(produccion.charAt(k)))
                            {
                                break;
                            }
                            else
                            {
                                primeros += produccion.charAt(k);
                            }
                        }
                    }
                    else
                    {
                        generarPrimeros(gramatica, produccion.charAt(1) + "", letra);
                    }
                }

            }
        }
        System.out.println (primeros);
    return primeros ;
    }
    
}
