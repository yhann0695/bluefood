package com.bluefood.infrastructure.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.bluefood.domain.pedido.Pedido;
import com.bluefood.domain.pedido.PedidoRepository;

@Controller
@RequestMapping(path = "/cliente/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoRepository pedidoRespositoy;

	@GetMapping(path = "/view")
	public String viewPedido(@RequestParam("pedidoId") Integer pedidoId,
			Model model) {
		
		Pedido pedido = pedidoRespositoy.findById(pedidoId).orElseThrow();
		model.addAttribute("pedido", pedido);
		
		return "cliente-pedido";
	}
}
