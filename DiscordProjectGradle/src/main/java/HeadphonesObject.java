import java.util.ArrayList;

public class HeadphonesObject extends SoundMaster{
    public HeadphonesObject() throws InterruptedException {
        super();

    }
    public HeadphonesObject(String name, String rank, String price) throws InterruptedException {
        super();
        super.name = name;
        super.rank = rank;
        super.price = price;

        System.out.println("created new Headphone Object " + name);



    }




}
