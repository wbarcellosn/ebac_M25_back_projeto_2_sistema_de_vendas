/**
 * @author winic
 */
package mocks;

import main.dao.IProdutoDAO;
import main.domain.Produto;
import main.exceptions.TipoChaveNaoEncontradaException;

import java.util.Collection;

public class ProdutoDAOMock implements IProdutoDAO {

    @Override
    public Boolean cadastrar(Produto entity) throws TipoChaveNaoEncontradaException {
        return true;
    }

    @Override
    public void excluir(String valor) {}

    @Override
    public void alterar(Produto entity) throws TipoChaveNaoEncontradaException {}

    @Override
    public Produto consultar(String valor) {
        Produto produto = new Produto();
        produto.setCodigo(valor);
        return produto;
    }

    @Override
    public Collection<Produto> buscarTodos() {
        return null;
    }
}
