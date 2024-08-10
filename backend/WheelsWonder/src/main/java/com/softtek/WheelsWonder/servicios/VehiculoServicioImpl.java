package com.softtek.WheelsWonder.servicios;


import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import com.softtek.WheelsWonder.repositorios.IVehiculoRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class VehiculoServicioImpl extends CRUDImpl<Vehiculo,Integer> implements IVehiculoServicio{

    @Autowired
    private IVehiculoRepositorio repo;

    @Override
    protected IGenericoRepositorio<Vehiculo, Integer> getRepo() {
        return repo;
    }

    /*@Override
    public Page<Vehiculo> consultarVehiculosPaginados(Pageable pageable) {
        return repo.findAll(pageable);
    }*/

    @Override
    public List<Vehiculo> findByTipo(Tipo TIPO) {
        return repo.findByTipo(TIPO);
    }

    @Override
    public List<Vehiculo> findByMarca(String marca) {
        return repo.findByMarca(marca);
    }

    @Override
    public List<Vehiculo> findByLocalizacion(String localizacion) {
        return repo.findByLocalizacion(localizacion);
    }


    @Override
    public List<Vehiculo> findByUsuarioVehiculo_Id(Integer id_usuario) {
        return repo.findByUsuarioVehiculo_Id(id_usuario);
    }

    @Override
    public List<Vehiculo> consultaPorPrecio(double minPrice, double maxPrice) {
        return repo.consultaPorPrecio(minPrice,maxPrice);
    }

    @Override
    public List<Vehiculo> findVehiculosDisponibles(LocalDate fechaActual) {
        return repo.findVehiculosDisponibles(fechaActual);
    }

}
