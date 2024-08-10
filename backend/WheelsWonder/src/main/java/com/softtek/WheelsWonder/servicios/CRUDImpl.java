package com.softtek.WheelsWonder.servicios;

import com.softtek.WheelsWonder.modelo.Vehiculo;
import com.softtek.WheelsWonder.repositorios.IGenericoRepositorio;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public abstract class CRUDImpl <T,ID> implements ICRUD<T,ID> {
    protected abstract IGenericoRepositorio<T,ID> getRepo();

    @Override
    public T crear(T t) {
        return getRepo().save(t);
    }

    @Override
    public T modificar(T t) {
        return getRepo().save(t);
    }

    @Override
    public void eliminar(ID id) {
        getRepo().deleteById(id);
    }

    @Override
    public T consultaUno(ID id) {
        return getRepo().findById(id).orElse(null);
    }

    @Override
    public List<T> consultarTodos() {
        return getRepo().findAll();
    }

    /*@Override
    public Page<T> consultarPagina(Pageable pageable){
        return getRepo().findAll(pageable);
    }

    public abstract Page<Vehiculo> consultarVehiculosPaginados(Pageable pageable);*/
}
