package code;
import java.io.File;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

public class JAXBExample {

	public static void main(String[] args) {

		try {
			File file = new File("src/main/resources/customerData.xml");
			JAXBContext jaxbContext = JAXBContext.newInstance(Customer.class);
			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			Customer customer = (Customer) jaxbUnmarshaller.unmarshal(file);
			System.out.println(customer);
		} catch (JAXBException e) {
			e.printStackTrace();
		}
	}
}

@XmlRootElement
class Customer {
	String name;
	int age;
	int id;

	public String getName() {
		return name;
	}

	@XmlElement
	public void setName(String name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	@XmlElement
	public void setAge(int age) {
		this.age = age;
	}

	public int getId() {
		return id;
	}

	@XmlAttribute
	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		StringBuffer sb = new StringBuffer();
		sb.append("name = ");
		sb.append(name);
		sb.append(", id = ");
		sb.append(id);
		sb.append(", age = ");
		sb.append(age);
		return sb.toString();
	}
}
