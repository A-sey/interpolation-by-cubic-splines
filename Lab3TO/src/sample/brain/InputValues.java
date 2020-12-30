package sample.brain;

public class InputValues {
    private final double[] X;
    private final double[] Y;

    public InputValues(int count) {
        X = new double[count];
        Y = new double[count];
    }

    public InputValues(double... val) {
        int count = val.length / 2;
        X = new double[count];
        Y = new double[count];
        for (int i = 0; i < val.length; i++) {
            if (i % 2 == 0) {
                X[i / 2] = val[i];
            } else {
                Y[i / 2] = val[i];
            }
        }
    }

    public double getX(int i) {
        return X[i];
    }

    public double getY(int i) {
        return Y[i];
    }

    public int getCount(){
        return X.length;
    }
}
