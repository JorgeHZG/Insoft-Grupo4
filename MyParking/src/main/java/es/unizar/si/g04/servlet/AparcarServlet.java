package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
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

		// Lógica de base de datos
		PlazaDAO plazaDAO = new PlazaDAO(); // Crear instancia DAO

;		try {

			PlazaVO plaza = plazaDAO.Aparcar(request.getParameter("matricula"));
			System.out.println("Plaza: " + String.valueOf(plaza.getNumeroPlaza()));
			System.out.println("Estado: " + String.valueOf(plaza.getEstadoPlaza()));
			System.out.println("Tipo: " + String.valueOf(plaza.getTipoPLaza()));
			
			request.setAttribute("numero", plaza.getNumeroPlaza());
			System.out.println("Valor de la plaza: " + String.valueOf(plaza.getNumeroPlaza()));
			if (plaza.getNumeroPlaza() == -1) {
				System.out.println("No hay plazas disponibles");
				request.setAttribute("errorMessage",
						"No se ha encontrado una plaza libre para el tipo de vehiculo seleccionado");
				request.getRequestDispatcher("AparcarCoche.jsp").forward(request, response);
			} else if (plaza.getNumeroPlaza() == -2) {
				request.setAttribute("errorMessage", "El vehiculo debe salir del parking para volver a aparcar");
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