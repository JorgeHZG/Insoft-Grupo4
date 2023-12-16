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
public class DesaparcarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public DesaparcarServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		PlazaDAO plazaDAO = new PlazaDAO(); // Crear instancia DAO

		try {
			boolean desaparcado = plazaDAO.SalirParking(request.getParameter("matricula"));

			if (desaparcado) {
				System.out.println("Coche desaparcado de la plaza correctamente");
				request.getRequestDispatcher("loginCorrecto.jsp").forward(request, response);
			} else {
				System.out.println("Coche NO desaparcado de la plaza");
				request.setAttribute("errorMessage",
						"Por alguna razon no puedes desaparcar el coche");
				request.getRequestDispatcher("DesaparcarCoche.jsp").forward(request, response);
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