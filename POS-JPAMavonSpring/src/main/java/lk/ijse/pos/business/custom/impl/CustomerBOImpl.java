package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.CustomerBO;
import lk.ijse.pos.dao.custom.CustomerDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.CustomerDTO;
import lk.ijse.pos.entity.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class CustomerBOImpl implements CustomerBO {

    @Autowired
    private CustomerDAO customerDAO;

//    public CustomerBOImpl(){
//        String dao = DAOFactory.getInstance().<String>getDAO(DAOTypes.CUSTOMER);
//    }

    @Override
    public CustomerDTO getCustomerById(String id) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();

        em.getTransaction().begin();
        customerDAO.setSession(em);
        Customer customer = customerDAO.find(id);
        CustomerDTO customerDTO = new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress());
        em.getTransaction().commit();
        em.close();
        return customerDTO;

    }

    public List<CustomerDTO> getAllCustomers() throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        customerDAO.setSession(em);
        List<CustomerDTO> customers = customerDAO.findAll().stream().map(customer -> new CustomerDTO(customer.getId(), customer.getName(), customer.getAddress())).collect(Collectors.toList());
        em.getTransaction().commit();
        em.close();
        return customers;

    }

    public void saveCustomer(CustomerDTO dto) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        customerDAO.setSession(em);
        customerDAO.save(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
        em.getTransaction().commit();
        em.close();
    }

    public void updateCustomer(CustomerDTO dto) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        customerDAO.setSession(em);
        customerDAO.update(new Customer(dto.getId(), dto.getName(), dto.getAddress()));
        em.getTransaction().commit();
        em.close();
    }

    public void removeCustomer(String id) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        customerDAO.setSession(em);
        customerDAO.delete(id);
        em.getTransaction().commit();
        em.close();
    }

}
