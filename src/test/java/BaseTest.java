import java.util.ArrayList;
import java.util.List;

public class BaseTest {

    public List<Integer> color(String color) {
        String[] colors = color.replaceAll("rgba", "")
                .split(","); //rgba(119, 119, 119, 1)
        List<Integer> result = new ArrayList<Integer>();
        for (String number : colors) {
            result.add(Integer.parseInt(number.replaceAll("[^0-9]", "")));
        }
        return result;
    }

    public boolean colorIsGray(String colorRGBA) {
        List<Integer> result = color(colorRGBA);
        return result.get(0) == result.get(1) && result.get(1) == result.get(2);
    }

    public boolean colorIsRed(String colorRGBA) {
        List<Integer> result = color(colorRGBA);
        return result.get(1) == 0 && result.get(2) == 0;
    }

    public boolean boldFont(String font) {
        return Integer.parseInt(font) >= 700 || font.equals("bold");
    }

    public boolean crossOut(String linethrough) {
        return linethrough.equals("line-through");
    }
}


