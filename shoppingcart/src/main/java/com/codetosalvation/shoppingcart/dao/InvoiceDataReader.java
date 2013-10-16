package com.codetosalvation.shoppingcart.dao;

import com.codetosalvation.shoppingcart.exception.DataReaderException;
import com.codetosalvation.shoppingcart.exception.ResourceNotFoundException;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;

import org.apache.commons.io.IOUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

@Component
public class InvoiceDataReader implements DataReader {
	Logger logger = LoggerFactory.getLogger(InvoiceDataReader.class);
	@Override
	public List<String> read(Resource resoure) throws DataReaderException, ResourceNotFoundException {
		InputStream inStream = null;
		try {
			logger.debug("Reading invoice data..");
			inStream = resoure.getInputStream();
			return IOUtils.readLines(inStream);
		}catch(java.io.FileNotFoundException fne) {
			throw new ResourceNotFoundException(fne.getMessage());
		}catch (final IOException e) {
			throw new DataReaderException("Exception while reading invoice data file", e);
		} finally {
			IOUtils.closeQuietly(inStream);
		}
	}
}
