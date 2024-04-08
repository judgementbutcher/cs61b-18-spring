import static org.junit.Assert.*;

import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;

public class TestArrayDequeGold {
    @Test
    public void testArrayDeque(){
        StudentArrayDeque<Integer> a = new StudentArrayDeque<>();
        ArrayDequeSolution<Integer> b = new ArrayDequeSolution<>();
        String errorMessage = "";
        while(true) {
            double numberBetweenZeroAndOne = StdRandom.uniform();
            Integer num = StdRandom.uniform(10);
            if (numberBetweenZeroAndOne <= 0.25) {
                    a.addFirst(num);
                    b.addFirst(num);
                    errorMessage += "addFirst(" + num + ")" + "\n";
            }else if (numberBetweenZeroAndOne > 0.25 && numberBetweenZeroAndOne <= 0.5) {
                if(!a.isEmpty() && !b.isEmpty()){
                    Integer an = a.removeFirst();
                    Integer bn = b.removeFirst();
                    errorMessage += "removeFirst()" + "\n";
                    assertEquals(errorMessage,an, bn);
                }

            }else if (numberBetweenZeroAndOne > 0.5 && numberBetweenZeroAndOne <= 0.75) {
                if(!a.isEmpty() && !b.isEmpty()) {
                    Integer an = a.removeLast();
                    Integer bn = b.removeLast();
                    errorMessage += "removeLast()" + "\n";
                    assertEquals(errorMessage,an, bn);
                }
            }else if (numberBetweenZeroAndOne > 0.75) {
                a.addLast(num);
                b.addLast(num);
                errorMessage += "addLast(" + num + ")" + "\n";
            }
        }
    }
}
