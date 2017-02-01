package myhealthylife.sentencegenerator.dao;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import myhealthylife.sentencegenerator.model.Sentence;

public enum LifeCoachDao {
	instance;
	private EntityManagerFactory emf;
	
	private LifeCoachDao() {
		if (emf!=null) {
			emf.close();
		}
		emf = Persistence.createEntityManagerFactory("service02-SentenceGenerator");
	}
	
	public EntityManager createEntityManager() {
		try {
			return emf.createEntityManager();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;    
	}

	public void closeConnections(EntityManager em) {
		em.close();
	}

	public EntityTransaction getTransaction(EntityManager em) {
		return em.getTransaction();
	}
	
	public EntityManagerFactory getEntityManagerFactory() {
		return emf;
	}
	
	// Sentence related operations could also directly go into the "Sentence" Model
	// Check Methods in LifeStaus as example
	public static Sentence getSentenceById(Long sentenceId) {
		EntityManager em = instance.createEntityManager();
		Sentence p = em.find(Sentence.class, sentenceId);
		instance.closeConnections(em);
		return p;
	}
	
	public static List<Sentence> getAll() {
		EntityManager em = instance.createEntityManager();
	    List<Sentence> list = em.createNamedQuery("Sentence.findAll", Sentence.class).getResultList();
	    instance.closeConnections(em);
	    return list;
	}
	
	// add other database global access operations

}