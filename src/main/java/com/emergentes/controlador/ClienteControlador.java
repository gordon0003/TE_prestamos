
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.modelo.Cliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "ClienteControlador", urlPatterns = {"/ClienteControlador"})
public class ClienteControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            ClienteDAO dao = new ClienteDAOimpl();
            int idcliente;
            Cliente cli = new Cliente();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("cliente", cli);
                    request.getRequestDispatcher("frmcliente.jsp").forward(request, response);
                    break;
                case "edit":
                    idcliente = Integer.parseInt(request.getParameter("idcliente"));
                    cli = dao.getById(idcliente);
                    System.out.println(cli);
                    request.setAttribute("cliente", cli);
                    request.getRequestDispatcher("frmcliente.jsp").forward(request, response);
                    break;     
                case "delete":
                    idcliente = Integer.parseInt(request.getParameter("idcliente"));
                    dao.delete(idcliente);
                    response.sendRedirect(request.getContextPath()+"/ClienteControlador");
                    break;
                case "view":
                    List<Cliente> lista = dao.getAll();
                    request.setAttribute("clientes", lista);
                    request.getRequestDispatcher("clientes.jsp").forward(request, response);
                default:
                    break;
            }
        } catch (Exception ex) {
            System.out.println("Error " + ex.getMessage());
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        String nombre = request.getParameter("nombre");
        String cedula = request.getParameter("cedula");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        
        Cliente cli = new Cliente();
        
        cli.setIdcliente(idcliente);
        cli.setNombre(nombre);
        cli.setCedula(cedula);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        
        if (idcliente == 0){
            try {
                ClienteDAO dao = new ClienteDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath()+"/ClienteControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                ClienteDAO dao = new ClienteDAOimpl();
                System.out.println("Datos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath()+"/ClienteControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
