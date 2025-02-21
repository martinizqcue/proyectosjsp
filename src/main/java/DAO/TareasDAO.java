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
public interface TareasDAO {
    List<Tareas> obtenerTareasPorProyecto(int idProyecto);
    void guardarTarea(Tareas tarea);
    void eliminarTarea(int idTarea);
}
