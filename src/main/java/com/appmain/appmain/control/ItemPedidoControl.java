package com.appmain.appmain.control;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.appmain.appmain.model.ItemPedido;
import com.appmain.appmain.service.ItemPedidoService;

@CrossOrigin
@RestController
@RequestMapping("/itempedido")
public class ItemPedidoControl {
    
    @Autowired
    private ItemPedidoService itenService;
}
