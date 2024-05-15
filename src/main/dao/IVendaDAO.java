package main.dao;

import main.dao.generic.IGenericDAO;
import main.domain.Venda;
import main.exceptions.TipoChaveNaoEncontradaException;

public interface IVendaDAO extends IGenericDAO<Venda, String> {

    void finalizarVenda(Venda venda) throws TipoChaveNaoEncontradaException;
}
