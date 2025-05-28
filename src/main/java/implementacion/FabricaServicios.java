package implementacion;

public class FabricaServicios {
    public static InterfaceProcesos CrearEjecucionProceso(String tipoProxy) {
        if ("AUDITABLE".equalsIgnoreCase(tipoProxy)) {
            return new ProxyProcesos(); // con auditoría
        } else if ("SOLOAUTH".equalsIgnoreCase(tipoProxy)) {
            return new ProxySoloAuth(); // sin auditoría
        } else {
            throw new IllegalArgumentException("Tipo de proxy desconocido: " + tipoProxy);
        }
    }
}