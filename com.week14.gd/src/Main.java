public class Main {
    public static void main(String[] args) {
        GradientDescent gd = new GradientDescent();
        double x0 = gd.solve(x -> 3*x*x - 20*x + 13, 1000, 0.001, -30, 30);
        // 6x - 20 = 3.333333 <- 변곡점 x
        // 3x^2 - 20x + 13 <- 20.3333
        System.out.println(x0);
        System.out.println(gd.hist);
    }
}
