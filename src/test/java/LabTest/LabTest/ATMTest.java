/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LabTest.LabTest;

import org.junit.Test;
import org.mockito.InOrder;
import org.mockito.Mockito;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

/**
 *
 * @author andrii
 */
public class ATMTest {

	@Test
	public void getMoneyInATMDoubleReturns() {
		double actualBalanse = 1000;
		double expectedBalanse;
		ATM atm = new ATM(actualBalanse);
		expectedBalanse = atm.getMoneyInATM();
		assertEquals(actualBalanse, expectedBalanse, 0.0000001);
	}
	@Test
	public void validateCardValidCardValidPinTRUE() {
		ATM atm = new ATM(1000);
		Card card = mock(Card.class);
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		assertTrue(atm.validateCard(card, 1111));
		verify(card,  atLeastOnce()).isBlocked();
		verify(card,  atLeastOnce()).checkPin(1111);
	}
	
	@Test
	public void validateCardInvalidCardValidPinFALSE() {
		ATM atm = new ATM(1000);
		Card card = mock(Card.class);
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(true);
		assertFalse(atm.validateCard(card, 1111));
		verify(card,  atLeastOnce()).isBlocked();
	}
	
	@Test
	public void validateCardValidCardInvalidPinFALSE() {
		ATM atm = new ATM(1000);
		Card card = mock(Card.class);
		when(card.checkPin(1111)).thenReturn(false);
		when(card.isBlocked()).thenReturn(false);
		assertFalse(atm.validateCard(card, 1111));
		verify(card,  atLeastOnce()).isBlocked();
		verify(card,  atLeastOnce()).checkPin(1111);
	}
	
	@Test 
	public void checkBalanseCardInATMDoubleReturns() throws NoCardInsertedException { 
		ATM atm = new ATM(1000);
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 100;
		double actualBalanse = 0;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111)); 

		actualBalanse = atm.checkBalanse();
		assertEquals(actualBalanse, expectedBalanse, 0.0000001);
		verify(account,  atLeastOnce()).getBalance();
	}
	
	@Test (expected = NoCardInsertedException.class)
	public void checkBalanseNoCardInATMNoCardInATMException() throws NoCardInsertedException {
		ATM atm = new ATM(1000);
		atm.checkBalanse();
	}
	
<<<<<<< HEAD

=======
	
>>>>>>> 2be7fcb0522b5460f199aff55db350e5c25d2d74
	
	@Test (expected = NoCardInsertedException.class)
	public void getCashNoCardInATMException() 
			throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM{
		ATM atm = new ATM(1000);
		atm.getCash(50);

	}
	
	@Test (expected = NotEnoughMoneyInAccount.class)
	public void getCashCardInATMEnoughtMoneyInATMNotEnoughtMoneyInAccountNotEnoughtMoneyInAccountException() 
			throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM{
		ATM atm = spy(new ATM(1000));
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 10;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111));
		
		atm.getCash(50);
	}
	
	@Test (expected = NotEnoughMoneyInATM.class)
	public void getCashCardInATMNotEnoughtMoneyInATMEnoughtMoneyInAccountNotEnoughtMoneyInATMexception() 
			throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM{
		ATM atm = spy(new ATM(1000));
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 10000;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111)); 
		
		atm.getCash(5000);
	}
	@Test 
	public void getCashCardInATMEnoughtMoneyInATMEnoughtMoneyInAccountDoubleReturns() 
			throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM{
		ATM atm = spy(new ATM(1000));
		Card card = mock(Card.class);
		Account account = mock(Account.class);
		double expectedBalanse = 1000;
		
		when(card.checkPin(1111)).thenReturn(true);
		when(card.isBlocked()).thenReturn(false);
		when(card.getAccount()).thenReturn(account);
		when(account.getBalance()).thenReturn(expectedBalanse);
		assertTrue(atm.validateCard(card, 1111)); 
		atm.getCash(1000);
		verify(account,  atLeastOnce()).getBalance();
		verify(atm,  atLeastOnce()).getMoneyInATM();
	}
	
	@Test 
	public void inRightOrder() throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM
	{ ATM atm = spy(new ATM(1000));
	Card card = mock(Card.class);
	Account account = mock(Account.class);
	double expectedBalanse = 60;
	double money=50;

	when(card.checkPin(1111)).thenReturn(true);
	when(card.isBlocked()).thenReturn(false);
	when(card.getAccount()).thenReturn(account);
	when(account.getBalance()).thenReturn(expectedBalanse);
	when(account.withdrow(money)).thenReturn(money);
	assertTrue(atm.validateCard(card, 1111)); 
	atm.getCash(money);
	InOrder order = Mockito.inOrder(account);
	order.verify(account).getBalance();
	order.verify(card.getAccount()).withdrow(money);
	}
	@Test 
	public void inRightOrderThreMethod() throws NoCardInsertedException, NotEnoughMoneyInAccount, NotEnoughMoneyInATM
	{ 
	ATM atm = spy(new ATM(1000));
	Card card = mock(Card.class);
	Account account = mock(Account.class);
	double expectedBalanse = 60;
	double money=50;

	when(card.checkPin(1111)).thenReturn(true);
	when(card.isBlocked()).thenReturn(false);
	when(card.getAccount()).thenReturn(account);
	when(account.getBalance()).thenReturn(expectedBalanse);
	when(account.withdrow(money)).thenReturn(money);
	assertTrue(atm.validateCard(card, 1111)); 
	atm.getCash(money);
	InOrder order = Mockito.inOrder(atm, account);
	order.verify(atm).getMoneyInATM();
	order.verify(account).getBalance();
	order.verify(card.getAccount()).withdrow(money);
	}
    
}
