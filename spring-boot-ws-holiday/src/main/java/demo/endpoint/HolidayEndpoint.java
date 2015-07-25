package demo.endpoint;



import java.text.SimpleDateFormat;
import java.util.Date;

import org.jdom2.Element;
import org.jdom2.Namespace;
import org.jdom2.filter.Filters;
import org.jdom2.xpath.XPathExpression;
import org.jdom2.xpath.XPathFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;

import demo.service.HumanResourceService;


@Endpoint
public class HolidayEndpoint {

	private static final String NAMESPACE_URI = "http://joedayz.pe/hr/schemas";
	
	private XPathExpression<Element> startDateExpression;
	private XPathExpression<Element> endDateExpression;
	private XPathExpression<String> nameExpression;
	
	private HumanResourceService humanResourceService;
	
	
	@Autowired
	public HolidayEndpoint(HumanResourceService humanResourceService){
		
		this.humanResourceService = humanResourceService;
		
		Namespace namespace = Namespace.getNamespace("hr", NAMESPACE_URI);
		XPathFactory xPathFactory = XPathFactory.instance();
		
		startDateExpression =xPathFactory.compile("//hr:StartDate", Filters.element(), null,namespace);
		
		endDateExpression = xPathFactory.compile("//hr:EndDate", Filters.element(), null,namespace);
		
		nameExpression = xPathFactory.compile(
				"concat(//hr:FirstName,' ',//hr:LastName)",
				Filters.fstring(), null,namespace);		
	}
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart = "HolidayRequest")
	public void handleHolidayRequest(@RequestPayload Element holidayRequest)
					throws Exception{
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		Date startDate = dateFormat.parse(
					this.startDateExpression.
						evaluateFirst(holidayRequest).getText());
		
		Date endDate = dateFormat.parse(
				this.endDateExpression.
					evaluateFirst(holidayRequest).getText());
		String name = this.nameExpression.evaluateFirst(holidayRequest);
		
		this.humanResourceService.bookHoliday(startDate, endDate, name);
		
		
	}
	
	
}
