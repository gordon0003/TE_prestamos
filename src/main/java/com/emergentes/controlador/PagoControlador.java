package com.emergentes.controlador;

import com.emergentes.dao.ClienteDAO;
import com.emergentes.dao.ClienteDAOimpl;
import com.emergentes.dao.PagoDAO;
import com.emergentes.dao.PagoDAOimpl;
import com.emergentes.dao.PrestamoDAO;
import com.emergentes.dao.PrestamoDAOimpl;
import com.emergentes.modelo.Cliente;
import com.emergentes.modelo.Pago;
import com.emergentes.modelo.Prestamo;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "PagoControlador", urlPatterns = {"/PagoControlador"})
public class PagoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            PagoDAO dao = new PagoDAOimpl();
            PrestamoDAO daoPrestamo = new PrestamoDAOimpl();
            ClienteDAO daoCliente = new ClienteDAOimpl();
          //  UsuarioDAO daoUsuario = new UsuarioDAOimpl();
            int idpago;
            List<Prestamo> lista_prestamo = null;
            List<Cliente> lista_cliente = null;
           // List<Usuario> lista_usuario = null;
            Pago cli = new Pago();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    lista_prestamo = daoPrestamo.getAll();
                    request.setAttribute("lista_prestamo", lista_prestamo);
                    
                    lista_cliente = daoCliente.getAll();
                    request.setAttribute("lista_cliente", lista_cliente);
                    
                  //  lista_usuario = daoUsuario.getAll();
                  // request.setAttribute("lista_usuario", lista_usuario);
                    
                    request.setAttribute("pago", cli);
                    request.getRequestDispatcher("frmpago.jsp").forward(request, response);
                    break;
                case "edit":
                    idpago = Integer.parseInt(request.getParameter("idpago"));
                    cli = dao.getById(idpago);
                    System.out.println(cli);

                    lista_prestamo = daoPrestamo.getAll();
                    request.setAttribute("lista_prestamo", lista_prestamo);

                    lista_cliente = daoCliente.getAll();
                    request.setAttribute("lista_cliente", lista_cliente);
                    
               //     lista_usuario = daoUsuario.getAll();
                //    request.setAttribute("lista_usuario", lista_usuario);

                    request.setAttribute("pago", cli);
                    request.getRequestDispatcher("frmpago.jsp").forward(request, response);
                    break;
                case "delete":
                    idpago = Integer.parseInt(request.getParameter("idpago"));
                    dao.delete(idpago);
                    response.sendRedirect(request.getContextPath() + "/PagoControlador");
                    break;
                case "view":
                    List<Pago> lista = dao.getAll();
                    request.setAttribute("pagos", lista);
                    request.getRequestDispatcher("pagos.jsp").forward(request, response);
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
        int idpago = Integer.parseInt(request.getParameter("idpago"));
        int idprestamo = Integer.parseInt(request.getParameter("idprestamo"));
        String usuario = request.getParameter("usuario");
        String fecha = request.getParameter("fecha");
        Double cuota = Double.parseDouble(request.getParameter("cuota"));

        Pago cli = new Pago();

        cli.setIdpago(idpago);
        cli.setIdprestamo(idprestamo);
        cli.setUsuario(usuario);
        cli.setFecha(fecha);
        cli.setCuota(cuota);
System.out.println(cli);
        if (idpago == 0) {
            try {
                PagoDAO dao = new PagoDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath() + "/PagoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                PagoDAO dao = new PagoDAOimpl();
                System.out.println("Datos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath() + "/PagoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
