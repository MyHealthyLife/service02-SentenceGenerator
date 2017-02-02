package myhealthylife.sentencegenerator.model;

import myhealthylife.sentencegenerator.dao.SentenceGeneratorDao;

import java.io.Serializable;

import javax.persistence.*;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


import java.util.Date;
import java.util.List;


/**
 * The persistent class for the "Sentence" database table.
 * 
 */
@Entity
@Table(name="Sentence")
@NamedQuery(name="Sentence.findAll", query="SELECT s FROM Sentence s")
@XmlRootElement(name="sentence")
@XmlType(propOrder={"idSentence", "type", "text", "sentenceType"})
public class Sentence implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue
	private long idSentence;

	@Column(name="type")
	private Integer type;
	
	@Column(name="text")
	private String text;
	
	@ManyToOne(cascade=CascadeType.ALL,fetch=FetchType.EAGER)
	@JoinColumn(name="idSentenceType",referencedColumnName="idSentenceType",insertable=true,updatable=true)
	private SentenceType sentenceType;
	
	public Sentence() {
	}
	

	/* GETTERS AND SETTERS */
	public long getIdSentence() {
		return idSentence;
	}


	public void setIdSentence(long idSentence) {
		this.idSentence = idSentence;
	}


	public Integer getType() {
		return type;
	}


	public void setType(Integer type) {
		this.type = type;
	}
	

	public String getText() {
		return text;
	}


	public void setText(String text) {
		this.text = text;
	}
	
	
	/* DATABASE OPERATIONS */
	public static Sentence getSentenceById(long sentenceId) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		Sentence p = em.find(Sentence.class, sentenceId);
		SentenceGeneratorDao.instance.closeConnections(em);
		return p;
	}
	
	public static List<Sentence> getAll() {

		System.out.println("--> Initializing Entity manager...");
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		System.out.println("--> Querying the database for all the people...");
	    List<Sentence> list = em.createNamedQuery("Sentence.findAll", Sentence.class).getResultList();
		System.out.println("--> Closing connections of entity manager...");
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return list;
	}
	
	public static Sentence saveSentence(Sentence p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		em.persist(p);
		tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return p;
	}
	
	public static Sentence updateSentence(Sentence p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
		p=em.merge(p);
		tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	    return p;
	}
	
	public static void removeSentence(Sentence p) {
		EntityManager em = SentenceGeneratorDao.instance.createEntityManager();
		EntityTransaction tx = em.getTransaction();
		tx.begin();
	    p=em.merge(p);
	    em.remove(p);
	    tx.commit();
	    SentenceGeneratorDao.instance.closeConnections(em);
	}


	public SentenceType getSentenceType() {
		return sentenceType;
	}


	public void setSentenceType(SentenceType sentenceType) {
		this.sentenceType = sentenceType;
	}


	


}