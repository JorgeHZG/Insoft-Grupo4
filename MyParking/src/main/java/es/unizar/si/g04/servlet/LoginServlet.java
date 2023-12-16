package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import es.unizar.si.g04.model.ClienteDAO;
import es.unizar.si.g04.model.ClienteVO;

/*@WebServlet("/LoginServlet")*/
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public LoginServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetros del formulario
        String usuario = request.getParameter("DNI");
        String contrasena = request.getParameter("Password");

        // Lógica de base de datos
        ClienteDAO clienteDAO = new ClienteDAO(); // Crear instancia DAO
        ClienteVO cliente = new ClienteVO(null, null, null, null);
        ClienteVO clienteNULL = new ClienteVO(null, null, null, null);

        try {
            cliente = (clienteDAO.verificarCredenciales(usuario, contrasena));
            // System.out.println("Obtenido: " + cliente.getDni() + ", " +
            // cliente.getPassword() + ", " + cliente.getNombre() + ", " +
            // cliente.getApellido());
            if (cliente.getDni() != clienteNULL.getDni()) {
                // Redirigir a la página de éxito

                Cookie cookieDNI = new Cookie("usuario", usuario);
                cookieDNI.setMaxAge(30 * 60); // La cookie expirará en 30 minutos
                response.addCookie(cookieDNI);

                // Obtiene la sesión actual o crea una nueva
                HttpSession session = request.getSession(true);

                // Almacena la información en la sesión
                session.setAttribute("nombre", cliente.getNombre());
                session.setAttribute("apellido", cliente.getApellido());
                session.setAttribute("dni", cliente.getDni());

                // Redirige a la página de éxito
                response.sendRedirect("loginCorrecto.jsp");
            } else {
                // Redirigir a la página de error
                request.setAttribute("errorMessage", "Error al realizar el login, campos invalidos");
                request.getRequestDispatcher("loginNOcorrecto.jsp").forward(request, response);
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
