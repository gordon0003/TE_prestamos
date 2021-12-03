package com.emergentes.dao;

import com.emergentes.modelo.Cliente;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAOimpl extends ConexionDB implements ClienteDAO{

    @Override
    public void insert(Cliente cliente) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into clientes (nombre, cedula, direccion, telefono) values (?, ?, ?, ?)");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCedula());
            ps.setString(3, cliente.getDireccion());
            ps.setString(4, cliente.getTelefono());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        } 
    }

    @Override
    public void update(Cliente cliente) throws Exception {
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE clientes set nombre = ?, cedula = ?, direccion = ?, telefono = ? where idcliente = ?");
            ps.setString(1, cliente.getNombre());
            ps.setString(2, cliente.getCedula());            
            ps.setString(3, cliente.getDireccion()); 
            ps.setString(4, cliente.getTelefono());
            ps.setInt(5,cliente.getIdcliente());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }   
    }

    @Override
    public void delete(int idcliente) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM clientes WHERE idcliente = ?");
            ps.setInt(1,idcliente);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }    
    }

    @Override
    public Cliente getById(int idcliente) throws Exception {
        Cliente cli = new Cliente();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM clientes WHERE idcliente = ? limit 1");
            ps.setInt(1, idcliente);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdcliente(rs.getInt("idcliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCedula(rs.getString("cedula"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli; 
    }

    @Override
    public List<Cliente> getAll() throws Exception {
       List<Cliente> lista = null;
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM clientes");
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Cliente>();
            while(rs.next()){
                Cliente cli = new Cliente();
                System.out.println("Fila ...");
                cli.setIdcliente(rs.getInt("idcliente"));
                cli.setNombre(rs.getString("nombre"));
                cli.setCedula(rs.getString("cedula"));
                cli.setDireccion(rs.getString("direccion"));
                cli.setTelefono(rs.getString("telefono"));
                lista.add(cli);
            }
            rs.close();
            ps.close();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }  
        return lista;  
    }
    
}
