package markup;

import java.util.List;

public class OrderedList extends AbstractTexList {
    public OrderedList(List<ListItem> texList) {
        super(texList);
    }

    protected String printLeftBorder() {
        return "\\begin{enumerate}";
    }

    protected String printRightBorder() {
        return "\\end{enumerate}";
    }

    @Override
    public void toTex(StringBuilder tex) {
        super.toTex(tex);
    }
}
