import com.firstaid.AnswerCreator;
import com.firstaid.DatabaseHandler;
import com.firstaid.Main;
import junit.framework.TestCase;
import org.junit.Assert;

public class Tests extends TestCase {
    public void testReturnNull_WhenInputIsNotAccurate() {
        var dbPath = new Main().getClass().getResource("db.db").toString();
        var dbHandler = new DatabaseHandler();
        dbHandler.connect(dbPath);
        var answerCreator = new AnswerCreator(dbHandler);

        var result = answerCreator.getAnswer("Стало нехорошо");

        Assert.assertNull(result);
        dbHandler.disconnect();
    }

    public void testReturnCorrectAnswer_WhenInputIsCorrect() {
        var dbPath = new Main().getClass().getResource("db.db").toString();
        var dbHandler = new DatabaseHandler();
        dbHandler.connect(dbPath);
        var answerCreator = new AnswerCreator(dbHandler);

        var expected = "В случае обморока необходимо уложить пострадавшею в горизонтальное положение, приподнять" +
                " ноги, расстегнуть стесняющую одежду, обеспечить доступ свежего воздуха, лицо обрызгать холодной" +
                " водой, дать понюхать нашатырный спирт или уксус на ватке, натереть этими средствами виски, надавить" +
                " болевую точку под носом или помассировать ее.";
        var result = answerCreator.getAnswer("Потерял сознание");

        Assert.assertEquals(expected, result);
        dbHandler.disconnect();
    }
}
