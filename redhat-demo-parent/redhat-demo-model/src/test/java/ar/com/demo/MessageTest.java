package ar.com.demo;

import java.util.Date;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageTest {
	private Message message;
	private static final Date datetime = new Date(System.currentTimeMillis());
	
	@Before
	public void setup() {
		message = new Message(new Long(1), "Title example", "Text example",
				false, datetime);
	}
	
	@Test
	public void getTitle() {
		Assert.assertEquals("Title example", message.getTitle());
	}

	@Test
	public void setTitle() {
		Assert.assertEquals("Title example", message.getTitle());
		message.setTitle("New title");
		Assert.assertEquals("New title", message.getTitle());
	}
	
	@Test
	public void getText() {
		Assert.assertEquals("Text example", message.getText());
	}

	@Test
	public void setText() {
		Assert.assertEquals("Text example", message.getText());
		message.setText("New text");
		Assert.assertEquals("New text", message.getText());
	}

	@Test
	public void getId() {
		Assert.assertEquals(new Long(1), message.getId());
	}

	@Test
	public void setId() {
		Assert.assertEquals(new Long(1), message.getId());
		message.setId(new Long(2));
		Assert.assertEquals(new Long(2), message.getId());
	}
	
	@Test
	public void isRead() {
		Assert.assertFalse(message.isRead());
	}

	@Test
	public void setRead() {
		Assert.assertFalse(message.isRead());
		message.setRead(true);
		Assert.assertTrue(message.isRead());
	}

	@Test
	public void getDatetime() {
		Assert.assertEquals(datetime, message.getDatetime());
	}

	@Test
	public void setDatetime() {
		Assert.assertEquals(datetime, message.getDatetime());
		Date newDate = new Date(System.currentTimeMillis());
		message.setDatetime(newDate);
		Assert.assertEquals(newDate, message.getDatetime());
	}
	
	@After
	public void clean() {
		message = null;
	}
}
