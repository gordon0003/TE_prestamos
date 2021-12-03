package com.emergentes.utiles;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Validate extends ConexionDB {
     Connection con = this.conectar();
    PreparedStatement pr;
    
    public boolean checkUser(String login, String clave)
    {
        boolean resultado = false;
        try {
            String sql = "select * from usuarios where login=? and clave=?";
            
            pr = con.prepareStatement(sql);
            pr.setString(1,login);
            pr.setString(2,clave);
            ResultSet rs =  pr.executeQuery();
            resultado = rs.next();
        } catch (SQLException ex) {
            System.out.println(""
                    + "Error al autenticar");
        }
        return resultado;
    }

}
