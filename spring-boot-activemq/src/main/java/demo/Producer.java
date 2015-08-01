package demo;

import javax.jms.Queue;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Component
public class Producer  {

	@Autowired
	private JmsTemplate jmsTemplate;

	@Autowired
	private Queue queue;

	public void send(String msg) {
		this.jmsTemplate.convertAndSend(this.queue, msg);
	}

}