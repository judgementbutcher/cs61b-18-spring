public class TestStdDraw {
    public static void main(String[] args) {
        StdDraw.setScale(-100,100);
        StdDraw.setPenRadius(0.05);
        StdDraw.setPenColor(StdDraw.BLUE);
        StdDraw.point(0, 0);
        StdDraw.setPenColor(StdDraw.MAGENTA);
        StdDraw.line(20, 20, 80, 20);
    }
}
