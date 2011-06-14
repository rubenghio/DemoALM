package ar.com.demo;

import javax.ejb.Local;

@Local
public interface MessageManager {
	void findMessages();
	
	void select();
	
	void delete();
	
	void destroy();
	
	void save();
}
