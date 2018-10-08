package interfaces;

import java.util.List;

public interface Parser {
    List<String> deserializeJSON(String json);
    List<String> parse(String text);
}
