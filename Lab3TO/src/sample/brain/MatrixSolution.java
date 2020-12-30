package sample.brain;


import org.apache.commons.math3.linear.*;

public class MatrixSolution {
    public static double[] calculate(double[][] A, double[] C) {
        RealMatrix coeff = new Array2DRowRealMatrix(A, false);
        DecompositionSolver solver = new LUDecomposition(coeff).getSolver();
        RealVector constants = new ArrayRealVector(C, false);
        RealVector solution = solver.solve(constants);
        double[] R = new double[C.length];
        for (int i = 0; i < R.length; i++) {
            R[i] = solution.getEntry(i);
        }
        return R;
    }
}
