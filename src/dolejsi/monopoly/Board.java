package dolejsi.monopoly;

import javax.swing.*;

public class Board extends JPanel {
    public Integer x = 0;
    int[] place = new int[x];

    String owner;
    Integer pricePaid;
    Integer rentPrice;
    Integer visitors;
    Integer updates;


    void Building(String owner1, Integer pricePaid1, Integer rentPrice1,Integer visitors1, Integer updates1){
        owner1=owner;
        pricePaid1=pricePaid;
        rentPrice1=rentPrice;
        visitors1=visitors;
        updates1=updates;
    }
}
