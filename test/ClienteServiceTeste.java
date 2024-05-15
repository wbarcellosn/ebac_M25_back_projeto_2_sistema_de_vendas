/**
 * @author winic
 */

import main.dao.IClienteDAO;
import main.domain.Cliente;
import main.exceptions.TipoChaveNaoEncontradaException;
import main.service.ClienteService;
import main.service.IClienteService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import mocks.ClienteDAOMock;

public class ClienteServiceTeste {

    private IClienteService clienteService;

    private Cliente cliente;

    public ClienteServiceTeste() {
        IClienteDAO dao = new ClienteDAOMock();
        clienteService = new ClienteService(dao);
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

        clienteService.cadastrar(cliente);
    }

    @Test
    public void pesquisarCliente() {
        Cliente clienteConsultado = clienteService.buscarPorCPF(cliente.getCpf());
        Assert.assertNotNull(clienteConsultado);
    }

    @Test
    public void salvarCliente() throws TipoChaveNaoEncontradaException {
        Boolean retorno = clienteService.cadastrar(cliente);

        Assert.assertTrue(retorno);
    }

    @Test
    public void excluirCliente() {
        clienteService.excluir(cliente.getCpf());
    }

    @Test
    public void alterarCliente() throws TipoChaveNaoEncontradaException {
        cliente.setNome("Winicius Barcellos");
        clienteService.alterar(cliente);

        Assert.assertEquals("Winicius Barcellos", cliente.getNome());
    }
}
