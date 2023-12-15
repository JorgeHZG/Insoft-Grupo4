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
public class GananciasAdminServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public GananciasAdminServlet() {
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		AdminDAO a = new AdminDAO();
		float dinero;
		try {
			dinero = a.obtenerRendimientoEconomico();
			System.out.println("Total ganancias: " + dinero);
			request.setAttribute("ganancias", dinero);

			request.getRequestDispatcher("GananciasAdmin.jsp").forward(request, response);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doPost(request, response);
	}

}
