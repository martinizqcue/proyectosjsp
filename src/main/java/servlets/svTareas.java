package servlets;


import entities.Proyectos;
import entities.Tareas;
import hibernateUtil.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

@WebServlet(name = "svTareas", urlPatterns = {"/svTareas"})
public class svTareas extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svTareas</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svTareas at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtener el parámetro 'action' para saber qué acción se ha solicitado
        String action = request.getParameter("action");

        if ("actualizarProyecto".equals(action)) {
            // Si es la acción de actualizar, simplemente redirigimos al formulario de tareas
            request.getRequestDispatcher("/listarTareas.jsp").forward(request, response);
            return;
        }

        // Si no es la acción de actualizar, procederemos con el listado de tareas
        String idProyecto = request.getParameter("idProyecto");

        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            List<Tareas> tareas;
            List<Proyectos> proyectos = session.createQuery("FROM Proyectos", Proyectos.class).list();
            request.setAttribute("proyectos", proyectos); // Pasar proyectos al JSP

            // Filtrar tareas por proyecto si se ha seleccionado un proyecto
            if (idProyecto != null && !idProyecto.isEmpty()) {
                tareas = session.createQuery("FROM Tareas WHERE idProyecto.id = :idProyecto", Tareas.class)
                        .setParameter("idProyecto", Integer.parseInt(idProyecto))
                        .list();
            } else {
                tareas = session.createQuery("FROM Tareas", Tareas.class).list();
            }

            request.setAttribute("tareas", tareas);
            // Redirigir al JSP donde se listan las tareas por proyecto
            request.getRequestDispatcher("/listarTareasPorProyecto.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al obtener las tareas.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }


    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Verificar si la acción es eliminar
        String action = request.getParameter("action");

        if ("eliminar".equals(action)) {
            String idTareaStr = request.getParameter("idTarea");

            if (idTareaStr != null && !idTareaStr.isEmpty()) {
                try {
                    int idTarea = Integer.parseInt(idTareaStr);

                    try (Session session = hibernateUtil.getSessionFactory().openSession()) {
                        Transaction transaction = session.beginTransaction();

                        // Buscar la tarea por id
                        Tareas tarea = session.get(Tareas.class, idTarea);
                        if (tarea != null) {
                            // Si la tarea existe, la eliminamos
                            session.delete(tarea);
                            transaction.commit();
                            request.setAttribute("successMessage", "Tarea eliminada con éxito.");
                        } else {
                            // Si la tarea no existe, mostramos un mensaje de error
                            request.setAttribute("errorMessage", "La tarea con ID " + idTarea + " no existe.");
                        }
                    }

                    // Redirigir al listado de tareas con los mensajes
                    request.getRequestDispatcher("/listarTareasPorProyecto.jsp").forward(request, response);

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Error eliminando la tarea.");
                    request.getRequestDispatcher("/listarTareasPorProyecto.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Debe ingresar un ID de tarea válido.");
                request.getRequestDispatcher("/listarTareasPorProyecto.jsp").forward(request, response);
            }
            return;
        }

        // ------------------ Aquí sigue el código original para registrar tareas ------------------

        String idProyectoStr = request.getParameter("idProyecto");
        String descripcionTarea = request.getParameter("descripcionTarea");
        String responsable = request.getParameter("responsable");
        String fechaInicioStr = request.getParameter("fechaInicio");
        String fechaFinStr = request.getParameter("fechaFin");
        String estado = request.getParameter("estado");

        if (idProyectoStr != null && !idProyectoStr.isEmpty()) {
            try {
                int idProyecto = Integer.parseInt(idProyectoStr);
                Date fechaInicio = java.sql.Date.valueOf(fechaInicioStr);
                Date fechaFin = java.sql.Date.valueOf(fechaFinStr);

                try (Session session = hibernateUtil.getSessionFactory().openSession()) {
                    Proyectos proyecto = session.get(Proyectos.class, idProyecto);

                    if (proyecto != null) {
                        Tareas tarea = new Tareas();
                        tarea.setDescripcionTarea(descripcionTarea);
                        tarea.setResponsable(responsable);
                        tarea.setFechaInicio(fechaInicio);
                        tarea.setFechaFin(fechaFin);
                        tarea.setEstado(estado);
                        tarea.setIdProyecto(proyecto);

                        Transaction transaction = session.beginTransaction();
                        session.save(tarea);
                        transaction.commit();

                        response.sendRedirect("listarTareasPorProyecto.jsp");
                    } else {
                        request.setAttribute("errorMessage", "El proyecto con ID " + idProyecto + " no existe.");
                        request.getRequestDispatcher("/registroTarea.jsp").forward(request, response);
                    }
                }

            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error en los datos ingresados.");
                request.getRequestDispatcher("/registroTarea.jsp").forward(request, response);
            }
        } else {
            request.setAttribute("errorMessage", "Debe ingresar un ID de proyecto válido.");
            request.getRequestDispatcher("/registroTarea.jsp").forward(request, response);
        }
    }


    @Override
    public String getServletInfo() {
        return "Servlet para gestionar tareas";
    }
}
