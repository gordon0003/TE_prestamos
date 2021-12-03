
package com.emergentes.controlador;

import com.emergentes.dao.UsuarioDAO;
import com.emergentes.dao.UsuarioDAOimpl;
import com.emergentes.modelo.Usuario;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet(name = "UsuarioControlador", urlPatterns = {"/UsuarioControlador"})
public class UsuarioControlador extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            UsuarioDAO dao = new UsuarioDAOimpl();
            int idusuario;
            Usuario cli = new Usuario();
            String action = (request.getParameter("action") != null) ? request.getParameter("action") : "view";
            
            switch(action){
                case "add":
                    request.setAttribute("usuario", cli);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;
                case "edit":
                    idusuario = Integer.parseInt(request.getParameter("idusuario"));
                    cli = dao.getById(idusuario);
                    System.out.println(cli);
                    request.setAttribute("usuario", cli);
                    request.getRequestDispatcher("frmusuario.jsp").forward(request, response);
                    break;     
                case "delete":
                    idusuario = Integer.parseInt(request.getParameter("idusuario"));
                    dao.delete(idusuario);
                    response.sendRedirect(request.getContextPath()+"/UsuarioControlador");
                    break;
                case "view":
                    List<Usuario> lista = dao.getAll();
                    request.setAttribute("usuarios", lista);
                    request.getRequestDispatcher("usuarios.jsp").forward(request, response);
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
        int idusuario = Integer.parseInt(request.getParameter("idusuario"));
        String nombre = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        String telefono = request.getParameter("telefono");
        String login = request.getParameter("login");
        String clave = request.getParameter("clave");
        
        Usuario cli = new Usuario();
        
        cli.setIdusuario(idusuario);
        cli.setNombre(nombre);
        cli.setDireccion(direccion);
        cli.setTelefono(telefono);
        cli.setLogin(login);
        cli.setClave(clave);
        
        if (idusuario == 0){
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                dao.insert(cli);
                response.sendRedirect(request.getContextPath()+"/UsuarioControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
        else{
            try {
                UsuarioDAO dao = new UsuarioDAOimpl();
                System.out.println("Datos" + cli.toString());
                dao.update(cli);
                response.sendRedirect(request.getContextPath()+"/UsuarioControlador");
            } catch (Exception ex) {
                System.out.println("Error " + ex.getMessage());
            }
        }
    }
}
