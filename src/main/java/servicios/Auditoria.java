package servicios;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Auditoria {
    public void AuditoriaServicioUsado(String user, String servicio) {
        SimpleDateFormat formater = new SimpleDateFormat("dd/MM/yyyy hh:mm:ss");
        System.out.println(user + " utilizó el servicio > " 
                + servicio + ", a las " + formater.format(new Date()));
    }
}