import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
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
        for(int i = 0; i < 1; i++){
            pokemons[i] = new PokemonObject(pokemonLine.get(i),Integer.toString(i+1),"");
            //System.out.println(pokemons[i]);
            System.out.println(pokemons[i].getName());
            String test = pokemons[i].getName();
            String[] testList= test.split(" ");
            for (String str: testList) {
                //System.out.println(str);
                //Name Setter
                if(str.contains("alt=")) {
                    String testStr = str.replaceAll("alt=\"|\"","");
                    pokemons[i].setName(testStr);
                }
            }
            System.out.println(pokemons[i]);

        }

    }
    //testing the methods
    public static void main(String[] args){
        PokemonWebScrape tester = new PokemonWebScrape();
        tester.webScrape();
        //System.out.println("hiyo testing");
    }


}
