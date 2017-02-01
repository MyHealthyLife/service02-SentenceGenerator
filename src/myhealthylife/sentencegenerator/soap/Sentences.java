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

@WebService
@SOAPBinding(style = Style.DOCUMENT, use=Use.LITERAL) //optional
public interface Sentences {

	
	@WebMethod(operationName="readSentenceList")
    @WebResult(name="sentences") 
    public SentenceList readSentenceList();
	

    @WebMethod(operationName="readSentence")
    @WebResult(name="sentence") 
    public Sentence readSentence(@WebParam(name="sentenceId") long id);
    
    /*@WebMethod(operationName="createSentence")
    @WebResult(name="sentence") 
    public Sentence createSentence(@WebParam(name="sentence") Sentence person);
    

    @WebMethod(operationName="deleteSentence")
    @WebResult(name="sentenceId") 
    public long deleteSentence(@WebParam(name="sentenceId") long id);*/
    

}