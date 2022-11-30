module NewspaperApp {

    requires javafx.graphics;
    requires javafx.controls;
    requires javafx.fxml;
    requires MaterialFX;
    requires com.fasterxml.jackson.databind;
    requires com.fasterxml.jackson.dataformat.yaml;
    requires com.google.gson;
    requires org.apache.logging.log4j;
    requires jakarta.cdi;
    requires jakarta.inject;
    requires lombok;
    requires jakarta.xml.bind;
    requires io.vavr;
    requires java.sql;

    exports modelo;
    exports ui.main to javafx.graphics;
    exports ui.pantallas.principal;
    exports ui.pantallas.common;
    exports config;
    exports dao;
    exports servicios;
    exports ui.pantallas.login;
    exports ui.pantallas.newspaper;
    exports ui.pantallas.addNewspaper;
    exports ui.pantallas.articles;
    exports ui.pantallas.addArticles;
    exports ui.pantallas.deleteNewspaper;
    exports ui.pantallas.readersList;
    exports DI;
    exports ui.pantallas.addReaderArticles;
    exports ui.pantallas.deleteReader;
    exports ui.pantallas.informationReader;

    opens ui.pantallas.principal;
    opens ui.main;
    opens modelo to javafx.base,com.google.gson,javafx.fxml,jakarta.xml.bind;
    opens config;
    opens ui.pantallas.common;
    opens ui.pantallas.login;
    opens imagenes;
    opens ui.pantallas.newspaper;
    opens ui.pantallas.addNewspaper;
    opens ui.pantallas.articles;
    opens ui.pantallas.addArticles;
    opens ui.pantallas.deleteNewspaper;
    opens ui.pantallas.readersList;
    opens ui.pantallas.addReaderArticles;
    opens ui.pantallas.deleteReader;
    opens ui.pantallas.informationReader;
    opens servicios;
}
