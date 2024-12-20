package ee.ege.veebipood.service;

import ee.ege.veebipood.model.supplier.JohnDoe;
import ee.ege.veebipood.model.supplier.JohnDoeXML;
import ee.ege.veebipood.model.supplier.SupplierProduct;
import ee.ege.veebipood.model.supplier.SupplierProductEscuela;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Log4j2 // vs sout
// 1) annab ajatempli
// 2) annab faili kus juhtus
// 3) saab määrata error/info/debug
// 4) ta läheb logifaili
@Service
public class SupplierService {

    @Autowired
    RestTemplate restTemplate;

    public List<SupplierProduct> getProducts() {

        System.out.println("Login välja1");
        log.info("Login välja2");
        log.info(restTemplate); // uut tehes, loob igakord uue mälukoha

        String url = "https://fakestoreapi.com/products";
                                        // null -> Body ja Headers
        ResponseEntity<SupplierProduct[]> response = restTemplate.exchange(
            url,
            HttpMethod.GET,
            null,
            SupplierProduct[].class
        );
        // ResponseEntity sees on staatuskood + headrerid + body
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }

    public List<SupplierProductEscuela> getEscuelaProducts() {
        String url = "https://api.escuelajs.co/api/v1/products";
        // null -> Body ja Headers
        ResponseEntity<SupplierProductEscuela[]> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                SupplierProductEscuela[].class
        );
        // ResponseEntity sees on staatuskood + headrerid + body
        if (response.getBody() == null) {
            return new ArrayList<>();
        }
        return Arrays.asList(response.getBody());
    }

    public JohnDoe getXMLData() {
        String url = "https://mocktarget.apigee.net/xml";
        // null -> Body ja Headers
        ResponseEntity<String> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                String.class
        );
        JohnDoeXML xml = unmarshalXml(response.getBody());
        JohnDoe johnDoe = new JohnDoe();
        johnDoe.setCity(xml.city);
        johnDoe.setLastName(xml.lastName);
        johnDoe.setFirstName(xml.firstName);
        johnDoe.setState(xml.state);
        return johnDoe;
    }

    private JohnDoeXML unmarshalXml(String xmlData) {
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(JohnDoeXML.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(xmlData);
            return (JohnDoeXML) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            throw new RuntimeException("Error unmarshalling XML: " + e.getMessage(), e);
        }
    }
}
