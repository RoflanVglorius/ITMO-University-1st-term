package markup;

import java.util.List;

public class Paragraph implements Markdownable, ListElements {
    private List<Elements> elementsList;

    public Paragraph(List<Elements> elements) {
        elementsList = elements;
    }

    @Override
    public void toMarkdown(StringBuilder toMark) {
        for (Elements element : elementsList) {
            element.toMarkdown(toMark);
        }
    }

    @Override
    public void toTex(StringBuilder tex) {
        for (Elements element : elementsList) {
            element.toTex(tex);
        }
    }
}
