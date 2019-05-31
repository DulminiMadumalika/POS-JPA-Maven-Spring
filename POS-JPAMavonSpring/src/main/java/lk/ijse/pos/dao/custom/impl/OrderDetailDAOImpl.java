package lk.ijse.pos.dao.custom.impl;

import lk.ijse.pos.dao.CrudDAOImpl;
import lk.ijse.pos.dao.custom.OrderDetailDAO;
import lk.ijse.pos.entity.OrderDetail;
import lk.ijse.pos.entity.OrderDetailPK;
import org.hibernate.Session;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrderDetailDAOImpl extends CrudDAOImpl<OrderDetail, OrderDetailPK> implements OrderDetailDAO {

}
