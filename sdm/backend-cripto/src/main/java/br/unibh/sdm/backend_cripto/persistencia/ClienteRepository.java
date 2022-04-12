package br.unibh.sdm.backend_cripto.persistencia;

import java.util.List;

import org.socialsignin.spring.data.dynamodb.repository.EnableScan;
import org.springframework.data.repository.CrudRepository;

import br.unibh.sdm.backend_cripto.entidades.Cliente;

/**
 * Esta classe estende o padrão CrudRepository 
 * @author jhcru
 *
 */
@EnableScan()
public interface ClienteRepository extends CrudRepository<Cliente, String> {
	
	List<Cliente> findByNome(String codigo);
	
}
