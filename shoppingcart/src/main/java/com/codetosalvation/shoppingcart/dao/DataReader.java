package com.codetosalvation.shoppingcart.dao;

import com.codetosalvation.shoppingcart.exception.DataReaderException;
import com.codetosalvation.shoppingcart.exception.ResourceNotFoundException;

import java.util.List;

import org.springframework.core.io.Resource;

public interface DataReader {
	public List<String> read(Resource resoure)  throws DataReaderException, ResourceNotFoundException;
}
