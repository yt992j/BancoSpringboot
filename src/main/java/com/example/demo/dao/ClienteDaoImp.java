package com.example.demo.dao;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.demo.entity.Cliente;


@Repository
public class ClienteDaoImp implements ClienteDao {
	@PersistenceContext
	private EntityManager en;

	@SuppressWarnings("unchecked")
	@Override
	@Transactional(readOnly = true)
	public List<Cliente> findAll() {
		/*HQL o JPQL != SQL*/
		List<Cliente> result = en.createQuery("from Cliente").getResultList();
		return result;
	}

	@Override
	@Transactional(readOnly = true)
	public Cliente find(Long id) {
		Cliente result = en.find(Cliente.class, id);//select * from alumno where id ={id}
		return result;
	}

	@Override
	@Transactional
	public void insert(Cliente nuevo) {
		en.persist(nuevo);
		en.flush();
	}

	@Override
	@Transactional
	public void update(Cliente nuevo) {
		Cliente antes = find(nuevo.getId());
		BeanUtils.copyProperties(nuevo, antes);
		en.flush();
	}
	
	@Transactional
	@Override
	public void delete(Long id) {
		Cliente entity = find(id);
		en.remove(entity);
	}

}
