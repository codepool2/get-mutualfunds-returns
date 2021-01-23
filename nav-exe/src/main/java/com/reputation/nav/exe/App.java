package com.reputation.nav.exe;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.reputation.nav.model.ReturnTable;
import com.reputation.nav.service.IGetReturnsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

@SpringBootApplication
public class App implements CommandLineRunner {

    @Autowired
    private IGetReturnsService getReturnsServiceImpl;

    @Value("${investmentPeriod}")
    private int investmentPeriod;

    @Value("${horizon}")
    private int horizon;

    @Value("${schemeCode: 154655}")
    private int schemeCode;

    public static void main(String[] args) {

        SpringApplication.run(App.class, args);
    }

    public void run(String... args) throws Exception {

        List<ReturnTable> returnTableList = getReturnsServiceImpl.getReturnTable(investmentPeriod, horizon, schemeCode);

        ObjectMapper mapper = new ObjectMapper();


        FileOutputStream outputStream = new FileOutputStream("returnsData.json");

        byte[] strToBytes = mapper.writerWithDefaultPrettyPrinter().writeValueAsString(returnTableList).getBytes();

        outputStream.write(strToBytes);


    }
}
