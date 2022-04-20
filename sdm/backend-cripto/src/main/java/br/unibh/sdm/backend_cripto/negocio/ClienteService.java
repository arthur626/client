package br.unibh.sdm.backend_cripto.negocio;

import java.lang.invoke.MethodHandles;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.apache.commons.collections4.IteratorUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import br.unibh.sdm.backend_cripto.entidades.Cliente;
import br.unibh.sdm.backend_cripto.persistencia.ClienteRepository;

/**
 * Classe contendo a lógica de negócio para Cliente
 * @author jhcru
 *
 */
@Service
public class ClienteService {

    private static final Logger logger= LoggerFactory.getLogger(MethodHandles.lookup().lookupClass());
    
    private final ClienteRepository clienteRepo;

    public ClienteService(ClienteRepository clienteRepository){
        this.clienteRepo=clienteRepository;
    }
    
    public List<Cliente> getClientes(){
        if(logger.isInfoEnabled()){
            logger.info("Buscando todos os objetos");
        }
        Iterable<Cliente> lista = this.clienteRepo.findAll();
        if (lista == null) {
        	return new ArrayList<Cliente>();
        }
        return IteratorUtils.toList(lista.iterator());
    }

    public Cliente getClienteByCodigo(String codigo){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Cliente com o codigo {}",codigo);
        }
        Optional<Cliente> retorno = this.clienteRepo.findById(codigo);
        if(!retorno.isPresent()){
            throw new RuntimeException("Cliente com o codigo "+codigo+" nao encontrada");
        }
        return retorno.get();
    }
    
    public Cliente getClienteByNome(String nome){
        if(logger.isInfoEnabled()){
            logger.info("Buscando Cliente com o nome {}",nome);
        }
        List<Cliente> lista = this.clienteRepo.findByNome(nome);
        if(lista == null || lista.isEmpty()){
            throw new RuntimeException("Cliente com o nome "+nome+" nao encontrada");
        }
        return lista.get(0);
    }

    public Cliente saveCliente(Cliente criptomoeda){
        if(logger.isInfoEnabled()){
            logger.info("Salvando Cliente com os detalhes {}",criptomoeda.toString());
        }
        return this.clienteRepo.save(criptomoeda);
    }
    
    public void deleteCliente(String codigo){
        if(logger.isInfoEnabled()){
            logger.info("Excluindo Cliente com id {}",codigo);
        }
        this.clienteRepo.deleteById(codigo);
    }

    public boolean isClienteExists(Cliente criptomoeda){
    	Optional<Cliente> retorno = this.clienteRepo.findById(criptomoeda.getId());
        return retorno.isPresent() ? true:  false;
    }

    public boolean isClienteExists(String codigo){
    	Optional<Cliente> retorno = this.clienteRepo.findById(codigo);
        return retorno.isPresent() ? true:  false;
    }
}