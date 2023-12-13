package es.unizar.si.g04.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import es.unizar.si.g04.model.PlazaDAO;
import es.unizar.si.g04.model.PlazaVO;

/**
 * Servlet implementation class OcupacionServlet
 */
public class OcupacionServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public OcupacionServlet() {
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		PlazaDAO plazaDAO[] = { new PlazaDAO(), new PlazaDAO(), new PlazaDAO(), new PlazaDAO(), new PlazaDAO() };
		int lT, lM, lE, lMin, lF,
				tT, tM, tE, tMin, tF;

		int plaza[] = new int[5];
		float percent[] = new float[5];
		int free[] = new int[5];

		try {
			plaza = plazaDAO[0].ObtenerOcupacion("Turismo");
			tT = plaza[0];
			lT = plaza[1];
			percent[0] = (lT / tT) * 100;
			free[0] = tT - lT;
			request.setAttribute("tT", tT);
			request.setAttribute("lT", lT);
			request.setAttribute("pT", percent[0]);
			request.setAttribute("oT", free[0]);

			plaza = plazaDAO[1].ObtenerOcupacion("Moto");
			tM = plaza[0];
			lM = plaza[1];
			percent[1] = (lM / tM) * 100;
			free[1] = tM - lM;

			request.setAttribute("tM", tM);
			request.setAttribute("lM", lM);
			request.setAttribute("pM", percent[1]);
			request.setAttribute("oM", free[1]);

			plaza = plazaDAO[2].ObtenerOcupacion("Vehiculo electrico");
			tE = plaza[0];
			lE = plaza[1];
			percent[2] = (lE / tE) * 100;
			free[2] = tE - lE;

			request.setAttribute("tE", tE);
			request.setAttribute("lE", lE);
			request.setAttribute("pE", percent[2]);
			request.setAttribute("oE", free[2]);

			plaza = plazaDAO[3].ObtenerOcupacion("Minusvalidos");
			tMin = plaza[0];
			lMin = plaza[1];
			percent[3] = (lMin / tMin) * 100;
			free[3] = tMin - lMin;

			request.setAttribute("tMin", tMin);
			request.setAttribute("lMin", lMin);
			request.setAttribute("pMin", percent[3]);
			request.setAttribute("oMin", free[3]);

			plaza = plazaDAO[4].ObtenerOcupacion("Familiar");
			tF = plaza[0];
			lF = plaza[1];
			percent[4] = (lF / tF) * 100;
			free[4] = tF - lF;

			request.setAttribute("tF", tF);
			request.setAttribute("lF", lF);
			request.setAttribute("pF", percent[4]);
			request.setAttribute("oF", free[4]);

			request.getRequestDispatcher("Estadisticas.jsp").forward(request, response);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		doGet(request, response);
	}

}
