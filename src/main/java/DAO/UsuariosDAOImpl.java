/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Usuarios;
import hibernateUtil.hibernateUtil;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.query.Query;

/**
 *
 * @author alumno
 */
public class UsuariosDAOImpl {

    // Método para validar el usuario y su contraseña
    public static Usuarios validarUsuario(String usuario, String contrasena) {
        Session session = hibernateUtil.getSessionFactory().openSession();
        Usuarios user = null;
        
        try {
            // Crear una consulta para obtener al usuario basado en el nombre de usuario y la contraseña
            Query query = session.createQuery("FROM Usuarios WHERE nombre_usuario = :usuario AND contraseña = :contraseña");
            query.setParameter("usuario", usuario);
            query.setParameter("contraseña", contrasena);
            
            // Ejecutar la consulta y obtener el primer resultado
            user = (Usuarios) query.uniqueResult();
        } catch (HibernateException e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        
        return user;  // Retorna el usuario si es válido, de lo contrario retorna null
    }
}
