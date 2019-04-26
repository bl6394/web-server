package edu.wustl.elexicon.webserver.service;

import edu.wustl.elexicon.webserver.web.controller.Query13Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.activation.DataHandler;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.mail.util.ByteArrayDataSource;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
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



    public void sendMessage(String trxId, String csv, String email){

        MimeMessage mimeMessage = mailSender.createMimeMessage();
        try {
            MimeBodyPart body = new MimeBodyPart();
            body.setText("Transaction Id " + trxId + "\n");
            MimeBodyPart attachment = new MimeBodyPart();
            Multipart multipart = new MimeMultipart();
            multipart.addBodyPart(body);
            ByteArrayDataSource ds = new ByteArrayDataSource(csv, "text/plain");
            attachment.setDataHandler(new DataHandler(ds));
            attachment.setFileName("I-" + trxId + ".txt");
            multipart.addBodyPart(attachment);
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
