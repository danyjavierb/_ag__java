import org.example.CodingChallenge;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CodingChallengeTest {
    @Test
    public void test() {
        var challenge = new CodingChallenge();
        assertEquals(3,challenge.solution("apppgate","appgate"));
        assertEquals(5,challenge.solution("vovzvoz","voz"));
    }
}
