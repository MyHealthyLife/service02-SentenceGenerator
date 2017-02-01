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
	
	
	/*@WebMethod(operationName="readPersonList")
    @WebResult(name="people") 
    public PeopleList readPersonList();


    @WebMethod(operationName="readPerson")
    @WebResult(name="person") 
    public Person readPerson(@WebParam(name="personId") long id);
    

    @WebMethod(operationName="updatePerson")
    @WebResult(name="person") 
    public Person updatePerson(@WebParam(name="person") Person person);
    

    @WebMethod(operationName="createPerson")
    @WebResult(name="person") 
    public Person createPerson(@WebParam(name="person") Person person);
    

    @WebMethod(operationName="deletePerson")
    @WebResult(name="personId") 
    public long deletePerson(@WebParam(name="personId") long id);
    

    @WebMethod(operationName="readPersonHistory")
    @WebResult(name="healthProfile-history") 
    public MeasureHistory readPersonHistory(@WebParam(name="personId") long id, @WebParam(name="measureType") String measureType);


    @WebMethod(operationName="readMeasureTypes")
    @WebResult(name="measureTypes") 
    public MeasureTypes readMeasureTypes();
    

    @WebMethod(operationName="readPersonMeasure")
    @WebResult(name="measure") 
    public Measure readPersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measureType") String measureType, @WebParam(name="mid") long mid);
    

    @WebMethod(operationName="savePersonMeasure")
    @WebResult(name="person") 
    public Person savePersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measure") Measure measure);
    

    @WebMethod(operationName="updatePersonMeasure")
    @WebResult(name="measure") 
    public Measure updatePersonMeasure(@WebParam(name="personId") long id, @WebParam(name="measure") Measure measureToUpdate);*/
    
}