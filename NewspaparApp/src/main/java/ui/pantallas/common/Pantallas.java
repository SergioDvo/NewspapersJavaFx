package ui.pantallas.common;

public enum Pantallas {

    LOGIN ("/fxml/Login.fxml"),

    PANTALLANEWSPAPER("/fxml/newspaper/newspaperList.fxml"),

    PANTALLAADDNEWSPAPER("/fxml/newspaper/addNewspaper.fxml"),

    PANTALLAARTICLE("/fxml/articles/articles.fxml"),

    PANTALLAADDARTICLE("/fxml/articles/addArticles.fxml"),

    PANTALLADELETENEWSPAPER("/fxml/newspaper/deleteNewspaper.fxml"),

    PANTALLAREADER("/fxml/readers/readersList.fxml"),

    PANTALLAADDREADER("/fxml/readers/addReaderArticle.fxml"),

    PANTALLADELETEREADER("/fxml/readers/deleteReader.fxml"),

    PANTALLAREADEROFNEWSPAPER("/fxml/readers/informationReaderNewspaper.fxml");



    private String ruta;
    Pantallas(String ruta) {
        this.ruta=ruta;
    }
    public String getRuta(){return ruta;}


}
