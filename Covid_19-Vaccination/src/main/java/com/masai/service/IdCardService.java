package com.masai.service;

import com.masai.exception.IdCardException;
import com.masai.model.AdharCard;
import com.masai.model.IdCard;
import com.masai.model.PanCard;

public interface IdCardService {
	
	public PanCard getPanCardByNumber(Integer id) throws IdCardException;
	
	public AdharCard getAdharCardByNuber(Integer id) throws IdCardException;
	
	public IdCard saveIdcard(IdCard idCard) throws IdCardException;

}
