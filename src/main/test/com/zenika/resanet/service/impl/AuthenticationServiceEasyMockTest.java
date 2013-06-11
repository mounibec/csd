package com.zenika.resanet.service.impl;

import static org.easymock.EasyMock.*;
import static org.junit.Assert.fail;


import org.junit.Before;
import org.junit.Test;

import com.zenika.resanet.dao.OperatorDao;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.model.Operator;
import com.zenika.resanet.service.AuthenticationService;



public class AuthenticationServiceEasyMockTest {

	private AuthenticationService authenticationService;
	private OperatorDao operatorDao;
	
	@Before
	public void setUp() throws Exception {
		operatorDao = createMock(OperatorDao.class);
		authenticationService = new AuthenticationServiceImpl(operatorDao);
	}
	@Test
	public void testFindByLoginOK() {
		
		
		
		expect(operatorDao.findByLogin(null, "admin")).andReturn(new Operator());
		replay(operatorDao);
		try{
			authenticationService.findByLogin(null, "admin");
		}catch(OperatorNotFoundException exception){
			fail("Operator \"admin\" not found");
		}
		verify(operatorDao);
	}
	
}
