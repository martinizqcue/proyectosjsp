/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package servlets;

import DAO.UsuariosDAOImpl;
import entities.Usuarios;
import hibernateUtil.hibernateUtil;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.hibernate.Session;

/**
 *
 * @author alumno
 */
@WebServlet(name = "svLogin", urlPatterns = {"/svLogin"})
public class svLogin extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet svLogin</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet svLogin at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String nombreUsuario = request.getParameter("nombre_usuario");
        String contraseña = request.getParameter("contraseña");

        try (Session session = hibernateUtil.getSessionFactory().openSession()) {
            // Verificar si el usuario existe y la contraseña es correcta
            Usuarios usuario = (Usuarios) session.createQuery("FROM Usuarios WHERE nombre_usuario = :nombre_usuario AND contraseña = :contraseña")
                    .setParameter("nombre_usuario", nombreUsuario)
                    .setParameter("contraseña", contraseña)
                    .uniqueResult();

            if (usuario != null) {
                // Si el usuario existe, almacenar su id y rol en la sesión
                HttpSession httpSession = request.getSession();
                httpSession.setAttribute("idUsuario", usuario.getId());  
                httpSession.setAttribute("role", usuario.getRol());  
                httpSession.setAttribute("nombre_usuario", usuario.getNombreUsuario()); // Agregar nombre de usuario

                // Verificar el rol del usuario y redirigir a la página correspondiente
                String rol = usuario.getRol();
                if ("admin".equals(rol)) {
                    // Si el rol es admin, redirigir a la página de admin
                    response.sendRedirect("admin.jsp");
                } else if ("invitado".equals(rol)) {
                    // Si el rol es invitado, redirigir a la página de invitado
                    response.sendRedirect("invitado.jsp");
                } else {
                    // En caso de que el rol sea desconocido o incorrecto
                    request.setAttribute("errorMessage", "Rol no reconocido.");
                    request.getRequestDispatcher("/index.jsp").forward(request, response);
                }
            } else {
                // Si no se encuentra el usuario, mostrar mensaje de error
                request.setAttribute("errorMessage", "Usuario o contraseña incorrectos.");
                request.getRequestDispatcher("/index.jsp").forward(request, response);
            }
        } catch (Exception e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Error en el proceso de login.");
            request.getRequestDispatcher("/index.jsp").forward(request, response);
        }
    
    
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
