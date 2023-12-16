package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;

import es.unizar.si.g04.model.AdminDAO;
import es.unizar.si.g04.model.AdministradorVO;
import es.unizar.si.g04.model.ClienteDAO;
import es.unizar.si.g04.model.ClienteVO;

/*@WebServlet("/LoginServlet")*/
public class LoginServletAdmin extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServletAdmin() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetros del formulario
        String usuario = request.getParameter("DNI");
        String contrasena = request.getParameter("Password");

        AdminDAO adminDAO = new AdminDAO(); // Crear instancia DAO
        AdministradorVO admin = new AdministradorVO(null, null);
        AdministradorVO adminNULL = new AdministradorVO(null, null);

        try {
            admin = (adminDAO.verificarCredenciales(usuario, contrasena));
            if (admin.getDni() != adminNULL.getDni()) { // TODO
                // Redirigir a la página de éxito

                Cookie cookieDNI = new Cookie("usuario", usuario);
                cookieDNI.setMaxAge(30 * 60); // La cookie expirará en 30 minutos
                response.addCookie(cookieDNI);

                // Obtiene la sesión actual o crea una nueva
                HttpSession session = request.getSession(true);

                // Almacena la información en la sesión
                session.setAttribute("dni", admin.getDni());

                // Redirige a la página de éxito
                response.sendRedirect("loginCorrectoAdmin.jsp");
            } else {
                // Redirigir a la página de error
                request.setAttribute("errorMessage", "Error al realizar el login, campos invalidos");
                request.getRequestDispatcher("loginNOcorrectoAdmin.jsp").forward(request, response);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }
}
