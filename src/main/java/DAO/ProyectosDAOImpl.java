/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import entities.Proyectos;
import hibernateUtil.hibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 *
 * @author alumno
 */
public class ProyectosDAOImpl implements ProyectosDAO {
    @Override
    public List<Proyectos> obtenerProyectosPorEstado(String estado) {
        List<Proyectos> proyectos = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            proyectos = session.createQuery("FROM Proyectos WHERE estado = :estado", Proyectos.class)
                    .setParameter("estado", estado)
                    .list();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return proyectos;
    }

    @Override
    public void guardarProyecto(Proyectos proyecto) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            session.save(proyecto);
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        }
    }

    @Override
    public void eliminarProyecto(int idProyecto) {
        Transaction transaction = null;
        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            transaction = session.beginTransaction();
            Proyectos proyecto = session.get(Proyectos.class, idProyecto);
            if (proyecto != null) {
                session.delete(proyecto);
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
