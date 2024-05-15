/**
 * @author winic
 */
package main.service;

import main.dao.IClienteDAO;
import main.domain.Cliente;
import main.service.generic.GenericService;

public class ClienteService extends GenericService<Cliente, Long> implements IClienteService {

    public ClienteService(IClienteDAO clienteDAO) {
        super(clienteDAO);
    }

    @Override
    public Cliente buscarPorCPF(Long cpf) {
        return this.dao.consultar(cpf);
    }
}

