package ar.com.demo;

import org.jboss.seam.annotations.Name;

@Name("messageForm")
public class MessageForm {
	private String title;
	private String text;
	
	public MessageForm() {
	}

	public MessageForm(final String title, final String text) {
		this.title = title;
		this.text = text;
	}
	
	public String getTitle() {
		return title;
	}
	public void setTitle(final String title) {
		this.title = title;
	}
	public String getText() {
		return text;
	}
	public void setText(final String text) {
		this.text = text;
	}
}
