package com.zenika.resanet.service.impl;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.sql.Connection;

import org.junit.Before;
import org.junit.Test;

import com.zenika.dao.impl.OperatorDaoMock;
import com.zenika.resanet.dao.ConnectionManager;
import com.zenika.resanet.exception.OperatorNotFoundException;
import com.zenika.resanet.service.AuthenticationService;

public class AuthenticationServiceMockTest {

	private AuthenticationService authenticationService;
	private OperatorDaoMock operatorDao;
	private Connection cx;

	
	@Before
	public void setUp() throws Exception {
		operatorDao = new OperatorDaoMock();
		authenticationService = new AuthenticationServiceImpl(operatorDao);
		cx = ConnectionManager.getInstance().getConnection();

	}

	@Test
	public void testFindByLoginOK() {
		try{
			authenticationService.findByLogin(cx, "admin");
			assertEquals(1,operatorDao.getFindByLoginCount());
		}catch(OperatorNotFoundException exception){
			fail("Operator \"admin\" not found");
		}
		
		try{
			authenticationService.findByLogin(cx, "Pierre");
			assertEquals(2,operatorDao.getFindByLoginCount());
		}catch(OperatorNotFoundException exception){
			fail("Operator \"Pierre\" not found");
		}
		
	}
	@Test
	public void testFindByLoginKO(){
		try{
			authenticationService.findByLogin(cx, "UserNotFound");
			fail();
		}catch(OperatorNotFoundException exception){
			assertEquals(0,operatorDao.getFindByLoginCount());
		}
	}
	
}
