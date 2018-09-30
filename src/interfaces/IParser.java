package interfaces;

import java.util.List;

public interface IParser {
    List<String> deserializeJSON(String json);
    List<String> parse(String text);
}
