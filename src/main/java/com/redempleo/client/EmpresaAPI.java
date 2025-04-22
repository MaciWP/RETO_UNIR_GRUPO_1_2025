package com.redempleo.client;

import com.google.gson.Gson;
import com.redempleo.dto.empresa.EmpresaDTO;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.List;

public class EmpresaAPI {

    private static final String BASE_URL = "http://localhost:8081/api/empresas";
    private static final HttpClient client = HttpClient.newHttpClient();
    private static final Gson gson = new Gson();

    public static boolean crearEmpresa(EmpresaDTO empresa) {
        try {
            String json = gson.toJson(empresa);

            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .header("Content-Type", "application/json")
                    .POST(HttpRequest.BodyPublishers.ofString(json))
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            return response.statusCode() == 200 || response.statusCode() == 201;

        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    public static List<EmpresaDTO> getEmpresas() {
        try {
            HttpRequest request = HttpRequest.newBuilder()
                    .uri(URI.create(BASE_URL))
                    .GET()
                    .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            if (response.statusCode() == 200) {
                EmpresaDTO[] empresas = gson.fromJson(response.body(), EmpresaDTO[].class);
                return List.of(empresas); // Devuelve lista inmutable
            } else {
                System.err.println("Error al obtener empresas. Código: " + response.statusCode());
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return List.of(); // Lista vacía en caso de error
    }
}

