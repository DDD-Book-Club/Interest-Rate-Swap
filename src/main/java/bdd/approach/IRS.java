package bdd.approach;

public class IRS {

    public static double calculate(double fixing) {
        double notional = 1000000;
        double fixedRate = 6.25;
        double result  = notional * (fixedRate - fixing) / 12 / 100;
        return Math.floor(result * 100) / 100 ;
    }

}
