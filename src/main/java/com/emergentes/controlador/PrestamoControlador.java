
package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.PrestamoDAO;
import com.emergentes.dao.PrestamoDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Prestamo;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PrestamoControlador", urlPatterns = {"/PrestamoControlador"})
public class PrestamoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PrestamoDAO dao = new PrestamoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
            UsuarioDAO daoUsuario = new UsuarioDAOimpl();
            int idprestamo;
            List<Cliente> lista_cliente = null;
            List<Usuario> lista_usuario = null;
            Prestamo cli = new Prestamo();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    lista_cliente = daoCliente.getAll();
                   request.setAttribute("lista_cliente", lista_cliente);
                    lista_usuario = daoUsuario.getAll();
                   request.setAttribute("lista_usuario", lista_usuario);
                    request.setAttribute("prestamo", cli);
                    request.getRequestDispatcher("frmprestamo.jsp").forward(request, response);
                    break;
                case "edit":
                    idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
                    cli = dao.getById(idprestamo);
                    System.out.println(cli);
                    
                    lista_cliente = daoCliente.getAll();
                    request.setAttribute("lista_cliente", lista_cliente);
                    lista_usuario = daoUsuario.getAll();
                    request.setAttribute("lista_usuario", lista_usuario);
                    
                    request.setAttribute("prestamo", cli);
                    request.getRequestDispatcher("frmprestamo.jsp").forward(request, response);
                    break;     
                case "delete":
                    idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
                    dao.delete(idprestamo);
                    response.sendRedirect(request.getContextPath()+"/PrestamoControlador");
                    break;
                case "view":
                    List<Prestamo> lista = dao.getAll();
                    request.setAttribute("prestamos", lista);
                    request.getRequestDispatcher("prestamos.jsp").forward(request, response);
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
        int idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
        int idcliente = Integer.parseInt(request.getParameter("idcliente"));
        int usuario = Integer.parseInt(request.getParameter("usuario"));
        String fprestamo = request.getParameter("fprestamo");
        Double monto = Double.parseDouble(request.getParameter("monto"));
        Double interes = Double.parseDouble(request.getParameter("interes"));
        Double saldo = Double.parseDouble(request.getParameter("saldo"));
        String formapago = request.getParameter("formapago");
        String fpago = request.getParameter("fpago");
        String plazo = request.getParameter("plazo");
        String fplazo = request.getParameter("fplazo");
        
        Prestamo cli = new Prestamo();
        
        cli.setIdprestamo(idprestamo);
        cli.setIdcliente(idcliente);
        cli.setUsuario(usuario);
        cli.setFprestamo(fprestamo);
        cli.setMonto(monto);
        cli.setInteres(interes);
        cli.setSaldo(saldo);
        cli.setFormapago(formapago);
        cli.setFpago(fpago);
        cli.setPlazo(plazo);
        cli.setFplazo(fplazo);
        
        if (idprestamo == 0){
            try {
                PrestamoDAO dao = new PrestamoDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath()+"/PrestamoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                PrestamoDAO dao = new PrestamoDAOimpl();
                System.out.println("Datos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath()+"/PrestamoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
