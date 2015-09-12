package demo;

import javax.jms.JMSException;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import static org.junit.Assert.assertTrue;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoActiveMqApplication.class)
public class DemoActiveMqApplicationTests {

	@Rule
	public OutputCapture outputCapture = 
				new OutputCapture();
	
	@Autowired
	private Producer producer;
	
	@Test
	public void sendSimpleMessage() throws InterruptedException, JMSException{
		this.producer.send("Test message");
		Thread.sleep(3000);
		assertTrue(this.outputCapture.toString().contains("Test message"));
	}
	
	
	
	
}
