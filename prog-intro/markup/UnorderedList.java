package markup;

import java.util.List;

public class UnorderedList extends AbstractTexList {
    public UnorderedList(List<ListItem> texList) {
        super(texList);
    }

    @Override
    protected String printLeftBorder() {
        return "\\begin{itemize}";
    }

    @Override
    protected String printRightBorder() {
        return "\\end{itemize}";
    }
}
