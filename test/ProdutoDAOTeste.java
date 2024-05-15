import main.dao.IProdutoDAO;
import main.dao.ProdutoDAO;
import main.domain.Produto;
import main.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.Collection;

/**
 * @author winic
 */

public class ProdutoDAOTeste {

    private IProdutoDAO produtoDao;

    private Produto produto;

    public ProdutoDAOTeste() {
        produtoDao = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
        produtoDao.cadastrar(produto);
    }

    @Test
    public void pesquisar() {
        Produto produto = this.produtoDao.consultar(this.produto.getCodigo());
        Assert.assertNotNull(produto);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        produto.setCodigo("A2");
        Boolean retorno = produtoDao.cadastrar(produto);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluir() {
        produtoDao.excluir(produto.getCodigo());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        produto.setNome("Produto 2");
        produtoDao.alterar(produto);

        Assert.assertEquals("Produto 2", produto.getNome());
    }

    @Test
    public void buscarTodos() {
        Collection<Produto> list = produtoDao.buscarTodos();
        Assert.assertNotNull(list);
        Assert.assertEquals(2, list.size());
    }
}
