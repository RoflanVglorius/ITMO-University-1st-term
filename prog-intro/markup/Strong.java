package markup;

import java.util.List;

public class Strong extends AbstractMarkup {

    public Strong(List<Elements> elementsList) {
        super(elementsList);
    }

    protected String printMarkup() {
        return "__";
    }

    @Override
    protected String printLeftBorder() {
        return "\\textbf{";
    }

    @Override
    protected String printRightBorder() {
        return "}";
    }
}
