package markup;

import java.util.List;

public abstract class AbstractTexList implements ListElements {
    private List<ListItem> toTexList;

    public AbstractTexList(List<ListItem> texList) {
        toTexList = texList;

    }

    @Override
    public void toTex(StringBuilder tex) {
        tex.append(printLeftBorder());
        for (ListItem texElement : toTexList) {
            texElement.toTex(tex);
        }
        tex.append(printRightBorder());
    }

    protected abstract String printLeftBorder();

    protected abstract String printRightBorder();

}
