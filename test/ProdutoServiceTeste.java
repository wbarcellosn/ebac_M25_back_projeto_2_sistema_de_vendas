import main.dao.IProdutoDAO;
import main.domain.Produto;
import main.exceptions.TipoChaveNaoEncontradaException;
import main.service.IProdutoService;
import main.service.ProdutoService;
import mocks.ProdutoDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;

/**
 * @author winic
 */

public class ProdutoServiceTeste {

    private IProdutoService produtoService;

    private Produto produto;

    public ProdutoServiceTeste() {
        IProdutoDAO dao = new ProdutoDAOMock();
        produtoService = new ProdutoService(dao);
    }

    @Before
    public void init() {
        produto = new Produto();
        produto.setCodigo("A1");
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(BigDecimal.TEN);
    }

    @Test
    public void pesquisar() {
        Produto produtor = this.produtoService.consultar(produto.getCodigo());
        Assert.assertNotNull(produtor);
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        Boolean retorno = produtoService.cadastrar(produto);
        Assert.assertTrue(retorno);
    }

    @Test
    public void excluir() {
        produtoService.excluir(produto.getCodigo());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        produto.setNome("Produto 2");
        produtoService.alterar(produto);

        Assert.assertEquals("Produto 2", produto.getNome());
    }
}
