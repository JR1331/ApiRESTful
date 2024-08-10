package com.softtek.WheelsWonder.repositorios;

import com.softtek.WheelsWonder.modelo.Tipo;
import com.softtek.WheelsWonder.modelo.Vehiculo;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;


public interface IVehiculoRepositorio extends IGenericoRepositorio<Vehiculo, Integer> {
    List<Vehiculo> findByTipo(Tipo TIPO);
    List<Vehiculo> findByMarca(String marca);
    List<Vehiculo> findByLocalizacion(String localizacion);
    List<Vehiculo> findByUsuarioVehiculo_Id(Integer id_usuario);
    @Query("SELECT v FROM Vehiculo v WHERE v.precio BETWEEN :minPrice AND :maxPrice")
    List<Vehiculo> consultaPorPrecio(@Param("minPrice") double minPrice, @Param("maxPrice") double maxPrice);
    @Query(value="SELECT v.* FROM vehiculos v WHERE v.id NOT IN (SELECT a.id_vehiculo FROM alquileres a WHERE a.fecha_inicio <= :fechaActual AND a.fecha_fin >= :fechaActual)", nativeQuery = true)
    List<Vehiculo> findVehiculosDisponibles(LocalDate fechaActual);
}
