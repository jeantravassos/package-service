package com.jeantravassos.packageservice;

import static org.assertj.core.api.Assertions.assertThat;

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
    public void contextLoads() throws APIException {
        assertThat(packerService.pack()).isNotNull();
    }

    @SpringBootApplication
    static class TestConfiguration {
    }
}
