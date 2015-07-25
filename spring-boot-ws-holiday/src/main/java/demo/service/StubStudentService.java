package demo.service;

import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Service;

import pe.joedayz.soap.Student;

@Service
public class StubStudentService implements StudentService {


	private Map<Integer,Student> studentMap = new HashMap<Integer,Student>();
		public StubStudentService(){
			Student s1 = new Student();
			s1.setStudentId(1);
			s1.setName("Ram");
			s1.setAge(20);
			s1.setClazz("ABC");
			studentMap.put(1, s1);
			Student s2 = new Student();
			s2.setStudentId(2);
			s2.setName("Shyam");
			s2.setAge(22);
			s2.setClazz("EFG");
			studentMap.put(2, s2);
		}
		public Student getStudent(int studentId){
			return studentMap.get(studentId);
		}

}
