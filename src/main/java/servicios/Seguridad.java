package servicios;

import okhttp3.*;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Seguridad {

    private static final String SUPABASE_URL = "https://umuvhqfzotyekfoizztg.supabase.co/rest/v1/";
    private static final String API_KEY = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJpc3MiOiJzdXBhYmFzZSIsInJlZiI6InVtdXZocWZ6b3R5ZWtmb2l6enRnIiwicm9sZSI6ImFub24iLCJpYXQiOjE3NDg0NDkxNTMsImV4cCI6MjA2NDAyNTE1M30.emI0y0KSbr0F1wegLwDoxBogBlOAWSQxNBsvt5bJw4E";
    private static final OkHttpClient client = new OkHttpClient();
    private static final ObjectMapper mapper = new ObjectMapper();

    public boolean AutenticacionViaAPI(String usuario, String password) {
    try {
        String tabla = "usuarios";
        String url = SUPABASE_URL + tabla + "?username=eq." + usuario + "&password=eq." + password;

        Request request = new Request.Builder()
                .url(url)
                .addHeader("apikey", API_KEY)
                .addHeader("Authorization", "Bearer " + API_KEY)
                .addHeader("Content-Type", "application/json")
                .addHeader("Accept", "application/json")
                .build();

        Response response = client.newCall(request).execute();

        String respuestaJson = response.body().string(); // respuesta como texto
        System.out.println("Respuesta de Supabase: " + respuestaJson);

        if (response.isSuccessful()) {
            JsonNode result = mapper.readTree(respuestaJson);
            return result.isArray() && result.size() > 0;
        } else {
            System.out.println("Error en Supabase: " + response.message());
            return false;
        }
    } catch (Exception e) {
        System.out.println("Excepción al autenticar con Supabase: " + e.getMessage());
        return false;
    }
}

    public boolean Autorizacion(String user, String password) {
        return "fbolano".equals(user) && "pds".equals(password);
    }
}
