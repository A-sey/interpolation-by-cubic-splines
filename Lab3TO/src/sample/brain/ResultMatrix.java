package sample.brain;

public class ResultMatrix {
    private double A[][];
    private double C[];
    private int row;

    public ResultMatrix(int count){
        int c = (count-1)*4;
        A = new double[c][c];
        C = new double[c];
        row = 0;
    }

    public void addLine(int number, double[] left, double right){
        A[number] = left;
        C[number] = right;
    }

    public void addLine(double[] left, double right){
        A[row] = left;
        C[row] = right;
        row++;
    }

    public double[][] getA() {
        return A;
    }

    public double[] getC() {
        return C;
    }
}
