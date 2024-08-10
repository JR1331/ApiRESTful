package com.softtek.WheelsWonder.servicios;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ICRUD <T,ID>{
    T crear(T t);
    T modificar(T t);
    void eliminar(ID id);

    T consultaUno(ID id);
    List<T> consultarTodos();
    //Page<T> consultarPagina(Pageable pageable);
}