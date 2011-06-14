package ar.com.demo;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import junit.framework.Assert;

import org.easymock.EasyMock;
import org.easymock.IMocksControl;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class MessageManagerTest {
	private Message message;
	
	@Before
	public void setup() {
		message = new Message();
		message.setDatetime(new Date(System.currentTimeMillis()));
		message.setId(new Long(1));
		message.setRead(false);
		message.setText("Text");
		message.setTitle("Title");
	}
	
	@Test
	public void findMessageTest() {
		IMocksControl emCtlr = EasyMock.createNiceControl();
		IMocksControl queryCtlr = EasyMock.createNiceControl();
		EntityManager em = emCtlr.createMock(EntityManager.class);
		Query query = queryCtlr.createMock(Query.class);
		em.createQuery("select msg from Message msg order by msg.datetime desc");
		EasyMock.expectLastCall().andReturn(query);
		List<Message> list = new ArrayList<Message>();
		list.add(message);
		query.getResultList();
		EasyMock.expectLastCall().andReturn(list);

		emCtlr.replay();
		queryCtlr.replay();
		
		MessageManagerBean mb = new MessageManagerBean();
		mb.setEm(em);
		mb.findMessages();
		
		Assert.assertEquals(mb.getMessageList().size(), list.size());
		
		EasyMock.verify(em);
		EasyMock.verify(query);
	}
	
	@Test
	public void select() {
		Assert.assertFalse(message.isRead());
		MessageManagerBean mb = new MessageManagerBean();
		mb.setMessage(message);
		mb.select();
		Assert.assertTrue(message.isRead());
	}

	@Test
	public void delete() {
		List<Message> messageList = new ArrayList<Message>();
		messageList.add(message);
		MessageManagerBean mb = new MessageManagerBean();
		mb.setMessageList(messageList);
		mb.setMessage(message);
		Assert.assertEquals(messageList.size(), mb.getMessageList().size());
		IMocksControl emCtlr = EasyMock.createNiceControl();
		EntityManager em = emCtlr.createMock(EntityManager.class);
		mb.setEm(em);
		em.remove(message);
		EasyMock.expectLastCall();
		
		emCtlr.replay();
		
		mb.delete();
		
		EasyMock.verify(em);
		
		Assert.assertEquals(0, mb.getMessageList().size());
	}

	@Test
	public void save() {
		MessageForm mForm = new MessageForm("TestSave", "Test saving process");
		List<Message> messageList = new ArrayList<Message>();
		MessageManagerBean mb = new MessageManagerBean();
		mb.setMessageList(messageList);
		mb.setMessageForm(mForm);
		Assert.assertTrue(messageList.isEmpty());
		IMocksControl emCtlr = EasyMock.createNiceControl();
		EntityManager em = emCtlr.createMock(EntityManager.class);
		mb.setEm(em);

		emCtlr.replay();
		
		mb.save();
		
		EasyMock.verify(em);
		
		Assert.assertEquals(1, mb.getMessageList().size());
	}
	
	@After
	public void clean() {
		message = null;
	}
}
