package markup;

import java.util.List;

public class ListItem implements Texable {
    private List<ListElements> toTexList;

    public ListItem(List<ListElements> texList) {
        toTexList = texList;
    }

    @Override
    public void toTex(StringBuilder tex) {
        tex.append("\\item ");
        for (ListElements texElement : toTexList) {
            texElement.toTex(tex);
        }
    }
}
