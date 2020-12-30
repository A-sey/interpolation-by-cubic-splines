package sample.brain;

import sample.brain.InputValues;
import sample.brain.ResultMatrix;

public class Stages {
    static private boolean mute = false;
    public void stageOne(ResultMatrix matrix, InputValues val) {
        int size = (val.getCount() - 1) * 4;
        for (int i = 0; i < val.getCount() - 1; i++) {
            double[] n1 = createLineOne(size, i, val.getX(i), val.getX(i));
            matrix.addLine(n1, val.getY(i));
            double[] n2 = createLineOne(size, i, val.getX(i), val.getX(i + 1));
            matrix.addLine(n2, val.getY(i + 1));
            if(!mute){
                int n = i+1;
                double xi = val.getX(i);
                double xp = val.getX(i+1);
                double y = val.getY(i);
                System.out.printf("S%d = a%d + b%d(x-x%d) + c%d(x-x%d)^2 + d%d(x-x%d)^3\n", n,n,n,i,n,i,n,i);
                System.out.printf("S%d = a%d + b%d(x-%.2f) + c%d(x-%.2f)^2 + d%d(x-%.2f)^3\n",n,n,n,xi,n,xi,n,xi);
                System.out.printf("%.2f = a%d + b%d(%.2f-%.2f) + c%d(%.2f-%.2f)^2 + d%d(%.2f-%.2f)^3\n",y,n,n,xi,xi,n,xi,xi,n,xi,xi);
                System.out.printf("%.2f = a%d + b%d(%.2f-%.2f) + c%d(%.2f-%.2f)^2 + d%d(%.2f-%.2f)^3\n\n",y,n,n,xp,xi,n,xp,xi,n,xp,xi);
            }
        }
    }

    private double[] createLineOne(int size, int pos, double x1, double x2) {
        double[] line = new double[size];
        double diff = x2 - x1;
        line[pos * 4 + 0] = 1;
        line[pos * 4 + 1] = diff;
        line[pos * 4 + 2] = diff * diff;
        line[pos * 4 + 3] = diff * diff * diff;
        return line;
    }

    public void stageTwo(ResultMatrix matrix, InputValues val) {
        int size = (val.getCount() - 1) * 4;
        for (int i = 0; i < val.getCount() - 2; i++) {
            double[] l = createLineTwo(size, i, val.getX(i), val.getX(i + 1));
            matrix.addLine(l, 0);
            if(!mute){
                int n = i+1;
                double xi = val.getX(i);
                double xp = val.getX(i+1);
                System.out.printf("S'%d(x%d)=S'%d(x%d)\n",n,n,n+1,n);
                System.out.printf("S'%d = b%d + 2c%d(x-x%d) + 3d%d(x-x%d)^2\n",n,n,n,i,n,i);
                System.out.printf("S'%d = b%d + 2c%d(x-x%d) + 3d%d(x-x%d)^2\n",n+1,n+1,n+1,i+1,n+1,i+1);
                System.out.printf("S'%d = b%d + 2c%d(x-%.3f) + 3d%d(x-%.3f)^2\n",n,n,n,xi,n,xi);
                System.out.printf("S'%d = b%d + 2c%d(x-%.3f) + 3d%d(x-%.3f)^2\n",n+1,n+1,n+1,xp,n+1,xp);
                System.out.printf("b%d + 2c%d(%.3f - %.3f) + 3d%d(%.3f - %.3f)^2 = b%d + 2c%d(%.3f - %.3f) + 3d%d(%.3f - %.3f)^2\n\n",
                        n,n,xp,xi,n,xp,xi,n+1,n+1,xp,xp,n+1,xp,xp);
            }
        }
    }

    private double[] createLineTwo(int size, int pos, double x1, double x2) {
        double[] line = new double[size];
        double diff = x2 - x1;
        line[pos * 4 + 1] = 1;
        line[pos * 4 + 2] = 2 * diff;
        line[pos * 4 + 3] = 3 * diff * diff;
        line[pos * 4 + 5] = -1;
        return line;
    }

    public void stageThree(ResultMatrix matrix, InputValues val) {
        int size = (val.getCount() - 1) * 4;
        for (int i = 0; i < val.getCount() - 2; i++) {
            double[] l = createLineThree(size, i, val.getX(i), val.getX(i + 1));
            matrix.addLine(l, 0);
            if(!mute){
                int n = i+1;
                double xi = val.getX(i);
                double xp = val.getX(i+1);
                System.out.printf("S''%d(x%d) = S''%d(x%d)\n", n, n, n+1, n);
                System.out.printf("S''%d = 2c%d + 6d%d(x-%.3f)\n", n, n, n, xi);
                System.out.printf("S''%d = 2c%d + 6d%d(x-%.3f)\n", n+1, n+1, n+1, xp);
                System.out.printf("2c%d + 6d%d(%.3f - %.3f) = 2c%d + 6d%d(%.3f - %.3f)\n\n",n, n, xp, xi,n+1, n+1, xp, xp);
            }
        }
    }

    private double[] createLineThree(int size, int pos, double x1, double x2) {
        double[] line = new double[size];
        double diff = x2 - x1;
        line[pos * 4 + 2] = 2;
        line[pos * 4 + 3] = 6 * diff;
        line[pos * 4 + 6] = -2;
        return line;
    }

    public void stageFour(ResultMatrix matrix, InputValues val){
        int size = (val.getCount()-1)*4;
        double[] l1 = createLineFour(size, 0, val.getX(0), val.getX(0));
        matrix.addLine(l1, 0);
        double[] l2 = createLineFour(size, val.getCount()-2, val.getX(val.getCount()-2), val.getX(val.getCount()-1));
        matrix.addLine(l2, 0);
        if(!mute){
            int i = 0;
            int n = 1;
            double xi = val.getX(i);
            double xp = val.getX(n);
            System.out.printf("S''%d(x%d) = 0\n", n, i);
            System.out.printf("2c%d + 6d%d(%.3f-%.3f) = 0\n", n, n, xp, xi);
            ///
            i = val.getCount()-2;
            n = i+1;
            xi = val.getX(i);
            xp = val.getX(n);
            System.out.printf("S''%d(x%d) = 0\n", n, n);
            System.out.printf("2c%d + 6d%d(%.3f-%.3f) = 0\n\n", n, n, xp, xi);
        }
    }

    private double[] createLineFour(int size, int pos, double x1, double x2){
        double[] line = new double[size];
        double diff = x2 - x1;
        line[pos * 4 + 2] = 2;
        line[pos * 4 + 3] = 6 * diff;
        return line;
    }
}
