/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hibernateUtil;


/**
 *
 * @author alumno
 */
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class hibernateUtil {

    public static SessionFactory getSessionFactory() {
        // Crear configuración de Hibernate
        Configuration configuration = new Configuration();

        // Cargar configuración desde el archivo hibernate.cfg.xml
        configuration.configure();

        // Devolver la fábrica de sesiones configurada
        return configuration.buildSessionFactory();
    }
}
