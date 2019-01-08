package br.com.ecommerce.security;

import java.io.IOException;
import java.util.ArrayList;

import javax.management.RuntimeErrorException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.ecommerce.dto.CredenciaisDTO;

public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter{

	private JWTUtil jwtUtil;
	
	private AuthenticationManager authenticationManager;
	
	@Autowired
	public JWTAuthenticationFilter(JWTUtil jwtUtil, AuthenticationManager authenticationManager) {
		this.jwtUtil = jwtUtil;
		this.authenticationManager = authenticationManager;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response)
			throws AuthenticationException {
		try {
			CredenciaisDTO credenciaisDTO = new ObjectMapper()
					.readValue(request.getInputStream(), CredenciaisDTO.class);
			
			UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(
					credenciaisDTO.getEmail(), credenciaisDTO.getSenha(), new ArrayList<>());
			
			Authentication authentication = authenticationManager.authenticate(authenticationToken);
			
			return authentication;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}

	}

	@Override
	protected void successfulAuthentication(HttpServletRequest request, HttpServletResponse response, FilterChain chain,
			Authentication authResult) throws IOException, ServletException {
		
		String username = ((UserSpringSecurity) authResult.getPrincipal()).getUsername();
		String token = jwtUtil.generateToken(username);
		response.addHeader("Authorization", "Bearer " + token );
	}

	
	
}
