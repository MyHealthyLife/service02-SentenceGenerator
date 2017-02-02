package myhealthylife.sentencegenerator.soap;

import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;
import javax.jws.WebResult;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;
import javax.jws.soap.SOAPBinding.Use;

import myhealthylife.sentencegenerator.model.Sentence;
import myhealthylife.sentencegenerator.model.SentenceList;
import myhealthylife.sentencegenerator.model.SentenceType;
import myhealthylife.sentencegenerator.model.SentenceTypeList;

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Sentences {

	
	/* Gets the entire list of sentences present in the database */
	@WebMethod(operationName="readSentenceList")
    @WebResult(name="sentences") 
    public SentenceList readSentenceList();
	
	/* Gets a specific sentence based on its identifier */
    @WebMethod(operationName="readSentence")
    @WebResult(name="sentence") 
    public Sentence readSentence(@WebParam(name="sentenceId") long id);
    
    /* Creates a new sentence object and saves it into the database */
    @WebMethod(operationName="createSentence")
    @WebResult(name="sentence") 
    public Sentence createSentence(@WebParam(name="sentence") Sentence sentenceToSave);
    
    /* Deletes a sentence already present in the database */
    @WebMethod(operationName="deleteSentence")
    @WebResult(name="idSentence") 
    public long deleteSentence(@WebParam(name="sentenceId") long id);
    
    /* Updates an existing sentence in the database */
    @WebMethod(operationName="updateSentence")
    @WebResult(name="sentence") 
    public Sentence updateSentence(@WebParam(name="sentence") Sentence sentenceToUpdate);
    
    /* Gets a random sentence from the whole set */
    @WebMethod(operationName="readRandomSentence")
    @WebResult(name="sentence") 
    public Sentence readRandomSentence();
    
    /* Gets a random sentence from the set of sentences having a particular type */
    @WebMethod(operationName="readRandomSentenceByType")
    @WebResult(name="sentence") 
    public Sentence readRandomSentenceByType(@WebParam(name="sentenceType") String sentenceType);
    
    /* Gets the entire set of types for the sentences */
    @WebMethod(operationName="readSentenceTypeList")
    @WebResult(name="sentenceTypes") 
    public SentenceTypeList readSentenceTypeList();
    
    /* Creates a new type for sentences */
    @WebMethod(operationName="createSentenceType")
    @WebResult(name="sentenceType") 
    public SentenceType createSentenceType(@WebParam(name="typeName") String typeName);
    
    /* Sets an existing type to an existing sentence */
    @WebMethod(operationName="setSentenceType")
    @WebResult(name="sentence") 
    public Sentence setSentenceType(@WebParam(name="sentenceId") long sentenceId, @WebParam(name="typeId") long typeId);
    

}