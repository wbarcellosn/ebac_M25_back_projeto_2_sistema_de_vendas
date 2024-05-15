import main.dao.*;
import main.domain.Cliente;
import main.domain.Produto;
import main.domain.Venda;
import main.exceptions.TipoChaveNaoEncontradaException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.time.Instant;

/**
 * @author winic
 */

public class VendaDAOTeste {

    private IVendaDAO vendaDao;

    private IClienteDAO clienteDao;

    private IProdutoDAO produtoDao;

    private Cliente cliente;

    private Produto produto;

    public VendaDAOTeste() {
        vendaDao = new VendaDAO();
        clienteDao = new ClienteDAO();
        produtoDao = new ProdutoDAO();
    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        this.cliente = cadastrarCliente();
        this.produto = cadastrarProduto("A1", BigDecimal.TEN);
    }


    @Test
    public void pesquisar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A1");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Venda vendaConsultada = vendaDao.consultar(venda.getCodigo());
        Assert.assertNotNull(vendaConsultada);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
    }

    @Test
    public void salvar() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A2");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(20));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }


    @Test
    public void cancelarVenda() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A3";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        venda.setStatus(Venda.Status.CANCELADA);
        vendaDao.alterar(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(codigoVenda, vendaConsultada.getCodigo());
        Assert.assertEquals(Venda.Status.CANCELADA, vendaConsultada.getStatus());
    }

    @Test
    public void adicionarMaisProdutosDoMesmo() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A4";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(produto, 1);

        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(30));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void adicionarMaisProdutosDiferentes() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A5";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);

        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(70));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void salvarProdutoExistente() throws TipoChaveNaoEncontradaException {
        Venda venda = criarVenda("A6");
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);

        Boolean retorno1 = vendaDao.cadastrar(venda);
        Assert.assertFalse(retorno1);
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A7";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(70));


        vendaConsultada.removerProduto(prod, 1);
        Assert.assertEquals(2, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(20));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerApenasUmProduto() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A8";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(70));


        vendaConsultada.removerProduto(prod, 1);
        Assert.assertEquals(2, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(20));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void removerTodosProdutos() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A9";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        Produto prod = cadastrarProduto(codigoVenda, BigDecimal.valueOf(50));
        Assert.assertNotNull(prod);
        Assert.assertEquals(codigoVenda, prod.getCodigo());

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        vendaConsultada.adicionarProduto(prod, 1);
        Assert.assertEquals(3, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(70));


        vendaConsultada.removerTodosProdutos();
        Assert.assertEquals(0, (int) venda.getQuantidadeTotalProdutos());
        Assert.assertEquals(venda.getValorTotal(), BigDecimal.valueOf(0));
        Assert.assertEquals(venda.getStatus(), Venda.Status.INICIADA);
    }

    @Test
    public void finalizarVenda() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A10";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);

        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        Assert.assertEquals(venda.getStatus(), vendaConsultada.getStatus());
    }

    @Test(expected = UnsupportedOperationException.class)
    public void tentarAdicionarProdutosVendaFinalizada() throws TipoChaveNaoEncontradaException {
        String codigoVenda = "A11";
        Venda venda = criarVenda(codigoVenda);
        Boolean retorno = vendaDao.cadastrar(venda);
        Assert.assertTrue(retorno);
        Assert.assertNotNull(venda);
        Assert.assertEquals(codigoVenda, venda.getCodigo());

        vendaDao.finalizarVenda(venda);
        Venda vendaConsultada = vendaDao.consultar(codigoVenda);
        Assert.assertEquals(venda.getCodigo(), vendaConsultada.getCodigo());
        Assert.assertEquals(venda.getStatus(), vendaConsultada.getStatus());

        vendaConsultada.adicionarProduto(this.produto, 1);

    }

    private Produto cadastrarProduto(String codigo, BigDecimal valor) throws TipoChaveNaoEncontradaException {
        Produto produto = new Produto();
        produto.setCodigo(codigo);
        produto.setDescricao("Produto 1");
        produto.setNome("Produto 1");
        produto.setValor(valor);
        produtoDao.cadastrar(produto);
        return produto;
    }

    private Cliente cadastrarCliente() throws TipoChaveNaoEncontradaException {
        Cliente cliente = new Cliente();
        cliente.setCpf(12345678900L);
        cliente.setNome("Winicius");
        cliente.setTelefone(27987654321L);
        cliente.setEndereco("Rua teste");
        cliente.setNumero(60);
        cliente.setCidade("Teste City");
        cliente.setEstado("TS");
        clienteDao.cadastrar(cliente);
        return cliente;
    }

    private Venda criarVenda(String codigo) {
        Venda venda = new Venda();
        venda.setCodigo(codigo);
        venda.setDataVenda(Instant.now());
        venda.setCliente(this.cliente);
        venda.setStatus(Venda.Status.INICIADA);
        venda.adicionarProduto(this.produto, 2);
        return venda;
    }


}
