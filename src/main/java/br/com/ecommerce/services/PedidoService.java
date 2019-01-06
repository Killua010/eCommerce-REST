package br.com.ecommerce.services;

import java.util.Date;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.ItemPedidoDAO;
import br.com.ecommerce.DAO.PagamentoDAO;
import br.com.ecommerce.DAO.PedidoDAO;
import br.com.ecommerce.domain.ItemPedido;
import br.com.ecommerce.domain.PagamentoComBoleto;
import br.com.ecommerce.domain.PagamentoComCartao;
import br.com.ecommerce.domain.Pedido;
import br.com.ecommerce.domain.enums.EstadoPagamento;
import br.com.ecommerce.services.exceptions.ObjectNotFoundException;

@Service
public class PedidoService {
	
	@Autowired
	private BoletoService boletoService;
	
	@Autowired
	private PagamentoDAO pagamentoDao;
	
	@Autowired
	private ItemPedidoDAO itemPedidoDao;
	
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ClienteService clienteService;
	
	@Autowired
	private PedidoDAO pedidoDao;
	
	@Autowired
	private EmailService emailService;
	
	public Pedido buscar(Integer id) {		
		return pedidoDao.findById(id).orElseThrow(() -> new ObjectNotFoundException("Objeto não encontrado! id: " + id
				+ ", tipo: " + Pedido.class.getName()));
	}

	public Pedido salvar(Pedido pedido) {
		
		pedido.setId(null);
		pedido.setInstance(new Date());
		pedido.setCliente(clienteService.buscar(pedido.getCliente().getId()));
		pedido.getPagamento().setEstadoPagamento(EstadoPagamento.PENDENTE);
		pedido.getPagamento().setPedido(pedido);
		
		if(pedido.getPagamento() instanceof PagamentoComBoleto) {
			PagamentoComBoleto boleto = (PagamentoComBoleto) pedido.getPagamento();
			boletoService.preencherPagamentoComBoleto(boleto, pedido.getInstance());
		}
		
		pedido = pedidoDao.save(pedido);
		pagamentoDao.save(pedido.getPagamento());
		
		for(ItemPedido ip : pedido.getItens()) {
			ip.setDesconto(0.0);
			ip.setProduto(produtoService.buscar(ip.getProduto().getId()));
			ip.setPreco(ip.getProduto().getPreco());
			ip.setPedido(pedido);
		}
		
		itemPedidoDao.saveAll(pedido.getItens());
		
		emailService.sendOrderConfirmationHtmlEmail(pedido);
		
		return pedido;
	}
	
}
