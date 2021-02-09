package markup;

public class Text implements Elements {
    private String text;

    public Text(String input) {
        text = input;
    }

    @Override
    public void toMarkdown(StringBuilder toMark) {
        toMark.append(text);
    }

    @Override
    public void toTex(StringBuilder tex) {
        tex.append(text);
    }
}
