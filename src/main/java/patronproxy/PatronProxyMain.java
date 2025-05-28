package patronproxy;

import implementacion.FabricaServicios;
import implementacion.InterfaceProcesos;

public class PatronProxyMain {
    public static void main(String[] args) {
        String usuario = "fbolano";
        String password = "pds";
        int proceso = 1;

        // Cambia a "SOLOAUTH" para usar el nuevo proxy
        InterfaceProcesos proxy = FabricaServicios.CrearEjecucionProceso("SOLOAUTH");

        try {
            proxy.EjecutarProcesos(proceso, usuario, password);
        } catch (Exception e) {
            System.out.println("ERROR: " + e.getMessage());
        }
    }
}
