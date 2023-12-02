package com.appmain.appmain.control;

import com.appmain.appmain.service.ClienteService;
import com.appmain.appmain.model.Cliente;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@CrossOrigin
@RestController
@RequestMapping("/cliente")
public class ClienteControl{

    @Autowired
    private ClienteService clienteService;
    
}