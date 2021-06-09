public class Main {
    public static void main(String[] args) {
        ReadExcel re = new ReadExcel();
        re.makeStockPriceList();
        CurveFitting cf = new CurveFitting(53, re.stockPrice, 2);
        //cf.MSE();
        System.out.println();
    }
}
