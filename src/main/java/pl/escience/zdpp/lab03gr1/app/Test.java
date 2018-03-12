package pl.escience.zdpp.lab03gr1.app;

import org.apache.commons.mail.DefaultAuthenticator;
import org.apache.commons.mail.Email;
import org.apache.commons.mail.EmailException;
import org.apache.commons.mail.SimpleEmail;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.Properties;

public class Test {
    public static void main (String []args){
        /*
        Email email = new SimpleEmail();
        email.setSmtpPort(587);
        email.setAuthenticator(new DefaultAuthenticator("wishesreminder@outlook.com",
                "Mojehaslo123$"));
        email.setDebug(false);
        email.setHostName("smtp.live.com");
        email.setStartTLSEnabled(true);
        try {
            email.setFrom("wishesreminder@onet.pl");
            email.setSubject("test");
            email.setMsg("testowa wiadomosc");
            email.addTo("patrykz8@o2.pl");

            email.send();
            System.out.println("SENT");
        } catch (EmailException e) {
            e.printStackTrace();
        }
        */

        final String username = "wishesreminder@onet.pl";
        final String password = "Mojehaslo123$";

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", "outlook.office365.com");
       // props.put("mail.smtp.port", "587");
        props.put("smtp.poczta.onet.pl", "465");


        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(username, password);
                    }
                });

        try {

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("wishesreminder@onet.pl"));
            message.setRecipients(Message.RecipientType.TO,
                    InternetAddress.parse("patrykz8@o2.pl"));
            message.setSubject("Test");
            message.setText("HI");

            Transport.send(message);

            System.out.println("Done");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }


    }
}
