import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import net.dv8tion.jda.api.entities.Activity;
import net.dv8tion.jda.api.events.message.MessageReceivedEvent;
import net.dv8tion.jda.api.hooks.ListenerAdapter;
import net.dv8tion.jda.api.requests.GatewayIntent;
import org.jetbrains.annotations.NotNull;


import javax.security.auth.login.LoginException;
import java.io.File;
import java.io.FileNotFoundException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

public class DiscordBot extends ListenerAdapter {


    public static void main(String[] args) throws LoginException, FileNotFoundException {

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



        JDA bot = JDABuilder
                .createDefault(token)
                .enableIntents(GatewayIntent.GUILD_MEMBERS, GatewayIntent.MESSAGE_CONTENT)
                .setActivity(Activity.watching("Looking at Mochi Barking"))
                .addEventListeners(new DiscordBot())
                .build();
    }

    @Override
    public void onMessageReceived(@NotNull MessageReceivedEvent event) {

        String msg = event.getMessage().getContentRaw();
        System.out.println("evant= " + event.getMessage());

        System.out.println("content= " +msg);

        if(!event.getAuthor().isBot() && event.getMessage().getContentRaw().equals("Hiyo")){
            event.getMessage().reply("Hiyo").queue();
        }
    }
}
