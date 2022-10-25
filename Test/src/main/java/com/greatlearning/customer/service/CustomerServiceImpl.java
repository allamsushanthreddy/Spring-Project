package com.greatlearning.customer.service;

import java.util.List;
import javax.transaction.Transactional;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import com.greatlearning.customer.entity.Customer;
import com.greatlearning.customer.service.CustomerService;

@Repository

public class CustomerServiceImpl implements CustomerService{
	private SessionFactory sessionFacotry;
	//create session

	private Session session;

	@Autowired
	CustomerServiceImpl(SessionFactory sessionFactory){
		this.sessionFacotry = sessionFactory;
		try {
			session = sessionFactory.getCurrentSession();

		}catch (HibernateException e) {
			session = sessionFactory.openSession();
		}
	}
	@Transactional
	public List<Customer> findAll(){
		Transaction tx = session.beginTransaction();
		List<Customer> customer = session.createQuery("from Customer").list();
		tx.commit();
		return customer;
	}
	@Transactional
	public Customer findById(int id) {
		Customer customer = new Customer();
		Transaction tx = session.beginTransaction();
		customer = session.get(Customer.class, id);
		tx.commit();
		return customer;
	}
	@Transactional
	public void save(Customer theCustomer) {
		Transaction tx = session.beginTransaction();
		session.saveOrUpdate(theCustomer);
		tx.commit();
	}
	@Transactional
	public void deleteById(int id) {
		Transaction tx = session.beginTransaction();
		Customer customer = session.get(Customer.class,id);
		//delete record
		session.delete(customer);
		tx.commit();
	}
}
