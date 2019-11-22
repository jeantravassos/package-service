package com.jeantravassos.packageservice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.io.File;
import java.io.IOException;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;

import com.jeantravassos.packageservice.exception.APIException;
import com.jeantravassos.packageservice.service.PackerService;

@SpringBootTest("service.pack=Mobiquity!!!")
class PackerServiceTests {


	@Autowired
    private PackerService packerService;
	
    @Test
    public void contextLoads() {

    }

    @SpringBootApplication
    static class TestConfiguration {
    }
    
    @Test
    public void whenExceptionThrown_thenAssertionSucceeds() {
    	assertThrows(APIException.class, () -> packerService.pack("test-"));
    }
    
    @Test
    public void whenCorrectFilePath_thenAssertionSucceeds() throws APIException {
    	assertThat(packerService.pack("test-file.txt")).isNotNull().isNotBlank().contains("2,7");
    }
    
    @Test
    public void whenEmptyFile_thenThrowException() throws APIException {
    	
    	try {
			File f = File.createTempFile("testFile", ".txt");
			
			assertThrows(APIException.class, () -> packerService.pack(f.getAbsolutePath()));
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    
    }
    
}
