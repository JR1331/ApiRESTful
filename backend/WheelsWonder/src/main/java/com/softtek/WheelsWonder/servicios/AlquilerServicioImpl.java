package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Alquiler;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.repositorios.IAlquilerRepositorio;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AlquilerServicioImpl extends CRUDImpl<Alquiler,Integer> implements IAlquilerServicio{

    @Autowired
    private IAlquilerRepositorio repo;

    @Override
    protected IGenericoRepositorio<Alquiler, Integer> getRepo() {
        return repo;
    }


    @Override
    public List<Alquiler> findByUsuarioAlquiler_Id(Integer id_usuario) {
        return repo.findByusuarioAlquiler_Id(id_usuario);
    }
}
