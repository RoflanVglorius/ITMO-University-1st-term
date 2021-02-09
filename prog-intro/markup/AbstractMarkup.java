package markup;

import java.util.List;

public abstract class AbstractMarkup implements Elements {
    private List<Elements> markupList;

    public AbstractMarkup(List<Elements> elementsList) {
        markupList = elementsList;
    }

    @Override
    public void toMarkdown(StringBuilder toMark) {
        String mark = printMarkup();
        toMark.append(mark);
        for (Elements element : markupList) {
            element.toMarkdown(toMark);
        }
        toMark.append(mark);
    }

    @Override
    public void toTex(StringBuilder tex) {
        tex.append(printLeftBorder());
        for (Elements element : markupList) {
            element.toTex(tex);
        }
        tex.append(printRightBorder());
    }

    protected abstract String printMarkup();
    protected abstract String printLeftBorder();
    protected abstract String printRightBorder();

}

