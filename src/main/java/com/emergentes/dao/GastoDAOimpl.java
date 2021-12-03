package com.emergentes.dao;

import com.emergentes.modelo.Gasto;
import com.emergentes.utiles.ConexionDB;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class GastoDAOimpl extends ConexionDB implements GastoDAO{

    @Override
    public void insert(Gasto gasto) throws Exception {
        try {
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("INSERT into gastos (idusuario, fecha, concepto, gasto) values (?, ?, ?, ?)");
            ps.setInt(1, gasto.getIdusuario());
            ps.setString(2, gasto.getFecha());
            ps.setString(3, gasto.getConcepto());
            ps.setDouble(4, gasto.getGasto());
            ps.executeUpdate();
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
    }

    @Override
    public void update(Gasto gasto) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("UPDATE gastos set idusuario= ?, fecha = ?, concepto = ?, gasto = ? where idgasto = ?");
            ps.setInt(1, gasto.getIdusuario());
            ps.setString(2, gasto.getFecha());
            ps.setString(3, gasto.getConcepto());            
            ps.setDouble(4, gasto.getGasto()); 
            ps.setInt(5,gasto.getIdgasto());
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }
    }

    @Override
    public void delete(int idgasto) throws Exception {
        try{
            this.conectar();
            PreparedStatement ps = this.conn.prepareStatement("DELETE FROM gastos WHERE idgasto = ?");
            ps.setInt(1,idgasto);
            ps.executeUpdate();
        }catch(Exception e){
            throw e;
        }finally{
            this.desconectar();
        }  
    }

    @Override
    public Gasto getById(int idgasto) throws Exception {
        Gasto cli = new Gasto();
        try {

            this.conectar();

            PreparedStatement ps = this.conn.prepareStatement("SELECT * FROM gastos WHERE idgasto = ? limit 1");
            ps.setInt(1, idgasto);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                cli.setIdgasto(rs.getInt("idgasto"));
                cli.setIdusuario(rs.getInt("idusuario"));
                cli.setFecha(rs.getString("fecha"));
                cli.setConcepto(rs.getString("concepto"));
                cli.setGasto(rs.getDouble("gasto"));
            }
        } catch (Exception e) {
            throw e;
        } finally {
            this.desconectar();
        }
        return cli; 
    }

    @Override
    public List<Gasto> getAll() throws Exception {
        List<Gasto> lista = null;
        try{
            this.conectar();
            String sql = "SELECT g.idgasto, g.idusuario, u.nombre as usuario, g.fecha, g.concepto, g.gasto FROM gastos g INNER JOIN usuarios u ON g.idusuario=u.idusuario";
//"select g.*,u.nombre as usuarios from gastos g left join usuarios u on g.idusuario = u.idusuario;";
                    //"select g.*,u.nombre as usuarios from gastos g";
            //sql += "left join usuarios u on g.idusuario = u.idusuario";
            PreparedStatement ps = this.conn.prepareStatement(sql);
            ResultSet rs = ps.executeQuery();
            
            lista = new ArrayList<Gasto>();
            while(rs.next()){
                Gasto cli = new Gasto();
                cli.setIdgasto(rs.getInt("idgasto"));
                cli.setIdusuario(rs.getInt("idusuario"));
                cli.setFecha(rs.getString("fecha"));
                cli.setConcepto(rs.getString("concepto"));
                cli.setGasto(rs.getDouble("gasto"));
                cli.setUsuario(rs.getString("usuario"));
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
