import com.sun.management.GarbageCollectionNotificationInfo;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;

public class SoundMaster {

    public SoundMaster() throws InterruptedException {
        System.out.println("Constructed");
    }


    String rank = "";
    String name = "";
    String price = "";

    ArrayList<HeadphonesObject> headphonesObjectArrayListTemp = new ArrayList<>();
    ArrayList<IEMsObject> ieMsObjectArrayListTemp = new ArrayList<>();
    ArrayList<HeadphonesObject> headphonesObjectArrayListReal = new ArrayList<>();
    ArrayList<IEMsObject> ieMsObjectArrayListReal = new ArrayList<>();


    //create a list
    public void webScrape(String address) throws InterruptedException {
        System.out.println("Running WEBSCRAPE");
        URL pageLocation = null;
        Scanner in = null;


        boolean start = false;

        try {
            pageLocation = new URL(address);
            in = new Scanner(pageLocation.openStream());
        } catch ( MalformedURLException e ) {
            e.printStackTrace();
        } catch ( IOException e ) {
            e.printStackTrace();
        }

        while(in.hasNext()){
            String line = in.nextLine();

            //this is when it starts
            if(line.contains("<tbody class=\"row-hover\">")){
                start = true;
            }

            //this is when it ends
            if(line.contains("</tbody>")){
                start = false;
            }
            if (line.contains("/table"))
                start = false;

            if(start){
                if(line.contains("tr class=\"row")){
                    line = (in.nextLine());
                    String[] lines = line.split("</td>");

                    rank = lines[0].replaceAll("<td class=\"column-1\"><span class=\"|</span>","");
                    rank = rank.replaceAll("\">"," ");

                    name = lines[2].replaceAll("<td class=\"column-3\"><a href=\"|</a>|<td class=\"column-3\">|<br><div class=\"at-content-ad\"></div>","");
                    name = name.replaceAll("\">"," ");
                    name = name.replaceAll("\\bhttp[^\\s]+ | <br> | <a href=","");




                    price = lines[3].replaceAll("<td class=\"column-4\">","");

                    if (address.contains("headphone")){
                        headphonesObjectArrayListTemp.add(new HeadphonesObject(name,rank,price));
                    }
                    else{
                        ieMsObjectArrayListTemp.add(new IEMsObject(name, rank, price));
                    }

                }
            }


        }
        //Loop end


    }
    public void fillList(){
        headphonesObjectArrayListReal.addAll(headphonesObjectArrayListTemp.subList(0, headphonesObjectArrayListTemp.size()/2));
        ieMsObjectArrayListReal.addAll(ieMsObjectArrayListTemp.subList(0, ieMsObjectArrayListTemp.size()/2));

        headphonesObjectArrayListTemp.clear();
        ieMsObjectArrayListTemp.clear();

    }

    public void startSounds() throws InterruptedException {
        System.out.println("Running Start Sounds");
        Thread.sleep(3000);
        webScrape("https://crinacle.com/rankings/headphones/");
        Thread.sleep(3000);
        webScrape("https://crinacle.com/rankings/iems/");
        fillList();
    }


    public <T> String getFromPrice(List<T> list, String price, String tag){
        System.out.println("Get from price");
        String compiledList = "";
            if (tag.equals("h")) {
                for (HeadphonesObject headP : headphonesObjectArrayListReal) {
                    try {
                        if (Integer.parseInt(headP.getPrice()) <= Integer.parseInt(price))
                            compiledList += headP.toString();
                    } catch (Exception e) {
                        continue;
                    }
                }

            } else {
                for (IEMsObject iemP : ieMsObjectArrayListReal) {
                    try{
                        if (Double.parseDouble(iemP.getPrice()) <= (Double.parseDouble(price)))
                            compiledList += iemP.toString();
                    } catch (Exception e){
                        System.out.println(iemP);
                        continue;
                    }

                }

            }
        return compiledList;
    }
    public String getFromName(String name, String tag){
        System.out.println("Running Get from Name");
        String compiledList = "";
        name = name.toLowerCase();
        if(tag.equals("h")) {
            for (HeadphonesObject head : headphonesObjectArrayListReal) {
                if (head.getName().toLowerCase().contains(name))
                    compiledList += head.toString();
            }
        } else {
            for (IEMsObject iems : ieMsObjectArrayListReal) {
                if (iems.getName().toLowerCase().contains(name))
                    compiledList += iems.toString();
            }
        }
        return compiledList;
    }


    //GETTERS
    public String getPrice() {
        return price;
    }

    public String getRank() {
        return rank;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString(){
        String info = "\nName: " + name +
                    "\nRank: " + rank +
                    "\nPrice: " + price + "$\n";

        return info;
    }



    public static void main(String[] args) throws InterruptedException {
        SoundMaster test = new SoundMaster();
        test.startSounds();


        System.out.println(test.getFromPrice(test.headphonesObjectArrayListReal,"100","h"));



    }


}
