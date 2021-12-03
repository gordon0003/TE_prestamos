package com.emergentes.dao;

import com.emergentes.modelo.Pago;
import java.util.List;

public interface PagoDAO {
    public void insert(Pago pago) throws Exception;
    public void update(Pago pago) throws Exception;
    public void delete(int idpago) throws Exception;
    public Pago getById(int idpago) throws Exception;
    public List<Pago> getAll() throws Exception;
}
