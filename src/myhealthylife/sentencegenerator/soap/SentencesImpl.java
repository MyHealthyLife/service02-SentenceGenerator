package myhealthylife.sentencegenerator.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.jws.WebParam;
import javax.jws.WebService;

import myhealthylife.sentencegenerator.model.Sentence;
import myhealthylife.sentencegenerator.model.SentenceList;

//Service Implementation

@WebService(endpointInterface = "myhealthylife.sentencegenerator.soap.Sentences",
    serviceName="SentenceService")
public class SentencesImpl implements Sentences {

	@Override
	public SentenceList readSentenceList() {
		
		SentenceList sentences = new SentenceList();
    	sentences.setSentence(Sentence.getAll());
    	
        return sentences;
	}



	
	
	
	/*
    @Override
    public PeopleList readPersonList() {
    	
    	PeopleList people = new PeopleList();
    	people.setPerson(Person.getAll());
    	
        return people;
    }


    
    
    @Override
    public Person readPerson(long id) {
        System.out.println("---> Reading Person by id = "+id);
        Person p = Person.getPersonById(id);
        if (p!=null) {
            System.out.println("---> Found Person by id = "+id+" => "+p.getFirstname());
        } else {
            System.out.println("---> Didn't find any Person with  id = "+id);
        }
        return p;
    }
    




    @Override
    public Person updatePerson(Person person) {
    	
    	// Gets the id of the person to update
    	Long personId = person.getIdPerson();
    	
    	// Updates the data of the user
    	Person currentPerson = Person.getPersonById(person.getIdPerson());
    	if(person.getFirstname()!=null) {
    		currentPerson.setFirstname(person.getFirstname());
    	}
    	if(person.getLastname()!=null) {
    		currentPerson.setLastname(person.getLastname());
    	}
    	if(person.getBirthdate()!=null) {
    		currentPerson.setBirthdate(person.getBirthdate());
    	}
    	
    	// Update query
        Person.updatePerson(currentPerson);
        return Person.getPersonById(personId);
    }
    
    



    @Override
    public Person createPerson(Person person) {
    	
		
    	// Gets the HealthProfile of the person the client wants to add
    	HealthProfile hp = person.getHealthProfile();
    	
    	if(hp!=null) {
    		person.setHealthProfile(hp);
    	}
    	
    	// Then saves the person
    	Person.savePerson(person);

    	// Gets the person just inserted
    	person = Person.getPersonById(person.getIdPerson());
    	
    	return person;
    }
    
    




    @Override
    public long deletePerson(long id) {
        Person p = Person.getPersonById(id);
        
        // If the person exists then it removes it
        if (p!=null) {
            Person.removePerson(p);
            return 0;
        }
        
        else {
            return -1;
        }
    }
    
    





    @Override
    public MeasureHistory readPersonHistory(long id, String measureType) {
    	
    	// Retrieves the data of the person
    	Person person = Person.getPersonById(id);
    	HealthProfile hp = person.getHealthProfile();
    	
    	if(hp!=null) {
	    	
    		// Gets all the measures
	    	List<Measure> measureListOfPerson = hp.getMeasure();
	    	
	    	// Creates new lists
	    	MeasureHistory mh = new MeasureHistory();
	    	
	    	if(measureListOfPerson!=null) {
	    	
		    	Iterator<Measure> i = measureListOfPerson.iterator();
		    	
		    	// Cycles through the list of measures
		    	while(i.hasNext()) {
		    		
		    		Measure currentMeasure = i.next();
		    		
		    		// Checks if the current measure is compliant with the type specified by the user
		    		if(currentMeasure.getMeasureType().equals(measureType)) {
		    			
		    			// Adds the current measure to the history to be returned
		    			mh.getMeasure().add(currentMeasure);
		    			
		    		}
		    	}
	    	}

	    	return mh;
    	}
    	return null;
    	
    }
    
    
    





    @Override
    public MeasureTypes readMeasureTypes() {
    	
    	// Gets all the supported measure types and returns it to the client
    	MeasureTypes mt = new MeasureTypes();
    	
    	return mt;
    	
    }
    
    






    @Override
    public Measure readPersonMeasure(long id, String measureType, long mid) {
    	
    	// Retrieves the data of the person
    	Person person = Person.getPersonById(id);
    	HealthProfile hp = person.getHealthProfile();
    	
    	if(hp!=null) {
    	
	    	List<Measure> measureList = hp.getMeasure();
	    	
	    	// Checks if the list of measurements already exists
	    	if(measureList!=null) {
		    	
		    	Iterator<Measure> i = measureList.iterator();
		    	
	    		// Cycles through all the measurements for that person
		    	while(i.hasNext()) {
		    		
		    		Measure currentMeasure = i.next();
		    		
		    		// Checks if the measurement is compliant with the input parameters
		    		if(currentMeasure.getMid()==mid && currentMeasure.getMeasureType().equals(measureType)) {
		    			
		    			// Returns the measurement to the client
		    			return currentMeasure;
		    			
		    		}
		    	}
	    	}
    	}
    	
    	return null;
    	
    }
    
    
    







    public Person savePersonMeasure(long id, Measure measureToAdd) {
    	
    	// Retrieves the data of the person
    	Person person = Person.getPersonById(id);
    	HealthProfile hp = person.getHealthProfile();
    	
    	// Checks if health profile exists
    	if(hp==null) {
    		hp = new HealthProfile();
    		List<Measure> measureList = new ArrayList<>();
    		hp.setMeasure(measureList);
    		person.setHealthProfile(hp);
    	}
    	
    	List<Measure> measureList = hp.getMeasure();
    	
    	// Checks if the list of measurements already exists
    	if(measureList==null) {
	    	
    		measureList = new ArrayList<>();
    		
    	}
    	
    	// Creates a new measure and copies the attributes provided by the client
    	Measure measure = measureToAdd;
    	measure.setMeasureType(measureToAdd.getMeasureType());
    	measure.setDateRegistered(new Date(System.currentTimeMillis()));
    	
    	// Adds the measure to the list
		measureList.add(measure);
		
		// Updates the person in the database
		Person.updatePerson(person);
		
		person = Person.getPersonById(id);
		List<Measure> measureTemp = person.getHealthProfile().getMeasure();
		Measure measureJustCreated = measureTemp.get(measureTemp.size()-1);
		
		// Returns the history only if it is not null
    	if(measureJustCreated!=null) {
    		return Person.getPersonById(id);
    	}
    	else {
    		return null;
    	}
    	
    }
    
    
    
    






    // Retrieves the data of the person
    public Measure updatePersonMeasure(long id, Measure measureToUpdate) {
		
    	Person person = Person.getPersonById(id);
		HealthProfile hp = person.getHealthProfile();
		int measureId = measureToUpdate.getMid();
		String measureType = measureToUpdate.getMeasureType();
		
		if(hp!=null) {
		
	    	// Gets the measure that needs to be updated
	    	Measure measure = Measure.getMeasureById(measureId);
	    	
	    	// Checks if the measure the client wants already exists
	    	if(measure!=null) {
				
	    		// Checks if it is the same type of measure
	    		if(measure.getMeasureType().equals(measureType)) {
	    			
		    		// Sets the updated value
		    		measure.setMeasureValue(measureToUpdate.getMeasureValue());
		    		
		    		// Updates the measure and returns it to the client
		    		Measure.updateMeasure(measure);
		    		
		    		measure = Measure.getMeasureById(measureId);
		        	return measure;
		        	
	    		}
	    	}
		}
		
		// Otherwise it returns not found
		return null;
    }*/

}