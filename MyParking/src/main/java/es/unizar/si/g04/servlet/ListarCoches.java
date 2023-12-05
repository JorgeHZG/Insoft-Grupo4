package es.unizar.si.g04.servlet;

import java.io.IOException;
import java.util.List;

import es.unizar.si.g04.model.VehiculoDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class ListarCoches extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        HttpSession session = request.getSession();
        String dni = (String) session.getAttribute("dni");

        VehiculoDAO v = new VehiculoDAO();
        List<String> vehiculos = v.obtenerVehiculosUsuario(dni);

        request.setAttribute("lista_vehiculos", vehiculos);
        request.setAttribute("tam", vehiculos.size());
        
        // Redirige a la página JSP
        request.getRequestDispatcher("reservaPlaza.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doPost(request, response);

    }
}
