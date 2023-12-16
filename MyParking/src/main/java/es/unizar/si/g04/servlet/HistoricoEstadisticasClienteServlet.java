package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

import es.unizar.si.g04.model.ClienteDAO;

/**
 * Servlet implementation class HistoricoEstadisticasClienteServlet
 */
public class HistoricoEstadisticasClienteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public HistoricoEstadisticasClienteServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ClienteDAO c = new ClienteDAO();
		try {
			HttpSession session = request.getSession();
			String dni = (String) session.getAttribute("dni");

			int totalMinutos = c.obtenerHistoricoEstancias(dni);

			final long PPM = 3; // precio por minutos en centimos
			float totalEuros = ((totalMinutos * PPM) / 100); // Euros totales
			totalEuros = (float) (Math.round(totalEuros * 1000.0) / 1000.0); // Redondear a tres decimales

			System.out.println("Dinero total gastado por el usuario en el parking: " + String.valueOf(totalEuros));

			request.setAttribute("tiempo", totalMinutos);
			request.setAttribute("dinero", totalEuros);

			request.getRequestDispatcher("DineroUsuario.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}
}
