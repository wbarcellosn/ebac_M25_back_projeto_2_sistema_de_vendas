import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 * @author winic
 */

@RunWith(Suite.class)
@Suite.SuiteClasses({
        ClienteServiceTeste.class,
        ClienteDAOTeste.class,
        ProdutoServiceTeste.class,
        ProdutoDAOTeste.class,
        VendaDAOTeste.class})

public class AllTests {

}