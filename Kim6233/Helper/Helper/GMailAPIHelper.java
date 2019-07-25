package Helper;

import java.util.Properties;

import javax.mail.Folder;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Store;

import Common.Constant;

public class GMailAPIHelper {

	public String getEmailContent(String emailSubject, String emailAddress) {
		String returnedLink = null;
		try {
			Properties props = new Properties();
			// set email protocol to IMAP
			props.put("mail.store.protocol", "imaps");
			// set up the session
			Session session = Session.getInstance(props);
			Store store = session.getStore("imaps");
			// Connect to your email account
			store.connect("imap.gmail.com", Constant.GMAIL_ADMIN_USERNAME, Constant.GMAIL_ADMIN_PASSWORD);
			// Get reference to your INBOX
			Folder folder = store.getFolder("INBOX");
			// Open the folder in READ MODE only
			folder.open(Folder.READ_ONLY);
			// Get all the messages in INBOX into Message array
			Message[] messages = folder.getMessages();
			// Loop through all the messages in your INBOX
			for (int i = messages.length - 1; i > 0; i--) {
				Message msg = messages[i];
				String mailSubject = msg.getSubject();
				String mailContent = msg.getContent().toString();
				// Find mail contains input registerMail
				if (mailSubject.contains(emailSubject + " " + emailAddress)) {
					returnedLink = mailContent;
					break;
				}
			}

		} catch (MessagingException ME) {
			ME.printStackTrace();
		} catch (Exception E) {
			E.printStackTrace();
		}
		return returnedLink;
	}

	public String getActivationLink(String registeredEmail) {
		String emailSubject = "Please confirm your account";

		String returnedActivationLink = getEmailContent(emailSubject, registeredEmail);

		return returnedActivationLink.substring(returnedActivationLink.indexOf("http"),
				returnedActivationLink.indexOf(">") - 1);
	}

	public String getResetPasswordLink(String resetEmail) {
		String emailSubject = "Please reset your password";
		String returnedResetPasswordLink = getEmailContent(emailSubject, resetEmail);

		return returnedResetPasswordLink.substring(returnedResetPasswordLink.indexOf("http"),
				returnedResetPasswordLink.indexOf(">") - 1);
	}

	public String getActivationCode(String registeredEmail) {
		String emailSubject = "Please confirm your account";
		String returnedActivationCode = getEmailContent(emailSubject, registeredEmail);
		
		return returnedActivationCode.substring("Your confirmation code is: ".length(),
				returnedActivationCode.indexOf(". Visit"));
	}

}
