/**
 * 
 */
package com.notarios.test;

import java.util.Calendar;
import java.util.List;

import com.notarios.dao.ProjectDAO;
import com.notarios.dao.TransactionDAO;
import com.notarios.dao.impl.online.ProjectOnlineDAO;
import com.notarios.dao.impl.online.TransactionOnlineDAO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

import junit.framework.Assert;
import junit.framework.TestCase;

/**
 * @author nnotario
 *
 */
public class TestTransactions extends TestCase {
	Project p = new Project();
	TransactionDAO transactionDao = null;
	ProjectDAO projectDao = null;

	/* (non-Javadoc)
	 * @see junit.framework.TestCase#setUp()
	 */
	protected void setUp() throws Exception {
		super.setUp();
		projectDao = new ProjectOnlineDAO("nicolasnotario", "cac21b2f8af71ab99bd389ad17fb3dc5");
		transactionDao = new TransactionOnlineDAO("nicolasnotario", "cac21b2f8af71ab99bd389ad17fb3dc5");
		p = projectDao.getProjects().get(0);
	}


/*	public void testAddTransaction() {
		List<Transaction> transcationsBefore = transactionDao.listTransactions(p);
		Transaction t = new Transaction();
		t.setAmount(10.0);
		t.setDate(Calendar.getInstance());
		t.setDescription("TEST");
		transactionDao.addTransaction(t, p);
		List<Transaction> transcationsAfter = transactionDao.listTransactions(p);
		Assert.assertEquals(transcationsBefore.size() +1 , transcationsAfter.size());
		
	}
	*/
	public void testEditTransaction() {
		List<Transaction> transcationsBefore = transactionDao.listTransactions(p);
		Transaction t = transcationsBefore.get(0);
		double oldAmount = t.getAmount();
		t.setAmount(oldAmount + 1.0);
		long oldMillis = t.getDate().getTimeInMillis();
		t.getDate().add(Calendar.DATE, -1);
		String oldDescription = t.getDescription();
		t.setDescription("1" + t.getDescription());
		int oldNumberOfTags = t.getTags().size();
		t.getTags().add("testtag2");
		
		transactionDao.saveTransaction(t, p);
		
		transcationsBefore = transactionDao.listTransactions(p);
		for (Transaction tL : transcationsBefore) {
			if (tL.getId() == t .getId()) {
				t = tL;
				break;
			}
		}
		
		Assert.assertEquals(oldAmount + 1.0, t.getAmount());
		Assert.assertFalse(oldMillis == t.getDate().getTimeInMillis());
		Assert.assertEquals("1" + oldDescription, t.getDescription());
		Assert.assertEquals(oldNumberOfTags + 1, t.getTags().size());
		
		
	}
	
}
