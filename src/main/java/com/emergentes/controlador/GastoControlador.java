package com.emergentes.controlador;

import com.emergentes.dao.GastoDAO;
import com.emergentes.dao.GastoDAOimpl;
import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Gasto;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "GastoControlador", urlPatterns = {"/GastoControlador"})
public class GastoControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            GastoDAO dao = new GastoDAOimpl();
            UsuarioDAO daoUsuario = new UsuarioDAOimpl();
            int idgasto;
            List<Usuario> lista_usuarios = null;
            Gasto cli = new Gasto();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";

            switch (action) {
                case "add":
                    lista_usuarios = daoUsuario.getAll();
                    request.setAttribute("lista_usuarios", lista_usuarios);
                    request.setAttribute("gasto", cli);
                    request.getRequestDispatcher("frmgasto.jsp").forward(request, response);
                    break;
                case "edit":
                    idgasto = Integer.parseInt(request.getParameter("idgasto"));
                    cli = dao.getById(idgasto);
                    //System.out.println(cli);
                    lista_usuarios = daoUsuario.getAll();
                    request.setAttribute("lista_usuarios", lista_usuarios);

                    request.setAttribute("gasto", cli);
                    request.getRequestDispatcher("frmgasto.jsp").forward(request, response);
                    break;
                case "delete":
                    idgasto = Integer.parseInt(request.getParameter("idgasto"));
                    dao.delete(idgasto);
                    response.sendRedirect(request.getContextPath() + "/GastoControlador");
                    break;

                case "view":
                    List<Gasto> lista = dao.getAll();
                    request.setAttribute("gastos", lista);
                    request.getRequestDispatcher("gastos.jsp").forward(request, response);
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
        int idgasto = Integer.parseInt(request.getParameter("idgasto"));
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        String fecha = request.getParameter("fecha");
        String concepto = request.getParameter("concepto");
        Double gasto = Double.parseDouble(request.getParameter("gasto"));
        //  String telefono = request.getParameter("telefono");
        Gasto cli = new Gasto();

        cli.setIdgasto(idgasto);
        cli.setIdusuario(idusuario);
        cli.setFecha(fecha);
        cli.setConcepto(concepto);
        cli.setGasto(gasto);
        // cli.setTelefono(telefono);

        if (idgasto == 0) {
            try {
                GastoDAO dao = new GastoDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath() + "/GastoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        } else {
            try {
                GastoDAO dao = new GastoDAOimpl();
                System.out.println("Datos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath() + "/GastoControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
