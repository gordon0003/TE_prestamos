
package com.emergentes.dao;

import com.emergentes.modelo.Prestamo;
import java.util.List;

public interface PrestamoDAO {
    public void insert(Prestamo prestamo) throws Exception;
    public void update(Prestamo prestamo) throws Exception;
    public void delete(int idprestamo) throws Exception;
    public Prestamo getById(int idprestamo) throws Exception;
    public List<Prestamo> getAll() throws Exception;
}
