package modelo;

import lombok.Data;

@Data
public class Article {

    private int id;
    private String name_article;
    private String description;
    private int id_newspaper;

    private int id_type;

    public Article(String linea) {
        String [] camps = linea.split(";");
        this.id = Integer.parseInt(camps[0]);
        this.name_article = camps[1];
        this.description = camps[2];
        this.id_newspaper = Integer.parseInt(camps[3]);
        this.id_type = Integer.parseInt(camps[4]);
    }
    public Article(int id, String name_article, String description, int id_newspaper, int id_type) {
        this.id = id;
        this.name_article = name_article;
        this.description = description;
        this.id_newspaper = id_newspaper;
        this.id_type = id_type;
    }

    public String convertToLine(){
        return id + ";" + name_article + ";" + description + ";"+ id_newspaper + ";"+ id_type + "\n";
    }

}
