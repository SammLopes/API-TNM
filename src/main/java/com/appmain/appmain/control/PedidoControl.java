package com.appmain.appmain.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appmain.appmain.model.Pedido;
import com.appmain.appmain.service.PedidoService;

@CrossOrigin
@RestController
@RequestMapping("/pedido")
public class PedidoControl {
    
    @Autowired
    private PedidoService pedidoService;
}
