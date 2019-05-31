package com.cbcho.boot.ch6.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.cbcho.boot.ch6.model.MyData;

@Repository
public class MyDataDaoImpl implements MyDataDao<MyData> {
	
	private static final long serialVersionUID = 1L;
	
	private EntityManager entityManager;
	
	public MyDataDaoImpl() {
		super();
	}
	
	public MyDataDaoImpl(EntityManager manager) {
		entityManager = manager;
	}

	@Override
	public List<MyData> getAll() {
		
		/*
		Query query = entityManager.createQuery("from MyData");
		List<MyData> list = query.getResultList();
		entityManager.close();
		
		return list;
		*/
		
		
		 List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		
		query.select(root); 
				
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
		
		return list;
		
		
		/*
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		
		query.select(root).orderBy(builder.asc(root.get("name"))); 
				
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
		
		return list;
		*/
		/*
		int offset = 1;
		int limit = 2;
		List<MyData> list = null;
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		
		query.select(root);
		
		list = (List<MyData>)entityManager.createQuery(query)
					.setFirstResult(offset)
					.setMaxResults(limit)
					.getResultList();
		return list;
		*/
	}

	@Override
	public MyData findById(long id) {
		
		return (MyData)entityManager.createQuery("from MyData where id = " + id).getSingleResult();
	}

	@Override
	public List<MyData> findByName(String name) {
		
		return (List<MyData>)entityManager.createQuery("from MyData where name = " + name).getResultList();
	}

	@Override
	public List<MyData> find(String fstr) {
		
		/*
		List<MyData> list = null;
		String qstr = "from MyData where id = :fstr";
		Query query = entityManager.createQuery(qstr).setParameter("fstr", Long.parseLong(fstr));
		list = query.getResultList();
		*/
		
		/*
		String qstr = "from MyData where id = :fid or name like :fname or mail like :fmail";
		Long fid = 0L;
		
		try {
			fid = Long.parseLong(fstr);
		} catch (Exception e) {
			
		}
		
		Query query = entityManager.createQuery(qstr).setParameter("fid", fid)
						.setParameter("fname", "%" + fstr + "%")
						.setParameter("fmail", fstr + "@%");
		
		list = query.getResultList();
		
		return list;
		*/
		
		/*
		List<MyData> list = null;
		String qstr = "from MyData where id = ?1 or name like ?2 or mail like ?3";
		Long fid = 0L;
		
		try {
			fid = Long.parseLong(fstr);
		} catch (Exception e) {
			
		}
		
		Query query = entityManager.createQuery(qstr).setParameter(1, fid)
						.setParameter(2, "%" + fstr + "%")
						.setParameter(3, fstr + "@%");
		
		list = query.getResultList();
		
		return list;
		*/
		
		/*
		List<MyData> list = null;
		Long fid = 0L;
		
		try {
			fid = Long.parseLong(fstr);
		} catch (Exception e) {
			
		}
		
		Query query = entityManager.createNamedQuery("findWithName").setParameter("fname", "%" + fstr + "%"); 
		
		list = query.getResultList();
		
		return list;
		*/
		
		CriteriaBuilder builder = entityManager.getCriteriaBuilder();
		CriteriaQuery<MyData> query = builder.createQuery(MyData.class);
		Root<MyData> root = query.from(MyData.class);
		
		query.select(root).where(builder.equal(root.get("name"), fstr));
		
		List<MyData> list = null;
		list = (List<MyData>)entityManager.createQuery(query).getResultList();
		
		return list;
		
	}

	@Override
	public List<MyData> findByAge(int min, int max) {
		
		return (List<MyData>)entityManager.createNamedQuery("findByAge")
				.setParameter("min", min)
				.setParameter("max", max)
				.getResultList();
	}
	
}
