package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface IVehiculoServicio extends ICRUD<Vehiculo,Integer>{
    List<Vehiculo> findByTipo(Tipo TIPO);
    List<Vehiculo> findByMarca (String marca);
    List<Vehiculo> findByLocalizacion (String localizacion);
    List<Vehiculo> findByUsuarioVehiculo_Id(Integer id_usuario);
    List<Vehiculo> consultaPorPrecio(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    List<Vehiculo> findVehiculosDisponibles(LocalDate fechaActual);

}
