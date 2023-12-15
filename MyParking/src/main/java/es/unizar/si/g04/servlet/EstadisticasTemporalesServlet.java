package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import es.unizar.si.g04.model.AdminDAO;

/**
 * Servlet implementation class GananciasAdminServlet
 */
public class EstadisticasTemporalesServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public EstadisticasTemporalesServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminDAO a = new AdminDAO();
		int horaPunta;
		int horaBaja;
		float promedioEstancia;
		try {
			horaPunta = a.obtenerHoraPunta();
			horaBaja = a.obtenerHoraBaja();
			promedioEstancia = a.obtenerPromedioEstancia();

			System.out.println("Hora punta: " + String.valueOf(horaPunta));
			System.out.println("Hora baja: " + String.valueOf(horaBaja));
			System.out.println("Promedio estancia: " + String.valueOf(promedioEstancia));

			request.setAttribute("hora_punta", horaPunta);
			request.setAttribute("hora_baja", horaBaja);
			request.setAttribute("hora_puntaS", horaPunta + 1);
			request.setAttribute("hora_bajaS", horaBaja + 1);
			request.setAttribute("promedio_estancia", promedioEstancia);

			request.getRequestDispatcher("InformacionDetallada.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
