package implementacion;
import servicios.Seguridad;

public class ProxySoloAuth implements InterfaceProcesos {

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        Seguridad seguridad = new Seguridad();
        if (!seguridad.AutenticacionViaAPI(Usuario, Password)) {
            throw new Exception("Usuario no autorizado para ejecutar procesos.");
        }

        ProcesoDefecto ejecutor = new ProcesoDefecto();
        ejecutor.EjecutarProcesos(IdProceso, Usuario, Password);
    }
}