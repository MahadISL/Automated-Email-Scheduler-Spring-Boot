package com.evamp.saanga.bankmanagement;

import com.evamp.saanga.bankmanagement.model.Transaction;
import com.evamp.saanga.bankmanagement.model.User;
import com.evamp.saanga.bankmanagement.repository.TransactionRepo;
import com.evamp.saanga.bankmanagement.repository.UserRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.time.LocalDateTime;
@EnableScheduling
@SpringBootApplication
public class BankmanagementApplication implements CommandLineRunner {

	@Autowired
	UserRepo userRepo;

	@Autowired
	TransactionRepo transactionRepo;

	public static void main(String[] args) {
		SpringApplication.run(BankmanagementApplication.class, args);
	}

	@Override
	public void run(String[] args) throws Exception
	{
		// User and transactions

//		User user = new User(1,"Mahad","Gul", "softdevelop8@gmail.com", 34582);
//		userRepo.save(user);
//		Transaction transaction = new Transaction(1, LocalDateTime.now().plusMinutes(1),20000,234,7463,user);
//		transactionRepo.save(transaction);
//		Transaction transaction1 = new Transaction(2, LocalDateTime.now().plusMinutes(2), 30000,742,5729,user);
//		transactionRepo.save(transaction1);
//
//		User user1 = new User(2,"Yusuf","Abbas","softdevelop8@gmail.com",128256);
//		userRepo.save(user1);
//		Transaction transaction2 = new Transaction(3, LocalDateTime.now().plusMinutes(3), 30000, 472,4720, user1);
//		transactionRepo.save(transaction2);
//		Transaction transaction3 = new Transaction(4, LocalDateTime.now().plusMinutes(2), 50000, 587,8279, user1);
//		transactionRepo.save(transaction3);
//
//		User user2 = new User(3,"Ali","Satti","softdevelop8@gmail.com",13856);
//		userRepo.save(user2);
//		Transaction transaction4 = new Transaction(5, LocalDateTime.now().plusMinutes(6), 15000, 232,94974, user2);
//		transactionRepo.save(transaction4);
//		Transaction transaction5 = new Transaction(6, LocalDateTime.now().plusMinutes(10), 25000, 483,96274, user2);
//		transactionRepo.save(transaction5);
	}

}
