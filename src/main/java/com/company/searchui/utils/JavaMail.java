package com.company.searchui.utils;


import javax.mail.*;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * JavaMail Class
 *
 * @author phildolganov
 *
 */
public class JavaMail {

    /**
     * getGmailMessage - method to get the gmail message by username, password, and email account
     *
     * @param username
     * @param password
     * @param subject
     * @param email
     * @return Message
     * @throws Exception
     */
    public static Message getGmailMessage(String username, String password, String subject, String email) throws Exception {
        String toField = null, subjectField = null;
        int iterations = 1;
        int waitLimit = 1;
        Message getMessage = null;
        Session session = null;
        Store store = null;
        Properties props = System.getProperties();

        // props to access google mail server
        props.setProperty("mail.store.protocol", "imaps");
        props.setProperty("mail.imap.ssl.enable", "true");
        props.setProperty("mail.imap.port","993");

        session = Session.getInstance(props, null);
        store = session.getStore("imaps");
        store.connect("imap.gmail.com", username, password);

        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        // for each loop iteration, get all the Inbox messages again...

        while (iterations <= waitLimit){
            Message[] messages = null;
            messages = folder.getMessages();

            // query emails by to and subject fields
            for (Message message : messages) {
                toField = message.getHeader("To")[0];
                subjectField = message.getSubject();

                if (toField.equalsIgnoreCase(email) && subjectField.equals(subject)){
                    getMessage = message;
                    break;
                }
            }

            // wait a second and return loop if not found
            if (getMessage == null){
                CreateDriver.getInstance().driverWait(Global_VARS.TIMEOUT_SECOND);
                iterations++;
            } else {
                break;
            }
        }

        // return message or throw exception if not found
        if (getMessage != null){
            return getMessage;
        } else {
            throw new Exception("The Email Message was Not found!");
        }
    }

    /**
     * getMsgContent - method to verify the content of a gmail message
     *
     * @param username
     * @param password
     * @param subject
     * @param to
     * @return String
     * @throws Exception
     */
    public static String  getMsgContent(String username, String password, String subject, String to) throws Exception {
        Message message = getGmailMessage(username, password, subject, to);

        String line;
        StringBuffer buffer = new StringBuffer();
        BufferedReader reader = new BufferedReader(new InputStreamReader(message.getInputStream()));

        while ((line = reader.readLine()) != null){
            buffer.append(line);
        }
        return buffer.toString();
    }

    /**
     * getMsgLink - method to get the link in the gmail message
     *
     * @param username
     * @param password
     * @param subject
     * @param to
     * @return String
     * @throws Exception
     */
    public static String getMsgLink(String username, String password, String subject, String to) throws Exception {

        String content = getMsgContent(username, password, subject, to);

        // get email url link
        Pattern pattern = Pattern.compile("href=\"(.*?)\"", Pattern.DOTALL);
        Matcher match = pattern.matcher(content);
        String regURL = null; // URL from email content

        while (match.find()){
            regURL = match.group(1);
        }
        return regURL;
    }

    /**
     * deleteEmails - method to delete all emails using username and password
     *
     * @param username
     * @param password
     * @throws Exception
     */
    public static void deleteEmails(String username, String password) throws Exception {

        // props to access google mail server
        Properties props = System.getProperties();
        props.setProperty("mail.store.protocol", "imaps");

        Session session = Session.getDefaultInstance(props, null);
        Store store = session.getStore("imaps");
        store.connect("imap.gmail.com", username, password);

        // get all emails in the inbox
        Folder folder = store.getFolder("INBOX");
        folder.open(Folder.READ_WRITE);

        Message[] messages = null;
        messages = folder.getMessages();

        for (int i = 0; i < messages.length; i++) {
            messages[i].setFlag(Flags.Flag.DELETED, true);
        }
        folder.close(true);
    }
}
