package myhealthylife.sentencegenerator.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;

import myhealthylife.sentencegenerator.dao.SentenceGeneratorDao;

/**
 * The persistent class for the "SentenceType" database table.
 * 
 */
@Entity
@Table(name="SentenceType")
@NamedQuery(name="SentenceType.findAll", query="SELECT s FROM SentenceType s")
@XmlRootElement(name="sentenceType")
@XmlType(propOrder={"idSentenceType", "name", "motive"})
public class SentenceType implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private Long idSentenceType;
	
	@Column(name="name")
	private String name;

	@Column(name="motive")
	private Boolean motive;
	
	public Long getIdSentenceType() {
		return idSentenceType;
	}

	public void setIdSentenceType(Long idSentenceType) {
		this.idSentenceType = idSentenceType;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	public Boolean getMotive() {
		return motive;
	}

	public void setMotive(Boolean motive) {
		this.motive = motive;
	}

	/* DATABASE OPERATIONS */
	public static SentenceType getSentenceTypeById(long sentenceId) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		SentenceType p = em.find(SentenceType.class, sentenceId);
		SentenceGeneratorDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<SentenceType> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<SentenceType> list = em.createNamedQuery("SentenceType.findAll", SentenceType.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return list;
	}
	
	public static SentenceType saveSentenceType(SentenceType p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return p;
	}
	
	public static SentenceType updateSentenceType(SentenceType p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeSentenceType(SentenceType p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	}
	
	
	
}
