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
        this.name = name;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Override
    public String toString(){
        return ("Name: " + this.name +
                "\nType: " + this.type +
                "\nNumber: " + this.number
                + "\n");
    }

}
