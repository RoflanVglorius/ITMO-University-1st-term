package markup;

import java.util.List;

public class Strikeout extends AbstractMarkup {

    public Strikeout(List<Elements> elementsList) {
        super(elementsList);
    }

    protected String printMarkup() {
        return "~";
    }

    @Override
    protected String printLeftBorder() {
        return "\\textst{";
    }

    @Override
    protected String printRightBorder() {
        return "}";
    }
}
