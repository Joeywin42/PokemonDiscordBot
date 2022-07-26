import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Scanner;

public class PokemonWebScrape {

    public void webScrape(){
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
           // System.out.println(pokemons[i]);
        }

        //getFromNumber("15", pokemons);
       // getFromName("Beedrill", pokemons);
        getFromType("fire", pokemons);

        //Arrays.stream(pokemons).forEach(x -> System.out.println(x));



    }
    //getting pokemon via certain attributes
    public void getFromNumber(String number, PokemonObject[] list){
        for (PokemonObject pokemons: list) {
            if(number.equals(pokemons.getNumber()))
                System.out.println(pokemons);
        }
    }

    public void getFromName(String name, PokemonObject[] list){
        for (PokemonObject pokemons: list) {
            if(name.toLowerCase().equals(pokemons.getName().toLowerCase()))
                System.out.println(pokemons);
        }
    }

    public void getFromType(String type, PokemonObject[] list){
        for (PokemonObject pokemons: list) {
            if(pokemons.getType().contains(type.toUpperCase()))
                System.out.println(pokemons);
        }
    }


    //testing the methods
    public static void main(String[] args){
        PokemonWebScrape tester = new PokemonWebScrape();
        tester.webScrape();
        //System.out.println("hiyo testing");
    }


}
