package demo;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertThat;
import java.io.StringReader;

import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.OutputCapture;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.ws.client.core.WebServiceTemplate;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DemoSpringWsApplication.class)
@WebAppConfiguration
@IntegrationTest("server.port=0")
public class DemoSpringWsApplicationTests {

	@Rule
	public OutputCapture output = new OutputCapture();
	
	private WebServiceTemplate webServiceTemplate =
			new WebServiceTemplate();
	
	@Value("${local.server.port}")
	private int serverPort;
	
	@Before
	public void setup(){
		this.webServiceTemplate.setDefaultUri("http://localhost:" + this.serverPort
				+ "/services/");
	}

	
	@Test
	public void testSendingHolidayRequest() {
		final String request = "<hr:HolidayRequest xmlns:hr=\"http://joedayz.pe/hr/schemas\">"
				+ "   <hr:Holiday>"
				+ "      <hr:StartDate>2013-10-20</hr:StartDate>"
				+ "      <hr:EndDate>2013-11-22</hr:EndDate>"
				+ "   </hr:Holiday>"
				+ "   <hr:Employee>"
				+ "      <hr:Number>1</hr:Number>"
				+ "      <hr:FirstName>John</hr:FirstName>"
				+ "      <hr:LastName>Doe</hr:LastName>"
				+ "   </hr:Employee>"
				+ "</hr:HolidayRequest>";

		StreamSource source = new StreamSource(new StringReader(request));
		StreamResult result = new StreamResult(System.out);

		this.webServiceTemplate.sendSourceAndReceiveToResult(source, result);
		assertThat(this.output.toString(), 
				containsString("Booking holiday for"));
	}

	
	
	
	
}
