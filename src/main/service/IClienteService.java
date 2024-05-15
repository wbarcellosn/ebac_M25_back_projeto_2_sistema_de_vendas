/**
 * @author winic
 */
package main.service;

import main.domain.Cliente;
import main.exceptions.TipoChaveNaoEncontradaException;

public interface IClienteService {

    Boolean cadastrar (Cliente cliente) throws TipoChaveNaoEncontradaException;

    Cliente buscarPorCPF (Long cpf);

    void excluir(Long cpf);

    void alterar(Cliente cliente) throws TipoChaveNaoEncontradaException;
}
