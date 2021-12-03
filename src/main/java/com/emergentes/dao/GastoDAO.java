package com.emergentes.dao;

import com.emergentes.modelo.Gasto;
import java.util.List;

public interface GastoDAO {
    public void insert(Gasto gasto) throws Exception;
    public void update(Gasto gasto) throws Exception;
    public void delete(int idcliente) throws Exception;
    public Gasto getById(int idgasto) throws Exception;
    public List<Gasto> getAll() throws Exception;
}
