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

	
	/**
	 * Gets the entire list of sentences present in the database
	 * @return The SentenceList object, containing all the sentences in the database
	 */
	@WebMethod(operationName="readSentenceList")
    @WebResult(name="sentenceList") 
    public SentenceList readSentenceList();
	
	/**
	 * Gets a specific sentence based on its identifier
	 * @param id The identifier of the sentence
	 * @return The Sentence object identified by id
	 */
    @WebMethod(operationName="readSentence")
    @WebResult(name="sentence") 
    public Sentence readSentence(@WebParam(name="sentenceId") long id);
    
    /**
     * Creates a new sentence object and saves it into the database
     * @param sentenceToSave The sentence the client wants to save into the database
     * @return The Sentence object just inserted in the database
     */
    @WebMethod(operationName="createSentence")
    @WebResult(name="sentence") 
    public Sentence createSentence(@WebParam(name="sentence") Sentence sentenceToSave);
    
    /**
     * Deletes a sentence already present in the database
     * @param id The identifier of the sentence to delete
     * @return The identifier of the sentence just deleted
     */
    @WebMethod(operationName="deleteSentence")
    @WebResult(name="idSentence") 
    public long deleteSentence(@WebParam(name="sentenceId") long id);
    
    /**
     * Updates an existing sentence in the database
     * @param sentenceToUpdate The sentence object the user wants to update
     * @return The sentence object just updated
     */
    @WebMethod(operationName="updateSentence")
    @WebResult(name="sentence") 
    public Sentence updateSentence(@WebParam(name="sentence") Sentence sentenceToUpdate);
    
    /**
     * Gets a random sentence from the whole set
     * @return A sentence object randomly chosen
     */
    @WebMethod(operationName="readRandomSentence")
    @WebResult(name="sentence") 
    public Sentence readRandomSentence();
    
    /**
     * Gets a random sentence from the set of sentences having a particular type
     * @param sentenceType The type of the sentence the user wants
     * @return A sentence object randomly chosen for the given type
     */
    @WebMethod(operationName="readRandomSentenceByType")
    @WebResult(name="sentence") 
    public Sentence readRandomSentenceByType(@WebParam(name="sentenceType") String sentenceType);
    
    /**
     * Gets the entire set of types for the sentences
     * @return A list containing the types available in the database
     */
    @WebMethod(operationName="readSentenceTypeList")
    @WebResult(name="sentenceTypeList") 
    public SentenceTypeList readSentenceTypeList();
    
    /**
     * Creates a new type for sentences
     * @param typeName The name of the type the user wants to create
     * @return The type object just created
     */
    @WebMethod(operationName="createSentenceType")
    @WebResult(name="sentenceType") 
    public SentenceType createSentenceType(@WebParam(name="typeName") String typeName);
    
    /**
     * Sets an existing type to an existing sentence
     * @param sentenceId The identifier of the sentence to which the user wants to assign an existing type
     * @param typeId The type the user wants to assign to that specific sentence
     * @return The sentence object just updated
     */
    @WebMethod(operationName="setSentenceType")
    @WebResult(name="sentence") 
    public Sentence setSentenceType(@WebParam(name="sentenceId") long sentenceId, @WebParam(name="typeId") long typeId);
    
    /**
     * Deletes a type already present in the database
     * @param id The identifier of the type the user wants to delete
     * @return The identifier of the type the user just deleted
     */
    @WebMethod(operationName="deleteSentenceType")
    @WebResult(name="idSentenceType") 
    public long deleteSentenceType(@WebParam(name="typeId") long id);
    

}