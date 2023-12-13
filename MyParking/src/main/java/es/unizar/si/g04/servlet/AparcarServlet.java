package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import es.unizar.si.g04.model.PlazaDAO;
import es.unizar.si.g04.model.PlazaVO;

/*@WebServlet("/ReservaPlaza")*/
public class AparcarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public AparcarServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// Parámetros del formulario
		// String tipo = request.getParameter("tipoVehiculo");

		// Lógica de base de datos
		PlazaDAO plazaDAO = new PlazaDAO(); // Crear instancia DAO

		try {
			PlazaVO plaza = plazaDAO.Aparcar(request.getParameter("matricula"));

			request.setAttribute("numero", plaza.getNumeroPlaza());

			if (plaza.getNumeroPlaza() == -1) {
				System.out.println("No hay plazas disponibles");
				request.setAttribute("errorMessage",
						"No se ha encontrado una plaza libre para el tipo de vehiculo seleccionado");
				request.getRequestDispatcher("AparcarCoche.jsp").forward(request, response);
			} else {
				request.getRequestDispatcher("plazaDisponible.jsp").forward(request, response);
			}

		} catch (Exception e) {

			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}