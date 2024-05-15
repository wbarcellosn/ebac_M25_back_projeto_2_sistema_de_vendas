/**
 * @author winic
 */
package main.service;

import main.dao.IProdutoDAO;
import main.domain.Produto;
import main.service.generic.GenericService;

public class ProdutoService extends GenericService<Produto, String> implements IProdutoService {

    public ProdutoService(IProdutoDAO dao) {
        super(dao);
    }
}
