package gerenciador.DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import gerenciador.Utils.JPAUtil;

public class DaoGeneric<Model> {
	
	public void salvar(Model model) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.persist(model);
		transaction.commit();
		entityManager.close();
	}
	
	public Model update(Model model) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		Model mod = entityManager.merge(model);
		transaction.commit();
		entityManager.close();
		
		return mod;
	}
	
	public void delete(Model model) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		EntityTransaction transaction = entityManager.getTransaction();
		transaction.begin();
		
		entityManager.remove(model);
		transaction.commit();
		entityManager.close();
		
	}
	
	
	@SuppressWarnings("unchecked")
	public List<Model> findAll(Class<Model> model) {
		EntityManager entityManager = JPAUtil.getEntityManager();
		
		List<Model> result = entityManager.createQuery("from "+ model.getName()).getResultList();
		
		entityManager.close();
		
		return result;
	}

}
