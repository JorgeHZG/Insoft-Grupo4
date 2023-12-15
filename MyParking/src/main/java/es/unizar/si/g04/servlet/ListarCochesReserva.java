package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.util.List;
import java.util.Vector;  
import es.unizar.si.g04.model.VehiculoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListarCochesReserva extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = (String) session.getAttribute("dni");

        VehiculoDAO v = new VehiculoDAO();
        List<String> vehiculos = v.obtenerVehiculosUsuario(dni);

        request.setAttribute("vector_vehiculos", vehiculos.toArray(new String[0]));

        request.getRequestDispatcher("reservaPlaza.jsp").forward(request, response);
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }
}
