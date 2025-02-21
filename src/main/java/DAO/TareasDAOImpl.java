/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Tareas;
import hibernateUtil.hibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumno
 */
public class TareasDAOImpl implements TareasDAO {

    @Override
    public List<Tareas> obtenerTareasPorProyecto(int idProyecto) {
        List<Tareas> tareas = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            tareas = session.createQuery("FROM Tareas WHERE id_proyecto = :idProyecto", Tareas.class)
                    .setParameter("idProyecto", idProyecto)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return tareas;
    }

    @Override
    public void guardarTarea(Tareas tarea) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(tarea);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarTarea(int idTarea) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Tareas tarea = session.get(Tareas.class, idTarea);
            if (tarea != null) {
                session.delete(tarea);
            }
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }
}