package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import es.unizar.si.g04.model.ClienteDAO;
import es.unizar.si.g04.model.ClienteVO;

/*@WebServlet("/LoginServlet")*/
public class LoginServlet extends HttpServlet {

    /**
	 * 
	 */
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
            System.out.println("Obtenido: " + cliente.getDni() + ", " + cliente.getPassword()  + ", " + cliente.getNombre() + ", " + cliente.getApellido());
            if (cliente.getNombre() != clienteNULL.getNombre()){ //TODO
                // Redirigir a la página de éxito
            	request.setAttribute("nombre", cliente.getNombre());
                request.setAttribute("apellido", cliente.getApellido());
                request.setAttribute("dni", cliente.getDni());
                request.getRequestDispatcher("loginCorrecto.jsp").forward(request, response);
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