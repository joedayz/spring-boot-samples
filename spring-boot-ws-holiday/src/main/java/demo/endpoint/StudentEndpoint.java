package demo.endpoint;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import pe.joedayz.soap.GetStudentRequest;
import pe.joedayz.soap.GetStudentResponse;
import demo.service.StudentService;

@Endpoint
public class StudentEndpoint {

	private static final String NAMESPACE_URI = "http://joedayz.pe/soap";
	
	@Autowired
	private StudentService studentService;
	
	@PayloadRoot(namespace=NAMESPACE_URI, localPart="getStudentRequest")
	@ResponsePayload
	public GetStudentResponse getStudent(@RequestPayload GetStudentRequest request){
		
		GetStudentResponse response = new GetStudentResponse();
		response.setStudent(studentService.getStudent(request.getStudentId()));
		return response;
	}
	
}
