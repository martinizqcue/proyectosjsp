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
public interface ProyectosDAO {
    List<Proyectos> obtenerProyectosPorEstado(String estado);
    void guardarProyecto(Proyectos proyecto);
    void eliminarProyecto(int idProyecto);
}
