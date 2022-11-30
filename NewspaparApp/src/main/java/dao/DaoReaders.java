package dao;

import config.ConfigXML;
import io.vavr.control.Either;
import jakarta.inject.Inject;
import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import jakarta.xml.bind.Unmarshaller;
import modelo.Reader;
import modelo.Readers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

public class DaoReaders implements dao.impl.DaoReadersImpl {
    @Inject
    public DaoReaders() {}

    @Override
    public Either<String,List<Reader>> getReaders() {
        Either<String, List<Reader>> respuesta = null;
        // Read the XML document from the file
        try {
            JAXBContext context = JAXBContext.newInstance(Readers.class);
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

            Unmarshaller unmarshaller = context.createUnmarshaller();
            Path xmlFile= Paths
                    .get(ConfigXML.getInstance().getProperty("xmlReadersPath"));
            Readers empList = (Readers) unmarshaller.unmarshal(Files.newInputStream(xmlFile));
            if (empList != null) {
                respuesta =  Either.right(empList.getReader());
            } else {
                respuesta = Either.left("We have a problem");
            }
        } catch (IOException | JAXBException e) {
            respuesta =  Either.left(e.getMessage());
        }
        return respuesta;

    }
    @Override
    public Either<String, Boolean> saveReaderList(List<Reader> readerList) {
        JAXBContext context = null;
        Marshaller marshaller = null;
        try {
            context = JAXBContext.newInstance(Readers.class);
            marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        } catch (JAXBException e) {
            return Either.left(e.getMessage());
        }
        Readers readers = new Readers();
        readers.setReader(readerList);
        Path xmlFile= Paths
                .get(ConfigXML.getInstance().getProperty("xmlReadersPath"));
        try {
            marshaller.marshal(readers, Files.newOutputStream(xmlFile));
        } catch (JAXBException | IOException e) {
            return Either.left(e.getMessage());
        }
        return Either.right(true);
    }
}
