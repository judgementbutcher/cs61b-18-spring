import static org.junit.Assert.*;
import org.junit.Test;

public class FilkTest {

    @Test
    public void testFilk(){
        int a = 1;
        int b = 1;
        assertTrue(a == b);

        int c = 128;
        int d = 128;
        assertTrue(c==d);

        Integer p = 1;
        Integer q = 1;
        assertTrue(p == q);

        Integer e = 128;
        Integer f = 128;
        assertTrue(e.equals(f));

    }
}
