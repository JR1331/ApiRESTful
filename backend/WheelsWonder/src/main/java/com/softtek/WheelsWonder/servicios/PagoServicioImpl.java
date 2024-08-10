package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Pago;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import com.softtek.WheelsWonder.repositorios.IPagoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PagoServicioImpl extends CRUDImpl<Pago,Integer> implements IPagoServicio{

    @Autowired
    private IPagoRepositorio repo;

    @Override
    protected IGenericoRepositorio<Pago, Integer> getRepo() {
        return repo;
    }
}
