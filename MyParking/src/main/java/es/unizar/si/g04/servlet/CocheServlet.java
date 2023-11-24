
package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.sql.SQLException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import es.unizar.si.g04.model.VehiculoDAO;
import es.unizar.si.g04.model.VehiculoVO;


public class CocheServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    public CocheServlet() {
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Parámetros del formulario
        String matricula = request.getParameter("Matricula");
        String tipo_vehiculo = request.getParameter("TipoVehiculo");
        String usuario = "";
        Cookie[] cookies = request.getCookies();
        
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if (cookie.getName().equals("usuario")) {
                    usuario = cookie.getValue();
                    break;
                }
            }   
        }
        System.out.println(usuario);

        // Lógica de base de datos
        VehiculoDAO vehiculo = new VehiculoDAO();
    
        if (vehiculo.addVehicle(matricula, usuario, tipo_vehiculo)) {
			 request.setAttribute("confirmMessage", "Vehiculo anadido correctamente");
		     request.getRequestDispatcher("loginCorrecto.jsp").forward(request, response);
		}
		else {
			String mensajeError = "Hubo un error al anadir el coche.";

		    // Construye el script de JavaScript para mostrar el mensaje de error en una ventana emergente
		    String script = "<script type='text/javascript'>"
		                    + "alert('" + mensajeError + "');"
		                    + "window.location.href='paginaAnterior.jsp';"  // Redirige a la página anterior
		                    + "</script>";
		}
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doPost(request, response);

    }
}



