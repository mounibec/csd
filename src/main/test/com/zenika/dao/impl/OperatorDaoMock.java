package com.zenika.dao.impl;

import java.sql.Connection;

import com.zenika.resanet.dao.OperatorDao;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.model.Operator;

public class OperatorDaoMock implements OperatorDao{

	private int findByLoginCount=0;;
	
	@Override
	public Operator findByLogin(Connection cx, String login) {
		if("admin".equals(login)||"Pierre".equals(login)){
			this.findByLoginCount++;
			return new Operator();
		}else{
			throw new OperatorNotFoundException();
		}
		
	}

	@Override
	public void save(Connection cx, Operator operator) {
		// TODO Auto-generated method stub
		
	}
	
	public int getFindByLoginCount(){
		return this.findByLoginCount;
	}

}
