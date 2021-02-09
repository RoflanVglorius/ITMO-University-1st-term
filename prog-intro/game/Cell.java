package game;

public enum Cell {
    X("X"), O("O"), M("-"),
    I("|"), E("."), N(" ");

    private final String charEq;

    Cell(String string) {
        charEq = string;
    }


    @Override
    public String toString() {
        return charEq;
    }

    public int getIndex() {
        return ordinal() + 1;
    }
}
