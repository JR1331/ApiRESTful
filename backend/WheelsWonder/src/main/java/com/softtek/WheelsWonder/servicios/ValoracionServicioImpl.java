package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Valoracion;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import com.softtek.WheelsWonder.repositorios.IValoracionRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ValoracionServicioImpl extends CRUDImpl<Valoracion,Integer> implements IValoracionServicio{

    @Autowired
    private IValoracionRepositorio repo;

    @Override
    protected IGenericoRepositorio<Valoracion, Integer> getRepo() {
        return repo;
    }


    @Override
    public long countByUsuarioValoracion_Id(Integer id_usuario) {
        return repo.countByUsuarioValoracion_Id(id_usuario);
    }

    @Override
    public Double findAveragePuntuacionByUsuarioValoracion_Id(Integer id_usuario) {
        return repo.findAveragePuntuacionByUsuarioValoracion_Id(id_usuario);
    }
}
