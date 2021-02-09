package markup;

import java.util.List;

public class Emphasis extends AbstractMarkup {

    public Emphasis(List<Elements> elementsList) {
        super(elementsList);
    }

    protected String printMarkup() {
        return "*";
    }

    @Override
    protected String printLeftBorder() {
        return "\\emph{";
    }

    @Override
    protected String printRightBorder() {
        return "}";
    }
}
