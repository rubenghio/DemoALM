package ar.com.demo;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageFormTest {
	private MessageForm form;
	
	@Before
	public void setup() {
		form = new MessageForm("Title example", "Text example");
	}
	
	@Test
	public void getTitle() {
		Assert.assertEquals("Title example", form.getTitle());
	}

	@Test
	public void setTitle() {
		Assert.assertEquals("Title example", form.getTitle());
		form.setTitle("New title");
		Assert.assertEquals("New title", form.getTitle());
	}
	
	@Test
	public void getText() {
		Assert.assertEquals("Text example", form.getText());
	}

	@Test
	public void setText() {
		Assert.assertEquals("Text example", form.getText());
		form.setText("New text");
		Assert.assertEquals("New text", form.getText());
	}
	
	@After
	public void clean() {
		form = null;
	}
}
