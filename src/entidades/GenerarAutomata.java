package entidades;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 *
 * @author Juan Camilo Uni Lara
 */
public class GenerarAutomata {

    String primeros = "";
    JSONArray solucion = new JSONArray();
    JSONArray solucionAuxiliar = new JSONArray();

    int estado = 0;

    public JSONArray obtenerAutomata(JSONArray gramatica) {

        JSONObject jsonObject1 = (JSONObject) gramatica.get(estado);
        solucion.add(generarLineaSolucion(estado, jsonObject1.get("noTerminal") + "",
                jsonObject1.get("producciones") + "", "$",
                (jsonObject1.get("producciones") + "").charAt((jsonObject1.get("producciones") + "").indexOf(".") + 1) + ""));

        for (int i = 0; i < solucion.size(); i++) {
            JSONObject jsonObject2 = (JSONObject) solucion.get(i);
            if (!(jsonObject2.get("transicion") + "").equals(jsonObject2.get("noTerminal") + "")) {
                generarEstados(gramatica, jsonObject2.get("transicion") + "", jsonObject2.get("noTerminal") + "");
            }
        }
        crearEstado(gramatica);
        return solucion;
    }

    public void crearEstado(JSONArray gramatica) {
        estado += 1;
        for (int i = 0; i < solucion.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) solucion.get(i);
            if ((jsonObject1.get("estado")).equals(0)) {
                solucionAuxiliar.add(jsonObject1);
            }
        }
        for (int i = 0; i < solucionAuxiliar.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) solucionAuxiliar.get(i);
            String pr = jsonObject1.get("producciones") + "";

            int lugarPunto = (jsonObject1.get("producciones") + "").indexOf(".");

            String x = (jsonObject1.get("producciones") + "").replace(".", "");
            x = x.substring(0, lugarPunto + 1) + "." + x.substring(lugarPunto + 1);

            if ((x.indexOf(".") + 1) < x.length()) {
                solucion.add(generarLineaSolucion(estado, jsonObject1.get("noTerminal") + "", x,
                        jsonObject1.get("primeros") + "", x.charAt(x.indexOf(".") + 1) + ""));
                solucionAuxiliar.add(generarLineaSolucion(estado, jsonObject1.get("noTerminal") + "", x,
                        jsonObject1.get("primeros") + "", x.charAt(x.indexOf(".") + 1) + ""));

                if (Character.isUpperCase(x.charAt(x.indexOf(".") + 1))) {
                    int bandera = 0;
                    for (int j = 0; j < solucion.size(); j++) {
                        JSONObject jsonObject2 = (JSONObject) solucion.get(j);
                        if ((jsonObject2.get("transicion") + "").equals(x.charAt(x.indexOf(".") + 1) + "")
                                && (jsonObject2.get("primeros") + "").equals(jsonObject1.get("primeros") + "")
                                && (jsonObject2.get("noTerminal") + "").equals(x.charAt(x.indexOf(".") + 1) + "")) {
                            bandera += 1;
                        }
                    }
                    if (bandera == 0) {
                        generarEstadosSiguientes(gramatica, (x.charAt(x.indexOf(".") + 1) + ""), (jsonObject1.get("noTerminal") + ""));
                    }
                }

            } else {
                solucion.add(generarLineaSolucion(estado, jsonObject1.get("noTerminal") + "", x,
                        jsonObject1.get("primeros") + "", ""));

            }
            int contador = 0;
            int tamanoActual = solucionAuxiliar.size();
            for (int j = i + 1; j < tamanoActual; j++) {

                JSONObject jsonObject2 = (JSONObject) solucionAuxiliar.get(j);
                String pr2 = jsonObject2.get("producciones") + "";

                int lugarPunto2 = (jsonObject2.get("producciones") + "").indexOf(".");

                String x2 = (jsonObject2.get("producciones") + "").replace(".", "");
                x2 = x2.substring(0, lugarPunto2 + 1) + "." + x2.substring(lugarPunto2 + 1);

                if ((jsonObject1.get("transicion") + "").equals(jsonObject2.get("transicion"))
                        && (jsonObject1.get("primeros") + "").equals(jsonObject2.get("primeros") + "")) {
                    if (i + 1 == j) {
                        contador++;
                    }

                    if ((x2.indexOf(".") + 1) < x2.length()) {
                        solucion.add(generarLineaSolucion(estado, jsonObject2.get("noTerminal") + "", x2,
                                jsonObject2.get("primeros") + "", x2.charAt(x2.indexOf(".") + 1) + ""));
                        solucionAuxiliar.add(generarLineaSolucion(estado, jsonObject2.get("noTerminal") + "", x2,
                                jsonObject2.get("primeros") + "", x2.charAt(x2.indexOf(".") + 1) + ""));

                    } else {
                        solucion.add(generarLineaSolucion(estado, jsonObject2.get("noTerminal") + "", x2,
                                jsonObject2.get("primeros") + "", ""));
                    }
                }

            }
            estado = estado + 1;
            i = i + contador;

        }
    }

    public JSONObject generarLineaSolucion(int nestado, String noTerminal, String producciones, String primeros, String transicion) {
        JSONObject lineaSolucion = new JSONObject();
        lineaSolucion.put("estado", nestado);
        lineaSolucion.put("noTerminal", noTerminal);
        lineaSolucion.put("producciones", producciones);
        lineaSolucion.put("primeros", primeros);
        lineaSolucion.put("transicion", transicion);
        return lineaSolucion;
    }

    public void generarEstadosSiguientes(JSONArray gramatica, String letra, String inicial) {
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if ((jsonObject1.get("noTerminal") + "").equals(letra)) {
                solucionAuxiliar.add(generarLineaSolucion(estado, letra, (jsonObject1.get("producciones") + ""),
                        generarPrimerosC1(gramatica, inicial, letra),
                        (jsonObject1.get("producciones") + "").charAt((jsonObject1.get("producciones") + "").indexOf(".") + 1) + ""));
                solucion.add(generarLineaSolucion(estado, letra, (jsonObject1.get("producciones") + ""),
                        generarPrimerosC1(gramatica, inicial, letra),
                        (jsonObject1.get("producciones") + "").charAt((jsonObject1.get("producciones") + "").indexOf(".") + 1) + ""));
            }
            primeros = "";
        }
    }

    public String generarPrimerosC1(JSONArray gramatica, String inicial, String letra) {
        for (int i = 0; i < solucion.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) solucion.get(i);
            if (jsonObject1.get("noTerminal").equals(inicial)) {
                String produccion = jsonObject1.get("producciones") + "";
                if (produccion.contains("." + letra)) {
                    int posPunto = produccion.indexOf(".");
                    if (posPunto + 2 < produccion.length()) {

                        if (Character.isUpperCase(produccion.charAt(posPunto + 2))) {
                            for (int j = 0; j < gramatica.size(); j++) {
                                JSONObject jsonObject2 = (JSONObject) gramatica.get(j);
                                if ((jsonObject2.get("noTerminal") + "").equals(produccion.charAt(posPunto + 2) + "")) {
                                    String var = jsonObject2.get("producciones") + "";
                                    var = var.charAt(var.indexOf(".") + 1) + "";
                                    generarPrimeros(gramatica, produccion.charAt(posPunto + 2) + "", var);
                                }
                            }

                        } else {

                            for (int k = 0; k < produccion.length(); k++) {
                                if (Character.isUpperCase(produccion.charAt(k))) {
                                    break;
                                } else {
                                    primeros += produccion.charAt(k);
                                }
                            }
                        }
                    } else {
                        primeros = "";
                        for (int j = 0; j < this.solucion.size(); j++) {
                            JSONObject jsonObject2 = (JSONObject) this.solucion.get(j);

                            if (inicial.equals(jsonObject2.get("noTerminal") + "")) {
                                primeros += jsonObject2.get("primeros") + "";
                                break;
                            }
                        }
                    }
                }
            }
        }
        String primeroArreglo = "";
        for (int p = 0; p < primeros.length(); p++) {
            primeroArreglo += primeros.charAt(p) + ",";
        }
        primeroArreglo = primeroArreglo.substring(0, primeroArreglo.length() - 1);

        return primeroArreglo;
    }

    public void generarEstados(JSONArray gramatica, String letra, String inicial) {
        int bandera = 0;
        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if ((jsonObject1.get("noTerminal") + "").equals(letra)) {
                for (int j = 0; j < solucion.size(); j++) {
                    JSONObject jsonObject2 = (JSONObject) solucion.get(j);
                    if ((jsonObject2.get("noTerminal") + "").equals(letra)
                            && (jsonObject2.get("producciones") + "").equals(jsonObject1.get("producciones"))) {
                        bandera += 1;
                    }
                }
                if (bandera == 0) {
                    solucion.add(generarLineaSolucion(estado, letra, (jsonObject1.get("producciones") + ""),
                            generarPrimeros(gramatica, inicial, letra),
                            (jsonObject1.get("producciones") + "").charAt((jsonObject1.get("producciones") + "").indexOf(".") + 1) + ""));
                }
            }
            primeros = "";
        }
    }

    public String generarPrimeros(JSONArray gramatica, String inicial, String letra) {

        for (int i = 0; i < gramatica.size(); i++) {
            JSONObject jsonObject1 = (JSONObject) gramatica.get(i);
            if (jsonObject1.get("noTerminal").equals(inicial)) {
                String produccion = jsonObject1.get("producciones") + "";
                if (produccion.contains("." + letra)) {
                    if (produccion.length() == 2) {
                        if (Character.isUpperCase(produccion.charAt(produccion.indexOf(".") + 1))) {
                            for (int j = 0; j < this.solucion.size(); j++) {
                                JSONObject jsonObject2 = (JSONObject) this.solucion.get(j);

                                if (inicial.equals(jsonObject2.get("noTerminal") + "")) {
                                    primeros += jsonObject2.get("primeros") + "";
                                    break;
                                }
                            }
                        } else {
                            produccion = produccion.replace(".", "");
                            for (int k = 0; k < produccion.length(); k++) {
                                if (Character.isUpperCase(produccion.charAt(k))) {
                                    break;
                                } else {
                                    primeros += produccion.charAt(k);
                                }
                            }
                        }

                    } else {
                        produccion = produccion.replace("." + letra, "");
                        if (Character.isUpperCase(produccion.charAt(0))) {
                            for (int j = 0; j < gramatica.size(); j++) {
                                JSONObject jsonObject2 = (JSONObject) gramatica.get(j);
                                if ((jsonObject2.get("noTerminal") + "").equals(produccion.charAt(0) + "")) {
                                    String var = jsonObject2.get("producciones") + "";
                                    var = var.charAt(var.indexOf(".") + 1) + "";
                                    generarPrimeros(gramatica, produccion.charAt(0) + "", var);
                                }
                            }

                        } else {

                            for (int k = 0; k < produccion.length(); k++) {
                                if (Character.isUpperCase(produccion.charAt(k))) {
                                    break;
                                } else {
                                    primeros += produccion.charAt(k);
                                }
                            }
                        }
                    }
                }
            }
        }
        String primeroArreglo = "";
        for (int p = 0; p < primeros.length(); p++) {
            primeroArreglo += primeros.charAt(p) + ",";
        }
        primeroArreglo = primeroArreglo.substring(0, primeroArreglo.length() - 1);
        return primeroArreglo;
    }

}
