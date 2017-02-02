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

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Sentences {

	
	@WebMethod(operationName="readSentenceList")
    @WebResult(name="sentences") 
    public SentenceList readSentenceList();
	

    @WebMethod(operationName="readSentence")
    @WebResult(name="sentence") 
    public Sentence readSentence(@WebParam(name="sentenceId") long id);
    
    @WebMethod(operationName="createSentence")
    @WebResult(name="sentence") 
    public Sentence createSentence(@WebParam(name="sentence") Sentence sentenceToSave);
    
    @WebMethod(operationName="deleteSentence")
    @WebResult(name="idSentence") 
    public long deleteSentence(@WebParam(name="sentenceId") long id);
    
    @WebMethod(operationName="updateSentence")
    @WebResult(name="sentence") 
    public Sentence updateSentence(@WebParam(name="sentence") Sentence sentenceToUpdate);
    
    @WebMethod(operationName="readRandomSentence")
    @WebResult(name="sentence") 
    public Sentence readRandomSentence();
    
    @WebMethod(operationName="readRandomSentenceByType")
    @WebResult(name="sentence") 
    public Sentence readRandomSentenceByType(@WebParam(name="sentenceType") long sentenceType);
    
    @WebMethod(operationName="createSentenceType")
    @WebResult(name="sentenceType") 
    public SentenceType createSentenceType(@WebParam(name="typeName") String typeName);
    
    @WebMethod(operationName="setSentenceType")
    @WebResult(name="sentence") 
    public Sentence setSentenceType(@WebParam(name="sentenceId") long id, @WebParam(name="typeName") String typeName);
    

}