package ee.ege.veebipood.model.supplier;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import lombok.Data;

@XmlRootElement(name = "root")
public class JohnDoeXML {
    public String city;
    public String firstName;
    public String lastName;
    public String state;
}
