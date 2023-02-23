package com.masai.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.masai.exception.IdCardException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;
import com.masai.repository.IdCardRepository;

@Service
public class IdServiceImpl implements IdCardService {

@Autowired
private	IdCardRepository idCardRepository;
	
	@Override
	public PanCard getPanCardByNumber(Integer id) throws IdCardException {
		
		    Optional<IdCard> optionalIdCard=idCardRepository.findById(id);
		    
		    IdCard idCard=optionalIdCard.get();
		       
		    
		       PanCard panCard= idCard.getPancard();
		       if(panCard==null) {
		    	   throw new IdCardException("No pancard is exist with this pancard Number");
		       }
		    
		

		return panCard;
	}

	@Override
	public AdharCard getAdharCardByNuber(Integer id) throws IdCardException {

	    Optional<IdCard> optionalIdCard=idCardRepository.findById(id);
	    
	    IdCard idCard=optionalIdCard.get();
	    
	    AdharCard adharCard=idCard.getAdharCard();
	    
	    if(adharCard==null) {
	    	   throw new IdCardException("No AadarCard is exist with this pancard Number");
	       }
	    
	 
	    
		return adharCard;
	}

	@Override
	public IdCard saveIdcard(IdCard idCard) throws IdCardException {
		
		
		IdCard savedIdCard =idCardRepository.save(idCard);
		
		
		if(savedIdCard==null) {
			
			throw new IdCardException("Please enter valid id card detail");
			
		}
		
		
		
		return savedIdCard;
	}

}
