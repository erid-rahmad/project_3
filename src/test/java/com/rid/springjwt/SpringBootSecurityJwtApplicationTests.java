package com.rid.springjwt;

import com.rid.springjwt.models.ReportFilter;
import com.rid.springjwt.models.Transaction;
import com.rid.springjwt.models.User;
import com.rid.springjwt.repository.TransactionRepository;
import com.rid.springjwt.repository.UserRepository;
import com.rid.springjwt.service.TransactionService;
import net.sf.jasperreports.engine.JRException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import static org.assertj.core.api.Assertions.assertThat;



import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.List;


@SpringBootTest
public class SpringBootSecurityJwtApplicationTests {

	@Autowired
	TransactionService transactionService;

	@Autowired
	UserRepository userRepository;

	@Autowired
	TransactionRepository transactionRepository;

	@Test
	public void transaksi() {
		transactionService.totalPoin("rid");
	}

	@Test
	public void history() {
		transactionService.history("rid");
	}

	@Test
	public void BeliPulsa() {
		Transaction transaction = new Transaction();
		List<User> user = userRepository.findAll();
		transaction.setName("PULSA");
		transaction.setUser(user.get(0));
		transaction.setNominal(new BigDecimal(111000));
		Transaction transaction1 = transactionService.BeliPulsa(transaction,"rid");
		assertThat(transaction1.getPoin()).isEqualByComparingTo(new BigDecimal(36));
	}

	@Test
	public void report() throws FileNotFoundException, JRException {

		ReportFilter reportFilter = new ReportFilter();
		transactionService.historyReport(reportFilter);
	}

}
