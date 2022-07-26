import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class PokemonWebScrape {

    public void webScrape(){
        String address = "https://pokemondb.net/pokedex/game/red-blue-yellow";
        URL pageLocation = null;
        Scanner in = null;

        try {
            pageLocation = new URL(address);
            in = new Scanner(pageLocation.openStream());
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        while(in.hasNextLine()){
            System.out.println(in.nextLine());
        }





    }
    public static void main(String[] args){
        PokemonWebScrape tester = new PokemonWebScrape();
        tester.webScrape();
        System.out.println("hiyo testing");
    }


}
