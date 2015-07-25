package demo;

import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.ws.config.annotation.EnableWs;
import org.springframework.ws.config.annotation.WsConfigurerAdapter;
import org.springframework.ws.transport.http.MessageDispatcherServlet;
import org.springframework.ws.wsdl.wsdl11.DefaultWsdl11Definition;
import org.springframework.xml.xsd.SimpleXsdSchema;
import org.springframework.xml.xsd.XsdSchema;

@EnableWs
@Configuration
public class WebServiceConfig extends WsConfigurerAdapter {

	@Bean
	public ServletRegistrationBean 
				dispatcherServlet(ApplicationContext applicationContext){
		MessageDispatcherServlet servlet = new MessageDispatcherServlet();
		servlet.setApplicationContext(applicationContext);
		return new ServletRegistrationBean(servlet, "/services/*");
	}
	
	
	@Bean(name="holiday")  //http://localhost:8080/services/holiday.wsdl
	public DefaultWsdl11Definition defaultWsdl11Definition(
				XsdSchema countriesSchema){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("HumanResource");
		wsdl11Definition.setLocationUri("/holidayService");
		wsdl11Definition.setTargetNamespace("http://joedayz.pe/hr/definitions");
		wsdl11Definition.setSchema(countriesSchema);
		
		return wsdl11Definition;
	}

	@Bean(name="students")  //http://localhost:8080/services/students.wsdl
	public DefaultWsdl11Definition studentsWsdl11Definition(
				XsdSchema studentsSchema){
		DefaultWsdl11Definition wsdl11Definition = new DefaultWsdl11Definition();
		wsdl11Definition.setPortTypeName("StudentResource");
		wsdl11Definition.setLocationUri("/studentService");
		wsdl11Definition.setTargetNamespace("http://joedayz.pe/soap");
		wsdl11Definition.setSchema(studentsSchema);
		
		return wsdl11Definition;
	}

	
	
	//Primero inicia con el XSD: Schema XML
	@Bean
	public XsdSchema countriesSchema(){
		return new SimpleXsdSchema(
				new ClassPathResource("META-INF/schemas/hr.xsd"));
	}
	
	
	@Bean
	public XsdSchema studentsSchema(){
		return new SimpleXsdSchema(
				new ClassPathResource("META-INF/schemas/students.xsd"));
	}
		
	
	
	
	
	
	
	
	
}
