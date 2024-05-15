package main.service.generic;

import main.dao.Persistente;
import main.exceptions.TipoChaveNaoEncontradaException;

import java.io.Serializable;
import java.util.Collection;

public interface IGenericService <T extends Persistente, E extends Serializable> {

    Boolean cadastrar(T entity) throws TipoChaveNaoEncontradaException;

    void excluir(E valor);

    void alterar(T entity) throws TipoChaveNaoEncontradaException;

    T consultar(E valor);

    Collection<T> buscarTodos();
}
