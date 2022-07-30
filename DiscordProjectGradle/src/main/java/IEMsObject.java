import java.util.ArrayList;

public class IEMsObject extends SoundMaster{
    public IEMsObject() throws InterruptedException {
        super();

    }
    public IEMsObject(String name, String rank, String price) throws InterruptedException {
        super();
        super.name = name;
        super.rank = rank;
        super.price = price;

        System.out.println("Created new IEM Object " + name);

    }


}
