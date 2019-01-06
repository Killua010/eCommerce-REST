package br.com.ecommerce.services.validation;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import br.com.ecommerce.DAO.ClienteDAO;
import br.com.ecommerce.domain.Cliente;
import br.com.ecommerce.domain.enums.TipoCliente;
import br.com.ecommerce.dto.ClienteNovoDTO;
import br.com.ecommerce.resources.exception.MensagemDoCampo;
import br.com.ecommerce.services.validation.utils.BR;

public class ClienteInsertValidator implements ConstraintValidator<ClienteInsert, ClienteNovoDTO> {
	
	@Autowired
	private ClienteDAO clienteDao;

	@Override
	public void initialize(ClienteInsert ann) {

	}

	@Override
	public boolean isValid(ClienteNovoDTO objDto, ConstraintValidatorContext context) {
		List<MensagemDoCampo> list = new ArrayList<>();

		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAFISICA.getCod())
				&& !BR.isValidCPF(objDto.getCpfOuCnpj())) {
			list.add(new MensagemDoCampo("cpfOuCnpj", "CPF inválido"));
		}
		
		if(objDto.getTipoCliente().equals(TipoCliente.PESSOAJURIDICA.getCod())
				&& !BR.isValidCNPJ(objDto.getCpfOuCnpj())) {
			list.add(new MensagemDoCampo("cpfOuCnpj", "CNPJ inválido"));
		}
		
		Cliente aux = clienteDao.findByEmail(objDto.getEmail());
		
		if(null != aux) {
			list.add(new MensagemDoCampo("email", "Email já existente"));
		}

		for (MensagemDoCampo e : list) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(e.getMensagem()).addPropertyNode(e.getNomeCampo())
					.addConstraintViolation();
		}
		return list.isEmpty();
	}
}