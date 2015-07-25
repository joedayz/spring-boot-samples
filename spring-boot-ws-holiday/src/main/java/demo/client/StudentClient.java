package demo.client;

import org.springframework.ws.client.core.support.WebServiceGatewaySupport;
import org.springframework.ws.soap.client.core.SoapActionCallback;

import pe.joedayz.wsdl.GetStudentRequest;
import pe.joedayz.wsdl.GetStudentResponse;

public class StudentClient extends WebServiceGatewaySupport{

	public GetStudentResponse getStudentById(int studentId){
		GetStudentRequest request = new GetStudentRequest();
		request.setStudentId(studentId);
		
		GetStudentResponse response = (GetStudentResponse)
				getWebServiceTemplate().
				marshalSendAndReceive(request, 
		 	new SoapActionCallback("http://localhost:8080/services/getStudentResponse"));
		return response;
	}
}
