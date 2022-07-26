import java.util.ArrayList;
import java.util.Locale;

public class PokemonObject {
    String name = "";
    String number = "";
    String type = "";

    public PokemonObject(){

    }

    public PokemonObject(String name, String number, String type){
        this.name = name;
        this.number = number;
        this.type = type;

        setName(name);
        setType(type);


    }

    //getters
    public String getName(){
        return this.name;
    }

    public String getNumber(){
        return this.number;
    }

    public String getType(){
        return this.type;
    }




    //setters


    public void setName(String name) {
        this.name = findName(name);
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = findType(type);
    }


    //find name from line
    private String findName(String line){
        String foundName = "";
        String[] list = line.split(" ");
        for (String str: list) {
            if(str.contains("alt="))
                foundName = str.replaceAll("alt=\"|\"","");
        }

        return foundName;
    }

    //find type from line
    private String findType(String line){
        String foundLine = "";
        String[] list = line.split(" ");
        ArrayList<String> typeLines = new ArrayList<String>();
        for(String str: list){
            if (str.contains("/type/"))
                typeLines.add((str.substring(12, str.length()-1)).toUpperCase());
        }

        return String.join(", ", typeLines);
    }

    @Override
    public String toString(){
        return ("Name: " + this.name +
                "\nType: " + this.type +
                "\nNumber: " + this.number
                + "\n");
    }

}
