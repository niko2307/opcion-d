package implementacion;

import servicios.Auditoria;
import servicios.Seguridad;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProxyProcesos implements InterfaceProcesos {

    private static final Map<String, Integer> conteoProcesos = new HashMap<>();
    private static final SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad securityService = new Seguridad();
        if (!securityService.Autorizacion(Usuario, Password)) {
            throw new Exception("El usuario '" + Usuario + "' no tiene privilegios para ejecutar el proceso");
        }

        String fechaHoy = sdf.format(new Date());
        String clave = Usuario + "_" + fechaHoy;
        int conteo = conteoProcesos.getOrDefault(clave, 0);

        if (conteo >= 3) {
            throw new Exception("El usuario '" + Usuario + "' ha excedido el límite de ejecuciones por día.");
        }

        conteoProcesos.put(clave, conteo + 1);

        ProcesoDefecto ejecutorProcess = new ProcesoDefecto();
        ejecutorProcess.EjecutarProcesos(IdProceso, Usuario, Password);

        Auditoria audit = new Auditoria();
        audit.AuditoriaServicioUsado(Usuario, ProcesoDefecto.class.getName());
    }
}