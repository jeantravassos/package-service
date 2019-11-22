package com.jeantravassos.packageservice.service;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.stereotype.Service;

import com.jeantravassos.packageservice.exception.APIException;

@Service
@EnableConfigurationProperties(Packer.class)
public class PackerService {

	public String pack() throws APIException {

		return Packer.pack("");

	}

}
