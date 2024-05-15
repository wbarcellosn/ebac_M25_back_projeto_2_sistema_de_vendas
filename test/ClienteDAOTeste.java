
import main.dao.IClienteDAO;
import main.domain.Cliente;
import main.exceptions.TipoChaveNaoEncontradaException;
import mocks.ClienteDAOMock;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Collection;

/**
 * @author winic
 */

public class ClienteDAOTeste {

    private IClienteDAO clienteDao;
    private Cliente cliente;

    public ClienteDAOTeste() {
        clienteDao = new ClienteDAOMock();

    }

    @Before
    public void init() throws TipoChaveNaoEncontradaException {
        cliente = new Cliente();
        cliente.setCpf(12345678900L);
        cliente.setNome("Winicius");
        cliente.setTelefone(27987654321L);
        cliente.setEndereco("Rua teste");
        cliente.setNumero(60);
        cliente.setCidade("Teste City");
        cliente.setEstado("TS");

    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        Assert.assertTrue(clienteDao.cadastrar(cliente));
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Winicius Barcellos");
        clienteDao.alterar(cliente);

        Assert.assertEquals("Winicius Barcellos", cliente.getNome());
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteEncontrado = clienteDao.consultar(cliente.getCpf());
        Assert.assertNotNull(clienteEncontrado);
    }

    @Test
    public void excluirCliente() {
        clienteDao.excluir(cliente.getCpf());
    }

//    @Test
//    public void buscarTodos() {
//        Collection<Cliente> list = clienteDao.buscarTodos();
//        Assert.assertNotNull(list);
//        Assert.assertEquals(2, list.size());
//    }
}
