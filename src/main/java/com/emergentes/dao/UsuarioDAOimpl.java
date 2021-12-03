package com.emergentes.dao;

import com.emergentes.modelo.Usuario;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDAOimpl extends ConexionDB implements UsuarioDAO {

    @Override
    public void insert(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into usuarios (nombre, direccion, telefono, login, clave) values (?, ?, ?, ?, ?)");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDireccion());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getClave());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Usuario usuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE usuarios set nombre = ?, direccion = ?, telefono = ?, login = ?, clave = ? where idusuario = ?");
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDireccion());
            ps.setString(3, usuario.getTelefono());
            ps.setString(4, usuario.getLogin());
            ps.setString(5, usuario.getClave());
            ps.setInt(6, usuario.getIdusuario());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void delete(int idusuario) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM usuarios WHERE idusuario = ?");
            ps.setInt(1,idusuario);
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public Usuario getById(int idusuario) throws Exception {
        Usuario cli = new Usuario();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios WHERE idusuario = ? limit 1");
            ps.setInt(1, idusuario);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdusuario(rs.getInt("idusuario"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setLogin(rs.getString("login"));
                cli.setClave(rs.getString("clave"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Usuario> getAll() throws Exception {
        List<Usuario> lista = null;
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM usuarios");
            ResultSet rs = ps.executeQuery();

            lista = new ArrayList<Usuario>();
            while (rs.next()) {
                Usuario cli = new Usuario();
                System.out.println("Fila ...");
                cli.setIdusuario(rs.getInt("idusuario"));
                cli.setNombre(rs.getString("nombre"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                cli.setLogin(rs.getString("login"));
                cli.setClave(rs.getString("clave"));
                lista.add(cli);
            }
            rs.close();
            ps.close();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return lista;
    }

}
