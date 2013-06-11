package com.zenika.resanet.service.impl;

import java.sql.Connection;

import com.zenika.resanet.dao.OperatorDao;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.model.Operator;
import com.zenika.resanet.service.AuthenticationService;

public class AuthenticationServiceImpl implements AuthenticationService {

	OperatorDao operatorDao;
	
	public AuthenticationServiceImpl(OperatorDao operatorDao){
		this.operatorDao=operatorDao;
	}
	
	@Override
	public Operator authenticate(Connection cx, String login, String password)
			throws OperatorNotFoundException {
		Operator operator = this.findByLogin(cx, login);
		if ((operator != null) && (operator.getPassword() == null || operator
                .getPassword().equals(password))) {
            return operator;
        } else {
            //return null;
            throw new OperatorNotFoundException("Operator not found");
        }
	}

	@Override
	public Operator findByLogin(Connection cx, String login) throws OperatorNotFoundException {
		Operator operator = this.operatorDao.findByLogin(cx,login);
		if (operator == null)
            throw new OperatorNotFoundException("Operator not found");
        return operator;
	}

	@Override
	public void save(Connection cx, Operator operator) {
		this.operatorDao.save(cx,operator);

	}

}
