package com.spark.digital.oidcProxy.controllers;

import com.spark.digital.oidcProxy.entity.Client;
import com.spark.digital.oidcProxy.entity.Layout;
import com.spark.digital.oidcProxy.repository.ClientRepository;
import com.spark.digital.oidcProxy.repository.LayoutRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Iterator;
import java.util.List;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepo;

    @Autowired
    private LayoutRepository layoutRepo;

    /* Retrieve list of existing OAuth Client details including page layout configuration */
    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value = "/client/list")
    public List<Client> getClientListWithDetails() {

        List<Client> clientList = clientRepo.findAll();
        Iterator iterator = clientList.iterator();

        while(iterator.hasNext()) {
            Client client = (Client) iterator.next();
            System.out.println(client.getName());
        }

        return clientList;
    }

    @CrossOrigin(origins = "*")
    @RequestMapping(method = RequestMethod.GET, value =  "/client/layout")
    public Layout getClientUILayoutDetails(@RequestParam(value = "clientName") String clientName) {
        Layout layout = layoutRepo.findClientByName(clientName);
        return layout;
    }
}
