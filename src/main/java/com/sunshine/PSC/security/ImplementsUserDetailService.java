package com.sunshine.PSC.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.sunshine.PSC.dao.ClienteDao;
import com.sunshine.PSC.dominio.Cliente;

@Repository
@Transactional
public class ImplementsUserDetailService implements UserDetailsService {

	@Autowired
	private ClienteDao dao;
	
	@Override
	public UserDetails loadUserByUsername(String cpf) throws UsernameNotFoundException {
		
		Cliente cliente = dao.findByCpf(cpf);
		
		if(cliente == null) {
			
			throw new UsernameNotFoundException("cliente não encontrado!");
		}
		
		return new User(cliente.getUsername(), cliente.getPassword(), true, true, true, true, cliente.getAuthorities() );
	}

}
