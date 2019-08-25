package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.controller.Query13Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

@Service
public class Mailer {

    private final String userName;
    private final String password;
    private final String server;
    private final JavaMailSenderImpl mailSender;

    public Mailer(@Value("${mailer.server}") String server, @Value("${mailer.username}") String userName, @Value ("${mailer.password}") String password){
        this.userName = userName;
        this.password = password;
        this.server = server;
        this.mailSender = getJavaMailSender();
    }

    private final Logger log = LoggerFactory.getLogger(Query13Controller.class);



    public void sendMessage(String trxId, Map<String,String> attachments, String email){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeBodyPart body = new MimeBodyPart();
            body.setText("Transaction Id " + trxId + "\n");
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            for (Map.Entry<String, String> attachment : attachments.entrySet()) {
                MimeBodyPart mimeBodyPart = new MimeBodyPart();
                ByteArrayDataSource ds = new ByteArrayDataSource(attachment.getValue(), "text/plain");
                mimeBodyPart.setDataHandler(new DataHandler(ds));
                mimeBodyPart.setFileName(attachment.getKey());
                multipart.addBodyPart(mimeBodyPart);
            }
            mimeMessage.setContent(multipart);
        } catch (MessagingException | IOException e) {
            e.printStackTrace();
        }

        MimeMailMessage mmm = new MimeMailMessage(mimeMessage);

        mmm.setTo( email );
        mmm.setSubject("English Lexicon Project: Trx " + trxId);
        mmm.setFrom("bjorn.loftis@gmail.com");

        mailSender.send(mmm.getMimeMessage());
    }


    private JavaMailSenderImpl getJavaMailSender() {

        JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
        mailSender.setHost(server);
        mailSender.setPort(587);

        mailSender.setUsername(userName);
        mailSender.setPassword(password);

        Properties props = mailSender.getJavaMailProperties();
        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.debug", "false");

        return mailSender;
    }

}
