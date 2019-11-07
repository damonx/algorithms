package com.spark.digital.oidcProxy.config;

import com.spark.digital.oidcProxy.entity.Client;
import com.spark.digital.oidcProxy.entity.Layout;
import com.spark.digital.oidcProxy.repository.ClientInsertRepository;
import com.spark.digital.oidcProxy.repository.LayoutInsertRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.PostConstruct;

@Configuration
@ComponentScan
@EnableTransactionManagement
public class Clients {

    @Autowired
    private ClientInsertRepository clientInsertRepo;

    @Autowired
    private LayoutInsertRepository layoutInsertRepository;

    @PostConstruct
    public void insertDefaultClients() {

        Client skinnyClient = new Client();
        skinnyClient.setId((long) 1);
        skinnyClient.setName("Skinny");
        skinnyClient.setUsername("skinny");
        skinnyClient.setSecret("password");
        skinnyClient.setLogo_url("https://www.phdmedia.com/nz/wp-content/uploads/sites/37/2018/10/Skinny.jpg");

        clientInsertRepo.insertClientList(skinnyClient);

        Client mySparkClient = new Client();
        mySparkClient.setId((long) 2);
        mySparkClient.setName("MySpark Consumer Web");
        mySparkClient.setUsername("mySparkWeb");
        mySparkClient.setSecret("password");
        mySparkClient.setLogo_url("https://www.spark.co.nz/content/dam/sparkdigital/images/logo/purple.svg");

        clientInsertRepo.insertClientList(mySparkClient);

        Client mySparkAppClient = new Client();
        mySparkAppClient.setId((long) 3);
        mySparkAppClient.setName("MySpark Consumer App");
        mySparkAppClient.setUsername("mySparkApp");
        mySparkAppClient.setSecret("password");
        mySparkAppClient.setLogo_url("https://www.spark.co.nz/content/dam/sparkresponsive/logo/green.svg");

        clientInsertRepo.insertClientList(mySparkAppClient);
    }

    @PostConstruct
    public void insertClientLayouts() {
        Layout skinnyLayout = new Layout((long)1,"skinny","orange","white","https://www.phdmedia.com/nz/wp-content/uploads/sites/37/2018/10/Skinny.jpg");
        layoutInsertRepository.insertLayoutCustom(skinnyLayout);

        Layout mySparkClientLayout = new Layout((long)2,"mysparkweb","purple","blue","https://www.spark.co.nz/content/dam/sparkdigital/images/logo/purple.svg");
        layoutInsertRepository.insertLayoutCustom(mySparkClientLayout);

        Layout mySparkAppClientLayout = new Layout((long)3,"mysparkapp","green","blue","https://www.spark.co.nz/content/dam/sparkresponsive/logo/green.svg");
        layoutInsertRepository.insertLayoutCustom(mySparkAppClientLayout);
    }

}
