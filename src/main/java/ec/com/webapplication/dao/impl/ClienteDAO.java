package ec.com.webapplication.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import ec.com.webapplication.dao.IClienteDAO;
import ec.com.webapplication.model.Clientes;

@Repository
public class ClienteDAO implements IClienteDAO{
	private SessionFactory sessionFactory;
	 
    /**
     * Get Hibernate Session Factory
     *
     * @return SessionFactory - Hibernate Session Factory
     */
    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }
 
    /**
     * Set Hibernate Session Factory
     *
     * @param SessionFactory - Hibernate Session Factory
     */
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

	@Override
	public List<Clientes> getClientes() {
		 List list = getSessionFactory().getCurrentSession().createQuery("from Cliente").list();
        return list;
	}  
}
