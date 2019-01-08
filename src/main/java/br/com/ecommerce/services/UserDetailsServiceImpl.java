package br.com.ecommerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import br.com.ecommerce.DAO.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.security.UserSpringSecurity;

@Service
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private ClienteDAO clienteDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		Cliente cliente = clienteDao.findByEmail(email);
		
		if(null == cliente) {
			throw new UsernameNotFoundException(email);
		}
		
		return new UserSpringSecurity(cliente.getId(), cliente.getEmail(), cliente.getSenha(), cliente.getPerfis());
	}

}
