package dolejsi.monopoly;

public class Tax extends BoardTile{
    private int taxToPay;

    public Tax(int position, int taxToPay) {
        super(position);
        this.taxToPay = taxToPay;
    }

    public int getTaxToPay() {
        return taxToPay;
    }
}
