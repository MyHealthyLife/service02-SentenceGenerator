package myhealthylife.sentencegenerator.soap;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

import javax.jws.WebParam;
import javax.jws.WebService;

import myhealthylife.sentencegenerator.model.Sentence;
import myhealthylife.sentencegenerator.model.SentenceList;
import myhealthylife.sentencegenerator.model.SentenceType;
import myhealthylife.sentencegenerator.model.SentenceTypeList;

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

	@Override
	public Sentence readSentence(long id) {
		
        Sentence p = Sentence.getSentenceById(id);
        if (p!=null) {
            System.out.println("---> Found Sentence by id = "+id+" => "+p.getText());
        } else {
            System.out.println("---> Didn't find any Sentence with  id = "+id);
        }
        return p;
		
	}

	@Override
	public Sentence createSentence(Sentence sentenceToSave) {
    	
    	// Saves the new sentence
    	Sentence.saveSentence(sentenceToSave);

    	// Gets the sentence just inserted
    	sentenceToSave = Sentence.getSentenceById(sentenceToSave.getIdSentence());
    	
    	return sentenceToSave;

	}

	@Override
	public long deleteSentence(long id) {
		

		Sentence p = Sentence.getSentenceById(id);
        
        // If the sentence exists then it removes it
        if (p!=null) {
            Sentence.removeSentence(p);
            return p.getIdSentence();
        }
        
        // Otherwise it returns an error code
        else {
            return -1;
        }
		
	}

	@Override
	public Sentence updateSentence(Sentence sentenceToUpdate) {
		
		// Gets the id of the sentence to update
    	Long sentenceId = sentenceToUpdate.getIdSentence();
    	
    	// Updates the data of the sentence
    	Sentence currentSentence = Sentence.getSentenceById(sentenceToUpdate.getIdSentence());
    	
    	if(sentenceToUpdate.getText()!=null) {
    		currentSentence.setText(sentenceToUpdate.getText());
    	}
    	
    	// Update query
        Sentence.updateSentence(currentSentence);
        return Sentence.getSentenceById(sentenceId);
		
	}

	@Override
	public Sentence readRandomSentence() {
		
		List<Sentence> sentenceList = this.readSentenceList().getSentence();
		
		if(!sentenceList.isEmpty()) {
			
			Sentence selectedSentence = sentenceList.get((int)this.getRandomLong(sentenceList.size()-1));
				
			return selectedSentence;
			
		}
		
		return null;
		
	}
	
	
	@Override
	public Sentence readRandomSentenceByType(long sentenceType) {
		
		List<Sentence> sentenceList = this.readSentenceList().getSentence();
		List<Sentence> sentenceListFiltered = new ArrayList<>();
		
		for(int i=0;i<sentenceList.size();i++) {
			
			Sentence currentSentence = sentenceList.get(i);
			
			/*if(currentSentence.getType()!=null && currentSentence.getType()==sentenceType) {
				
				sentenceListFiltered.add(currentSentence);
				
			}*/
			
			
		}
		
		
		if(!sentenceListFiltered.isEmpty()) {
			
			Sentence selectedSentence = sentenceListFiltered.get((int)this.getRandomLong(sentenceListFiltered.size()-1));
				
			return selectedSentence;
			
		}
		
		return null;
		
	}
	
	

	private long getRandomLong(long upperRange) {
		
		Random randomGenerator;
		randomGenerator = new Random();
		
		long lowerRange = 0;
		
		return lowerRange + (long)(randomGenerator.nextDouble()*(upperRange - lowerRange));
		
	}

	
	
	@Override
	public SentenceTypeList readSentenceTypeList() {
		
		// Gets all the types and adds them to the list
		SentenceTypeList sentenceTypes = new SentenceTypeList();
    	sentenceTypes.setSentenceType(SentenceType.getAll());
    	
        return sentenceTypes;
	}
	
	
	
	@Override
	public SentenceType createSentenceType(String typeName) {
		
		// Creates a new type
		SentenceType sTypeToSave = new SentenceType();
		sTypeToSave.setDescription(typeName);

		// Saves the new type
    	SentenceType.saveSentenceType(sTypeToSave);

    	// Gets the type just inserted and returns it
    	sTypeToSave = SentenceType.getSentenceTypeById(sTypeToSave.getIdSentenceType());
    	
    	return sTypeToSave;

		
	}
	
	
	@Override
	public Sentence setSentenceType(long sentenceId, long typeId) {
		
		// Gets the sentence to update from the database
		Sentence sentenceToUpdate = Sentence.getSentenceById(sentenceId);
		
		// Gets the requested sentence type from the database
		SentenceType sType = SentenceType.getSentenceTypeById(typeId);
		
		if(sentenceToUpdate!=null && sType!=null) {
			
			// Updates the sentence type
			sentenceToUpdate.setSentenceType(sType);
			Sentence.updateSentence(sentenceToUpdate);

			return Sentence.getSentenceById(sentenceId);
		}
		
		return null;
	}

	




}