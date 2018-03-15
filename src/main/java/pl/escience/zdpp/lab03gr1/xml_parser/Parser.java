package pl.escience.zdpp.lab03gr1.xml_parser;

import pl.escience.zdpp.lab03gr1.database.entity.WishTemplate;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.File;

public class Parser {
    public WishTemplate readFromXMLFile(String path) throws JAXBException {
        File file = new File(path);
        JAXBContext jaxbContext = JAXBContext.newInstance(WishTemplate.class);
        Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
        return (WishTemplate) jaxbUnmarshaller.unmarshal(file);
    }

    public void saveToXMLFile(WishTemplate wishTemplate, String directory) throws JAXBException {
        JAXBContext jaxbContext;
        jaxbContext = JAXBContext.newInstance(WishTemplate.class);
        Marshaller jaxbMarshaller = jaxbContext.createMarshaller();
        jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        jaxbMarshaller.marshal(wishTemplate, new File(directory, "wish_template_" + wishTemplate.getText()
                .substring(0, Math.min(wishTemplate.getText().length(), 10)) + ".xml"));
    }
}
