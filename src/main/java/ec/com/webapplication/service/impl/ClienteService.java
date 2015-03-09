package ec.com.webapplication.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ec.com.webapplication.dao.IClienteDAO;
import ec.com.webapplication.model.Clientes;
import ec.com.webapplication.service.IClienteService;

@Service
@Transactional(readOnly = true)
public class ClienteService implements IClienteService {
	// LoginDAO es inyectado...
	IClienteDAO ClienteDAO;

	@Override
	public List<Clientes> getClientes(){
		return getClienteDAO().getClientes();
	}
	
	@Autowired
	public IClienteDAO getClienteDAO() {
		return ClienteDAO;
	}
	
	@Autowired
	public void setClienteDAO(IClienteDAO clienteDAO) {
		ClienteDAO = clienteDAO;
	}
}
