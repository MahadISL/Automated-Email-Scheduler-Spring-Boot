package com.evamp.saanga.bankmanagement.service;

import com.evamp.saanga.bankmanagement.exception.CustomException;
import com.evamp.saanga.bankmanagement.model.Transaction;
import com.evamp.saanga.bankmanagement.model.User;
import com.evamp.saanga.bankmanagement.repository.TransactionRepo;
import com.evamp.saanga.bankmanagement.repository.UserRepo;
import com.evamp.saanga.bankmanagement.requestresponse.EmailResponse;
import com.evamp.saanga.bankmanagement.requestresponse.SendMoneyRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import java.io.File;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

//import static org.apache.poi.ss.util.CellUtil.createCell;

@Service
public class UserService {

    //findlist of transactions

    @Autowired
    ExcelService excelService;


    @Autowired
    UserRepo userRepo;

    @Autowired
    TransactionRepo transactionRepo;

    @Autowired
    EmailService emailService;

    @Autowired
    EmailResponse emailResponse;


    public long calculateTransactionAverage(List<Transaction> transactions){
        long totalAmount = transactions.stream().mapToInt(val -> val.getAmount()).sum();
        System.out.println(totalAmount);
        long totalTranscationsOneUser = transactions.stream().count();
        System.out.println(totalTranscationsOneUser);
        long averageTransaction = totalAmount/totalTranscationsOneUser;
        System.out.println(averageTransaction);
        return averageTransaction;
    }

    public void automatedEmailSchedule() throws IOException, MessagingException {
        List<User> users = userRepo.findAll();
        for(int i=0; i< users.size();i++){

            Integer accountNo = users.get(i).getaccountNo();
            Iterable<Integer> idList = Collections.singleton(accountNo);

            List<Transaction> transactionList = transactionRepo.findAllByObjAccountNoIn(idList);
            System.out.println(transactionList);
            excelService.exportExcel(transactionList, i);
            System.out.println(transactionList);

            long transactionAverage = calculateTransactionAverage(transactionList);

            User obj = userRepo.findEmailByAccountNo(accountNo);
            String email = obj.getEmail();;

            String message = "Good morning";
            emailResponse.setMessage(message);
            String firstName = users.get(i).getFirstName();
            emailResponse.setFirstName(firstName);
            String lastName = users.get(i).getLastName();
            emailResponse.setLastName(lastName);
            emailResponse.setTransactionAverage(String.valueOf(transactionAverage));

            //File object in current directory
            File currDir = new File(".");
            String path = currDir.getAbsolutePath();
            System.out.println(path);
            String fileLocation = path.substring(0, path.length() - 1) + i + "temp.xlsx";
            System.out.println(fileLocation);


            File file = new File(fileLocation);
            System.out.println(file);

//            emailService.sendEmail(email,"Automated Email to User", emailResponse.toString());
            emailService.sendAttachmentEmail(email, email, "Automated Email to User",emailResponse.toString(),file);
        }

    }

    public Boolean calculateBalance(User user, SendMoneyRequest request){

        // sender balance (user)
        Double currentBalance = user.getBalance();

        Integer amountTransferred = request.getAmount();

        // receiver balance
        User user1 = userRepo.findByAccountNo(request.getReceiverAccountNo());
        Double currentBalance1 = user1.getBalance();

        if (currentBalance >= amountTransferred){
            // Calculating and updating new balance in user account
            currentBalance = currentBalance - amountTransferred;
            user.setBalance(currentBalance);
            userRepo.save(user);

            // Calculating and updating new balance in receiver account
            currentBalance1 = currentBalance1 + amountTransferred;
            user1.setBalance(currentBalance1);
            userRepo.save(user1);

            return true;
        }
        else {
            return false;
        }
    }



}
