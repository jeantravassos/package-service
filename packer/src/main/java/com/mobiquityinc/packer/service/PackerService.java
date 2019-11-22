package com.mobiquityinc.packer.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.mobiquityinc.packer.exception.APIException;

@Service
@EnableConfigurationProperties(Packer.class)
public class PackerService {

	/**
	 * Service method calls the main function in the application
	 * 
	 * @param filePath
	 * @return
	 * @throws APIException
	 */
	public String pack(String filePath) throws APIException {

		if (StringUtils.isEmpty(filePath)) {
			throw new APIException("File Path is invalid : " + filePath);
		}
		
		return Packer.pack(filePath);
	}

}
