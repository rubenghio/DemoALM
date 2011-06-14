package ar.com.demo;

import java.util.Date;
import java.util.List;

import javax.ejb.Remove;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;
import javax.persistence.Query;

import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Factory;
import org.jboss.seam.annotations.In;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Out;
import org.jboss.seam.annotations.Scope;
import org.jboss.seam.annotations.datamodel.DataModel;
import org.jboss.seam.annotations.datamodel.DataModelSelection;

@Stateful
@Scope(ScopeType.SESSION)
@Name("messageManager")
public class MessageManagerBean implements MessageManager {
	@In (create = true)
	private MessageForm messageForm;
	
	@DataModel
	private List<Message> messageList;
	
	@DataModelSelection
	@Out(required = false)
	private Message message;
	
	@PersistenceContext(unitName="DemoPU", type=PersistenceContextType.EXTENDED)
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Factory("messageList")
	public void findMessages() {
		Query query = em.createQuery("select msg from Message msg order by msg.datetime desc");
		messageList = query.getResultList();
	}

	public void select() {
		message.setRead(true);
	}

	public void delete() {
		messageList.remove(message);
		em.remove(message);
		message = null;
	}

	@Remove
	public void destroy() {
	}

	@Override
	public void save() {
		Message m = new Message();
		m.setText(messageForm.getText());
		m.setTitle(messageForm.getTitle());
		m.setDatetime(new Date(System.currentTimeMillis()));
		m.setRead(false);
		messageList.add(m);
		em.persist(m);
	}

	public void setEm(EntityManager em) {
		this.em = em;
	}

	public void setMessageList(List<Message> messageList) {
		this.messageList = messageList;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public List<Message> getMessageList() {
		return messageList;
	}

	public Message getMessage() {
		return message;
	}

	public MessageForm getMessageForm() {
		return messageForm;
	}

	public void setMessageForm(MessageForm messageForm) {
		this.messageForm = messageForm;
	}
}