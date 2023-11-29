package lab14;

import lab14lib.Generator;

public class SawToothGenerator implements Generator {
    private int period;
    private int state;

    public SawToothGenerator(int period) {
        this.period = period;
        state = 0;
    }

    public double next() {
        state++;
        return s(state % period);
    }

    private double s(int x) {
        return (double) x * 2 / period - 1;
    }
}
