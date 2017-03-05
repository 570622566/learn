package test.com.jaxb;

import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;

public class JAXBExample {
	
	 public static void main(String[] args) {  
		   
	    //  Object2Xml();  
	      Xml2ObjectDemo();
	    }


	private static void Object2Xml() {
		Customer customer = new Customer();  
	      customer.setId(100);  
	      customer.setName("mkyong");  
	      customer.setAge(29);  
	   
	      try {  
	   
	        File file = new File("E:\\schema\\customer.xml");  
	        JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);  
	        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();  
	   
	        // output pretty printed  
	        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);  
	        
	        jaxbMarshaller.marshal(customer, file);  
	        jaxbMarshaller.marshal(customer, System.out);  
	   
	          } catch (JAXBException e) {  
	        e.printStackTrace();  
	          }
	}  
	 
	 
	 public static  void Xml2ObjectDemo(){
		 try {  
	            File file = new File("E:\\schema\\customer.xml");  
	            JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);  
	            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();  
	            Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);  
	            System.out.println(customer);  
	        } catch (JAXBException e) {  
	            e.printStackTrace();  
	        }  
	 }
}
