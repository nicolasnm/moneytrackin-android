/**
 * 
 */
package com.notarios.dao.impl.online;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.client.RestTemplate;

import com.notarios.dao.TransactionDAO;
import com.notarios.model.Project;
import com.notarios.model.Transaction;

/**
 * @author nnotario
 *
 */
public class TransactionOnlineDAO extends MoneytrackinOnlineDAO  implements TransactionDAO {
	private final String listTransactionsUrl = "https://www.moneytrackin.com/api/rest/listTransactions";
	private final String insertTransactionsUrl = "https://www.moneytrackin.com/api/rest/insertTransaction";
	private final String editTransactionsUrl = "https://www.moneytrackin.com/api/rest/editTransaction";
	private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd"); 
	public TransactionOnlineDAO(final String username, final String password) {
		super(username, password);
	}

	/* (non-Javadoc)
	 * @see com.notarios.dao.TransactionDAO#listTransactions(com.notarios.model.Project)
	 */
	public List<Transaction> listTransactions(Project p) {
		Map<String, String> variables = new HashMap<String, String>();
        variables.put("project", (p.getId() == 0) ? "" : String.valueOf(p.getId()));
        variables.put("endDate", dateFormatter.format(Calendar.getInstance().getTime()));
        variables.put("startDate", "2011-09-01");
		
        
               
        final List<Transaction> transactions = new ArrayList<Transaction>();
		// Calls the service and parses the result into a ListProjectsResult class
        final ListTransactionsResult transactionResult = getRestTemplate().getForObject(listTransactionsUrl+"?project={project}&startDate={startDate}&endDate={endDate}", ListTransactionsResult.class, variables);
		for (final MoneytrackinTransaction mtp : transactionResult.getTransactions()) {
			final Transaction t = new Transaction();
			t.setAmount(mtp.getAmount());
			t.setId(Integer.valueOf(mtp.getId()));
			t.setDescription(mtp.getDescription());
			if (mtp.getTags() != null) {
				for (String tag : mtp.getTags()) {
					t.getTags().add(tag);
				}
			}
			Calendar transactionDate = Calendar.getInstance();
			try {
				transactionDate.setTime(dateFormatter.parse(mtp.getDate()));
			} catch (ParseException e) {
			}
			t.setDate(transactionDate);
			transactions.add(t);
		}
		
        return transactions;
	}

	/* (non-Javadoc)
	 * @see com.notarios.dao.TransactionDAO#addTransaction(com.notarios.model.Transaction, com.notarios.model.Project)
	 */
	public void addTransaction(Transaction t, Project p) throws RuntimeException {
		Map<String, String> variables = new HashMap<String, String>();
        variables.put("project", (p.getId() == 0) ? "" : String.valueOf(p.getId()));
        variables.put("description", t.getDescription());
        variables.put("amount", String.valueOf(t.getAmount()));
        variables.put("date", dateFormatter.format(t.getDate().getTime()));
        StringBuffer tags = new StringBuffer();
        int i = 0;
        for (String tag : t.getTags()) {
        	if (i > 0) {
        		tags.append(" ");
        	}
        	tags.append(tag);
        	i++;
        }
        variables.put("tags", tags.toString());
        
		// Calls the service and parses the result into a String
        final MoneytrackinResult transactionResult = getRestTemplate().getForObject(insertTransactionsUrl+"?project={project}&description={description}&date={date}&amount={amount}&tags={tags}", MoneytrackinResult.class, variables);
        if (!transactionResult.isOk()) {
        	throw new RuntimeException ("Error adding transaction: " + transactionResult);
        }
	}

	/* (non-Javadoc)
	 * @see com.notarios.dao.TransactionDAO#saveTransaction(com.notarios.model.Transaction, com.notarios.model.Project)
	 */
	public void saveTransaction(Transaction t, Project p) {
		Map<String, String> variables = new HashMap<String, String>();
        variables.put("project", (p.getId() == 0) ? "" : String.valueOf(p.getId()));
        variables.put("description", t.getDescription());
        variables.put("amount", String.valueOf(t.getAmount()));
        variables.put("date", dateFormatter.format(t.getDate().getTime()));
        variables.put("transaction", String.valueOf(t.getId()));
        StringBuffer tags = new StringBuffer();
        int i = 0;
        for (String tag : t.getTags()) {
        	if (i > 0) {
        		tags.append(" ");
        	}
        	tags.append(tag);
        	i++;
        }
        variables.put("tags", tags.toString());
        
		// Calls the service and parses the result into a String
        final MoneytrackinResult transactionResult = getRestTemplate().getForObject(editTransactionsUrl+"?transactionID={transaction}&projectID={project}&description={description}&date={date}&amount={amount}&tags={tags}", MoneytrackinResult.class, variables);
        if (!transactionResult.isOk()) {
        	throw new RuntimeException ("Error adding transaction: " + transactionResult);
        }
	}

}
