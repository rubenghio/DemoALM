package ar.com.demo;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.validator.Length;
import org.hibernate.validator.NotNull;
import org.jboss.seam.ScopeType;
import org.jboss.seam.annotations.Name;
import org.jboss.seam.annotations.Scope;

@Entity
@Name("message")
@Scope(ScopeType.EVENT)
public class Message implements Serializable {
	private static final long serialVersionUID = -1273356850693151794L;
	private static final int LENGTH = 100;
	
	private Long id;
	private String title;
	private String text;
	private boolean read;
	private Date datetime;

	public Message() {
	}

	public Message(final Long id, final String title, final String text,
			final boolean read, final Date datetime) {
		this.id = id;
		this.title = title;
		this.text = text;
		this.read = read;
		this.datetime = datetime;
	}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	@NotNull
	@Length(max = LENGTH)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@NotNull
	@Lob
	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	@NotNull
	public boolean isRead() {
		return read;
	}

	public void setRead(boolean read) {
		this.read = read;
	}

	@NotNull
	@Basic
	@Temporal(TemporalType.TIMESTAMP)
	public Date getDatetime() {
		return datetime;
	}

	public void setDatetime(Date datetime) {
		this.datetime = datetime;
	}
}