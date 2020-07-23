package webtopdfbot;
import java.io.File;
import java.util.Map;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import io.woo.htmltopdf.HtmlToPdf;
import io.woo.htmltopdf.HtmlToPdfObject;

public class webtopdfbot extends TelegramLongPollingBot {

	public String getBotUsername() {
		// TODO Auto-generated method stub
		return "bharath";
	}
	
	public SendDocument getdocument(long chatid,String url) {
		boolean success=HtmlToPdf.create()
			    .object(HtmlToPdfObject.forUrl(url)).convert("C:/Users/bhara/pdf/"+chatid+".pdf");
				
		File a=new File("C:/Users/bhara/pdf/"+chatid+".pdf");
		SendDocument sendDocumentRequest = new SendDocument();
		if(success) {
			System.out.println("sucess");
			 
			sendDocumentRequest.setChatId(chatid);
		   sendDocumentRequest.setDocument(a);
		   return sendDocumentRequest;
		 
		}
		else {
			return null;
		}
	
	}
	public void onUpdateReceived(Update update) { 
		if(update.getMessage()!=null&& update.getMessage().hasText()) {
			long chat_id=update.getMessage().getChatId();
			String url = update.getMessage().getText();
			SendDocument sendDocumentRequest = getdocument(chat_id, url);
			 if(sendDocumentRequest!=null) {
			try {
					
					execute(sendDocumentRequest);

				} catch (TelegramApiException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			 }
			 else {
				 try {
						
						execute(new SendMessage(chat_id," "+ " enter valid url"));

					} catch (TelegramApiException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				 
			 }
		}
		
	}

	@Override
	public String getBotToken() {
		// TODO Auto-generated method stub
		return "Enter your telegram bot id";
	}

}
