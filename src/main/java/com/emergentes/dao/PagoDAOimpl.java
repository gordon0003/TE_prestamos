package com.emergentes.dao;

import com.emergentes.modelo.Pago;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class PagoDAOimpl extends ConexionDB implements PagoDAO{

    @Override
    public void insert(Pago pago) throws Exception {
       try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into pagos (idprestamo, usuario, fecha, cuota) values (?, ?, ?, ?)");
            ps.setInt(1, pago.getIdprestamo());
            ps.setString(2, pago.getUsuario());
            ps.setString(3, pago.getFecha());
            ps.setDouble(4, pago.getCuota());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Pago pago) throws Exception {
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE pagos set idprestamo = ?, usuario = ?, fecha = ?, cuota = ? where idpago = ?");
            ps.setInt(1, pago.getIdprestamo());
            ps.setString(2, pago.getUsuario());            
            ps.setString(3, pago.getFecha()); 
            ps.setDouble(4, pago.getCuota());
            ps.setInt(5,pago.getIdpago());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int idpago) throws Exception {
       try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM pagos WHERE idpago = ?");
            ps.setInt(1,idpago);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public Pago getById(int idpago) throws Exception {
       Pago cli = new Pago();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM pagos WHERE idpago = ? limit 1");
            ps.setInt(1, idpago);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdpago(rs.getInt("idpago"));
                cli.setIdprestamo(rs.getInt("idprestamo"));
                cli.setUsuario(rs.getString("usuario"));
                cli.setFecha(rs.getString("fecha"));
                cli.setCuota(rs.getDouble("cuota"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli;
    }

    @Override
    public List<Pago> getAll() throws Exception {
      List<Pago> lista = null;
        try{
            this.conectar();
            String sql = "SELECT g.idpago, g.idprestamo, c.nombre AS cliente, g.usuario, g.fecha, g.cuota FROM pagos g INNER JOIN prestamos p ON g.idprestamo=p.idprestamo INNER JOIN clientes c ON p.idcliente=c.idcliente";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Pago>();
            while(rs.next()){
                Pago cli = new Pago();
                cli.setIdpago(rs.getInt("idpago"));
                cli.setIdprestamo(rs.getInt("idprestamo"));
                cli.setUsuario(rs.getString("usuario"));
                cli.setFecha(rs.getString("fecha"));
                cli.setCuota(rs.getDouble("cuota"));
                cli.setCliente(rs.getString("cliente"));
                System.out.println("Fila: "+ cli.toString());                
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
