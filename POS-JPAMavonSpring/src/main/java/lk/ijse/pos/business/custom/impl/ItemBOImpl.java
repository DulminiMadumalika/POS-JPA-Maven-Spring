package lk.ijse.pos.business.custom.impl;

import lk.ijse.pos.business.custom.ItemBO;
import lk.ijse.pos.dao.custom.ItemDAO;
import lk.ijse.pos.db.HibernateUtil;
import lk.ijse.pos.dto.ItemDTO;
import lk.ijse.pos.entity.Item;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ItemBOImpl implements ItemBO {

    @Autowired
    private ItemDAO itemDAO;

    public List<ItemDTO> getAllItems() throws Exception {

        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        itemDAO.setSession(em);
        List<ItemDTO> items = itemDAO.findAll().stream().map(item -> new ItemDTO(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand())).collect(Collectors.toList());
        em.getTransaction().commit();
        em.close();
        return items;

    }

    public void saveItem(ItemDTO item) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        itemDAO.setSession(em);
        itemDAO.save(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        em.getTransaction().commit();
        em.close();
    }

    public void updateItem(ItemDTO item) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        itemDAO.setSession(em);
        itemDAO.update(new Item(item.getCode(), item.getDescription(), item.getUnitPrice(), item.getQtyOnHand()));
        em.getTransaction().commit();
        em.close();
    }

    public void deleteItem(String code) throws Exception {
        EntityManager em = HibernateUtil.getEntityManagerFactory().createEntityManager();
        em.getTransaction().begin();
        itemDAO.setSession(em);
        itemDAO.delete(code);
        em.getTransaction().commit();
        em.close();
    }

}
