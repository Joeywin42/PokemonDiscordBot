import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class PokemonWebScrape {
    ArrayList<PokemonObject> pokemonListClass = new ArrayList<PokemonObject>();
    public PokemonWebScrape(){
        webScrape();
    }


    public void webScrape(){
        System.out.println("Running WebScrape");
        String address = "https://pokemondb.net/pokedex/game/red-blue-yellow";
        URL pageLocation = null;
        Scanner in = null;

        //finding the page
        try {
            pageLocation = new URL(address);
            in = new Scanner(pageLocation.openStream());
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        //setting up the variables
        boolean start = false;
        String line = "";
        ArrayList<String> pokemonLine = new ArrayList<>();
        //reading the html setting up line
        while(in.hasNextLine()){
            //setting up line
            line = in.nextLine();

            // where it ends
            if(line.equals("</div>"))
                start = false;

            //Reading the line and configuring it
            if(start == true)
                pokemonLine.add(line);

            // where it starts
            if(line.equals("<div class=\"infocard-list infocard-list-pkmn-lg\">"))
                start = true;
        }

        //setting up each pokemon
        PokemonObject[] pokemons = new PokemonObject[pokemonLine.size()];
        for(int i = 0; i < pokemonLine.size(); i++){
            pokemons[i] = new PokemonObject(pokemonLine.get(i),Integer.toString(i+1),pokemonLine.get(i));
            pokemonListClass.add(pokemons[i]);
           // System.out.println(pokemons[i]);
        }

        //getFromNumber("15", pokemons);
       // getFromName("Beedrill", pokemons);
       // getFromType("fire", pokemons);

        //Arrays.stream(pokemons).forEach(x -> System.out.println(x));



    }
    //getting pokemon via certain attributes
    public String getFromNumber(String number, ArrayList<PokemonObject> list){
        for (PokemonObject pokemons: list) {
            if (number.equals(pokemons.getNumber()))
                return pokemons.toString();
        }
        System.out.println("Running getFromNumber inside PokemonWebScrape Class");
        return "Failed the search";
    }

    public String getFromName(String name, ArrayList<PokemonObject> list){
        for (PokemonObject pokemons: list) {
            if(name.toLowerCase().equals(pokemons.getName().toLowerCase()))
                return pokemons.toString();
        }
        System.out.println("Running getFromName inside PokemonWebScrape Class");
        return "failed the search";
    }

    public String getFromType(String type, ArrayList<PokemonObject> list){
        String pokemonListReturn = "";
        for (PokemonObject pokemons: list) {
            if(pokemons.getType().contains(type.toUpperCase()))
                pokemonListReturn += pokemons.toString()+ "\n";

        }
        System.out.println("Running getFromType insde PokemonWebScrape Class");
        return (pokemonListReturn);
    }

    public String getAllPokemon(ArrayList<PokemonObject> list){
        String allPokemon = "";
        for (PokemonObject pokemonObject: list)
            allPokemon += pokemonObject.toString() + "\n";

        return allPokemon;
    }


    //testing the methods
    public static void main(String[] args){
        PokemonWebScrape tester = new PokemonWebScrape();
        tester.webScrape();
        //System.out.println(tester.getFromType("fire", tester.pokemonListClass));
        //System.out.println("hiyo testing");
        System.out.println(tester.getAllPokemon(tester.pokemonListClass));
    }


}
