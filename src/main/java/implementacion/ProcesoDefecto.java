package implementacion;

public class ProcesoDefecto implements InterfaceProcesos {

    @Override
    public void EjecutarProcesos(int IdProceso, String Usuario, String Password) throws Exception {
        System.out.println("proceso " + IdProceso + " en acción");
        System.out.println("proceso " + IdProceso + " finalizado");
    }
}