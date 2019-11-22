package com.jeantravassos.packageservice;

import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.jeantravassos.packageservice.exception.APIException;
import com.jeantravassos.packageservice.service.Packer;

@SpringBootApplication
public class PackageServiceApplication {

	public static void main(String[] args) {
		//SpringApplication.run(PackageServiceApplication.class, args);
		
		try {
			System.out.println(Packer.pack("test-file.txt"));
		} catch (APIException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
