package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import es.unizar.si.g04.model.ClienteDAO;
import es.unizar.si.g04.model.ClienteVO;

//Servlet implementation class RegistroServlet
public class RegistroServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public RegistroServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Parámetros del formulario
		String dni = request.getParameter("DNI");
		String contrasena = request.getParameter("Password");
		String nombre = request.getParameter("Nombre");
		String apellido = request.getParameter("Apellido");

		// Lógica de base de datos
		ClienteDAO clienteDAO = new ClienteDAO(); // Crear instancia DAO

		try {
			clienteDAO.addUser(dni, contrasena, nombre, apellido);

			request.setAttribute("nombre", nombre);
			request.setAttribute("apellido", apellido);
			request.setAttribute("dni", dni);

			request.getRequestDispatcher("loginNOcorrecto.jsp").forward(request, response);

		} catch (Exception e) {
			System.out.println("Fallo al anadir usuario");
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
