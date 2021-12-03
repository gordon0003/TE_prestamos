package com.emergentes.dao;

import com.emergentes.modelo.Prestamo;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PrestamoDAOimpl extends ConexionDB implements PrestamoDAO{

    @Override
    public void insert(Prestamo prestamo) throws Exception {
       try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into prestamos (idcliente, usuario, fprestamo, monto, interes, saldo, formapago, fpago, plazo, fplazo) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ? )");
            ps.setInt(1, prestamo.getIdcliente());
            ps.setInt(2, prestamo.getUsuario());
            ps.setString(3, prestamo.getFprestamo());
            ps.setDouble(4, prestamo.getMonto());
            ps.setDouble(5, prestamo.getInteres());
            ps.setDouble(6, prestamo.getSaldo());
            ps.setString(7, prestamo.getFormapago());
            ps.setString(8, prestamo.getFpago());
            ps.setString(9, prestamo.getPlazo());
            ps.setString(10, prestamo.getFplazo());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        } 
    }

    @Override
    public void update(Prestamo prestamo) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE prestamos set idcliente = ?, usuario = ?, fprestamo = ?, monto = ?, interes = ?, saldo = ?, formapago = ?, fpago = ?, plazo = ?, fplazo = ? where idprestamo = ?");
            ps.setInt(1, prestamo.getIdcliente());
            ps.setInt(2, prestamo.getUsuario());
            ps.setString(3, prestamo.getFprestamo());            
            ps.setDouble(4, prestamo.getMonto()); 
            ps.setDouble(5, prestamo.getInteres());
            ps.setDouble(6, prestamo.getSaldo());
            ps.setString(7, prestamo.getFormapago());
            ps.setString(8, prestamo.getFpago());
            ps.setString(9, prestamo.getPlazo());
            ps.setString(10, prestamo.getFplazo());
            ps.setInt(11, prestamo.getIdprestamo());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        } 
    }

    @Override
    public void delete(int idprestamo) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM prestamos WHERE idprestamo = ?");
            ps.setInt(1,idprestamo);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Prestamo getById(int idprestamo) throws Exception {
       Prestamo cli = new Prestamo();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM prestamos WHERE idprestamo = ? limit 1");
            ps.setInt(1, idprestamo);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdprestamo(rs.getInt("idprestamo"));
                cli.setIdcliente(rs.getInt("idcliente"));
                cli.setUsuario(rs.getInt("usuario"));
                cli.setFprestamo(rs.getString("fprestamo"));
                cli.setMonto(rs.getDouble("monto"));
                cli.setInteres(rs.getDouble("interes"));
                cli.setSaldo(rs.getDouble("saldo"));
                cli.setFormapago(rs.getString("formapago"));
                cli.setFpago(rs.getString("fpago"));
                cli.setPlazo(rs.getString("plazo"));
                cli.setFplazo(rs.getString("fplazo"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Prestamo> getAll() throws Exception {
       List<Prestamo> lista = null;
        try{
            this.conectar();
            String sql = "SELECT p.idprestamo, p.idcliente, c.nombre as cliente, p.usuario, u.nombre as usuarios, p.fprestamo , p.monto, p.interes, p.saldo, p.formapago, p.fpago, p.plazo, p.fplazo FROM prestamos p INNER JOIN clientes c ON p.idcliente=c.idcliente INNER JOIN usuarios u ON p.usuario=u.idusuario";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Prestamo>();
            while(rs.next()){
                Prestamo cli = new Prestamo();
                System.out.println("Fila ...");
                cli.setIdprestamo(rs.getInt("idprestamo"));
                cli.setIdcliente(rs.getInt("idcliente"));
                cli.setUsuario(rs.getInt("usuario"));
                cli.setFprestamo(rs.getString("fprestamo"));
                cli.setMonto(rs.getDouble("Monto"));
                cli.setInteres(rs.getDouble("interes"));
                cli.setSaldo(rs.getDouble("saldo"));
                cli.setFormapago(rs.getString("formapago"));
                cli.setFpago(rs.getString("fpago"));
                cli.setPlazo(rs.getString("plazo"));
                cli.setFplazo(rs.getString("fplazo"));
                cli.setCliente(rs.getString("cliente"));
                cli.setUsuarios(rs.getString("usuarios"));
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
