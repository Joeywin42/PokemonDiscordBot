import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.entities.Message;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;


import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Locale;
import java.util.Scanner;

public class DiscordBot extends ListenerAdapter {
    PokemonWebScrape pokemons = new PokemonWebScrape();
    static SoundMaster device;

    static {
        try {
            device = new SoundMaster();
            device.startSounds();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


    public DiscordBot() throws InterruptedException {
    }


    public static void main(String[] args) throws LoginException, FileNotFoundException, InterruptedException {

        System.out.println("Hambot is running");


        URL url = DiscordBot.class.getResource("discordToken.txt");
        String token = "";
        try{
            File tokenGenerator = new File(url.toURI().getPath());
            Scanner reader = new Scanner(tokenGenerator);

            while(reader.hasNextLine()){
                token = reader.nextLine();
            }

        } catch (FileNotFoundException e){
            System.out.println("The file was probably not found rip");
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }
        //create pokemon





        JDA bot = JDABuilder
                .createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("Mochi Bark "))
                .addEventListeners(new DiscordBot())
                .build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {




        String msg = event.getMessage().getContentRaw();
        String msgType = "";

        System.out.println("event= " + event.getMessage());

        System.out.println("content= " +msg);
        System.out.println(msg.substring(2));
        String keyword = "::";

        //gaurd claus
        if(!msg.startsWith(keyword)) return;


        msg = msg.substring(2);

        //All the commands
        //pokemons.getFromType(msg.substring(8,msg.length()-1),pokemons.pokemonListClass
        if(!msg.startsWith("p.") || !msg.startsWith("h.") || !msg.startsWith("i."))
            try {
                switch (msg.toLowerCase()) {
                    case "test":
                        event.getMessage().reply("Test success").queue();
                        break;
                    case "hiyo":
                        event.getMessage().reply("hiyo").queue();
                        break;
                    case "100":
                        event.getMessage()
                                .reply("```"+(device.getFromPrice(device.headphonesObjectArrayListReal, msg, "h"))+ "```")
                                .queue();
                        return;
                    case "moondrop":
                        event.getMessage()
                                .reply("```" + device.getFromName(msg, "h") + "```")
                                .queue();
                        break;
                    //CONSTANTLY UPDATE HELP WITH EACH NEW FEATURE
                    case "help":
                        event.getMessage().reply(
                                "```To use the Pokemon commands you must use ::p.\n" +
                                        "----------The commands that I currently have is---------\n" +
                                        "::p.type() This gets all pokemons of a certain type\n" +
                                        "::p.name() This gets the pokemon of said name \n" +
                                        "::p.nums() This gets the pokemon of said number\n" +
                                        "------------------------------------------------------------------------------------------\n" +
                                        "Keep in mind that you are to type the command exactly as written with \n" +
                                        "the value in the parenthesis and the parenthesis is also necessary.```"
                        ).queue();
                        break;
                    default:
                        event.getMessage().reply("oops normal").queue();
                        break;

                }
            }catch (Exception e){
                e.printStackTrace();
            }

        if(msg.startsWith("h.") || msg.startsWith("i.")) {
            try{
                //h.cost(100)
                //Variables
                String tag =  msg.substring(0,1);
                String parameter = msg.substring(7,msg.length()-1);
                msg = msg.substring(2,6);

                switch (msg){
                    case "cost" :
                        event
                                .getMessage()
                                .reply(tag.equals("h") ?
                                        "```" + device.getFromPrice(device.headphonesObjectArrayListReal, parameter, tag) + "```" :
                                        "```" + device.getFromPrice(device.ieMsObjectArrayListReal, parameter, tag) + "```")
                                .queue();
                        return;
                    case "name":
                        event
                                .getMessage()
                                .reply("```" + device.getFromName(parameter, tag) + "```")
                                .queue();
                        return;

                    default:event.getMessage().reply("oops Headphones");
                            break;
                }







            }catch (Exception e){
                e.printStackTrace();
                event.getMessage().reply("``` oops headphone handler ```").queue();

            }
        }

        if(msg.startsWith("p.")){
            try {
                    msgType = msg.substring(2, 6);
                    msg = msg.substring(7, msg.length() - 1);
                //msgAlt should be case
                switch (msgType.toLowerCase()) {
                    case "type":
                        event.getMessage().reply(pokemons.getFromType(msg, pokemons.pokemonListClass)).queue();
                        System.out.println("type ran");
                        break;

                    case "name":
                        event.getMessage().reply(pokemons.getFromName(msg, pokemons.pokemonListClass)).queue();
                        System.out.println("name ran");
                        break;

                    case "nums":
                        event.getMessage().reply(pokemons.getFromNumber(msg, pokemons.pokemonListClass)).queue();
                        System.out.println("nums ran");
                        break;

                    case "allp":
                        String message = pokemons.getAllPokemon(pokemons.pokemonListClass);
                        event.getMessage().reply(message.substring(0, message.length()/4)).queue();
                        event.getMessage().reply(message.substring(message.length()/4, message.length()/2-2)).queue();
                        event.getMessage().reply(message.substring(message.length()/2-2,message.length()/4*3-8)).queue();
                        event.getMessage().reply(message.substring(message.length()/4*3-8)).queue();

                        System.out.println("allp Ran");
                        break;


                    default:
                        System.out.println(msg + " :::THIS DIDNT WORK:::");
                        event.getMessage().reply("oops Pokemon").queue();
                        break;
                    // event
                }

            } catch (Exception e) {
                e.printStackTrace();
                event.getMessage().reply("oops Pokemon Handler").queue();
            }
        }
    }
}
