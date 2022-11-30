package modelo;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Type {

    private int id;
    private String name_type;

    public Type(String linea) {
        String [] camps = linea.split(";");
        this.id = Integer.parseInt(camps[0]);
        this.name_type = camps[1];
    }
    public String convertToLine(){
        return id + ";" + name_type + "\n";
    }

}
