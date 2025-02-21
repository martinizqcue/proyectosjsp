package servlets;

import entities.Proyectos;
import entities.Usuarios;
import hibernateUtil.hibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;

import java.text.ParseException;  // Importar ParseException


import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "svProyectos", urlPatterns = {"/svProyectos"})
public class svProyectos extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svProyectos</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svProyectos at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String estado = request.getParameter("estado");

        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            List<Proyectos> proyectos;

            if (estado != null && !estado.isEmpty()) {
                proyectos = session.createQuery("FROM Proyectos WHERE estado = :estado", Proyectos.class)
                        .setParameter("estado", estado)
                        .list();
            } else {
                proyectos = session.createQuery("FROM Proyectos", Proyectos.class).list();
            }

            request.setAttribute("proyectos", proyectos);
            request.getRequestDispatcher("/listaProyectos.jsp").forward(request, response);

        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error al obtener los proyectos.");
            request.getRequestDispatcher("/error.jsp").forward(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String accion = request.getParameter("accion");
        HttpSession session = request.getSession();
        Integer idUsuario = (Integer) session.getAttribute("idUsuario");

        if (idUsuario == null) {
            response.sendRedirect("index.jsp");
            return;
        }

        if ("registrar".equals(accion)) {
            try (Session hibernateSession = hibernateUtil.getSessionFactory().openSession()) {
                Transaction tx = hibernateSession.beginTransaction();

                String nombreProyecto = request.getParameter("nombre_proyecto");
                String descripcion = request.getParameter("descripcion");
                String fechaInicioString = request.getParameter("fecha_inicio");
                String fechaFinString = request.getParameter("fecha_fin");
                String estado = request.getParameter("estado");

                // Validación de datos
                if (nombreProyecto == null || descripcion == null || fechaInicioString == null || fechaFinString == null || estado == null) {
                    request.setAttribute("errorMessage", "Faltan datos en el formulario.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }

                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaInicio = dateFormat.parse(fechaInicioString);
                Date fechaFin = dateFormat.parse(fechaFinString);

                // Obtener usuario logueado
                Usuarios usuario = hibernateSession.get(Usuarios.class, idUsuario);
                if (usuario == null) {
                    request.setAttribute("errorMessage", "Usuario no encontrado.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                    return;
                }

                // Crear el proyecto
                Proyectos nuevoProyecto = new Proyectos();
                nuevoProyecto.setNombreProyecto(nombreProyecto);
                nuevoProyecto.setDescripcion(descripcion);
                nuevoProyecto.setFechaInicio(fechaInicio);
                nuevoProyecto.setFechaFin(fechaFin);
                nuevoProyecto.setEstado(estado);
                nuevoProyecto.setIdUsuario(usuario); // Relación con el usuario

                hibernateSession.save(nuevoProyecto);
                tx.commit();

                // Redirigir al usuario a la lista de proyectos o página de éxito
                response.sendRedirect("listaProyectos.jsp");

            } catch (ParseException e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error al parsear las fechas.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            } catch (Exception e) {
                e.printStackTrace();
                request.setAttribute("errorMessage", "Error al registrar el proyecto.");
                request.getRequestDispatcher("/error.jsp").forward(request, response);
            }
        }else if ("eliminarProyecto".equals(accion)) {
            // Acción para eliminar un proyecto
            String idProyectoStr = request.getParameter("idProyecto");

            if (idProyectoStr != null && !idProyectoStr.isEmpty()) {
                try (Session hibernateSession = hibernateUtil.getSessionFactory().openSession()) {
                    Transaction tx = hibernateSession.beginTransaction();
                    
                    int idProyecto = Integer.parseInt(idProyectoStr);
                    Proyectos proyecto = hibernateSession.get(Proyectos.class, idProyecto);

                    if (proyecto != null) {
                        // El proyecto existe, lo eliminamos
                        hibernateSession.delete(proyecto);
                        tx.commit();

                        // Redirigir a la lista de proyectos con un mensaje de éxito
                        request.getSession().setAttribute("successMessage", "Proyecto eliminado con éxito.");
                    } else {
                        // Si el proyecto no existe, mostramos un mensaje de error
                        request.getSession().setAttribute("errorMessage", "El proyecto con ID " + idProyecto + " no existe.");
                    }

                    // Redirigir al listado de proyectos después de la operación
                    response.sendRedirect("listaProyectos.jsp");

                } catch (Exception e) {
                    e.printStackTrace();
                    request.setAttribute("errorMessage", "Error al eliminar el proyecto.");
                    request.getRequestDispatcher("/error.jsp").forward(request, response);
                }
            } else {
                request.setAttribute("errorMessage", "Debe ingresar un ID de proyecto válido.");
                request.getRequestDispatcher("/eliminarProyecto.jsp").forward(request, response);
            }
        }
    }


    @Override
    public String getServletInfo() {
        return "Servlet para gestionar proyectos";
    }
}
