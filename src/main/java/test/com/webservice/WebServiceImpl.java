package test.com.webservice;

import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;

@WebService(name = "MyService",serviceName="MyWebService",targetNamespace="http://www.tlcb.com")
public class WebServiceImpl implements WebServiceI {

	      @Override
	       public @WebResult(name="result") String sayHello(@WebParam(name="name") String name) {
	           System.out.println("WebService sayHello "+name);
	          return "sayHello "+name;
	      }

	@Override
	public String save(String name, String pwd) {
		System.out.println("WebService save "+name+"， "+pwd);
		 return "save Success";
	}

}
