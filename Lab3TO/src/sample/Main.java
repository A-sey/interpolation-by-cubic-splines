package sample;

import javafx.application.Application;
import javafx.collections.ObservableList;
import javafx.scene.Scene;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import sample.brain.InputValues;
import sample.brain.MatrixSolution;
import sample.brain.ResultMatrix;
import sample.brain.Stages;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
//        InputValues inputValues = new InputValues(1, 2, 2, 3, 4, 1, 7, 4); //Test
//        InputValues inputValues = new InputValues(1, 2, 4, 5, 6, 4, 8, 4.5); //First
//        InputValues inputValues = new InputValues(generate(0.5)); //Second1
        InputValues inputValues = new InputValues(generate(0.25)); //Second2

        ResultMatrix matrix = new ResultMatrix(inputValues.getCount());
        Stages stages = new Stages();
        stages.stageOne(matrix, inputValues);
        stages.stageTwo(matrix, inputValues);
        stages.stageThree(matrix, inputValues);
        stages.stageFour(matrix, inputValues);

        print(matrix, inputValues.getCount());
        double[] res = MatrixSolution.calculate(matrix.getA(), matrix.getC());
        print(res);
        System.out.println("\nEnd");

        draw(primaryStage, inputValues, res);
    }

    public static void draw(Stage primaryStage, InputValues values, double[] res) {
        primaryStage.setTitle("Result");
        NumberAxis X = new NumberAxis();
        X.setForceZeroInRange(false);
        NumberAxis Y = new NumberAxis();
        Y.setForceZeroInRange(false);
        LineChart chart = new LineChart(X, Y);
        chart.setLegendVisible(false);
        chart.setCreateSymbols(false);
        double dx = (values.getX(values.getCount() - 1) - values.getX(0)) / 200;
        double errSum = 0;
        int errCount = 0;

        for (int i = 0; i < values.getCount() - 1; i++) {
            XYChart.Series series = new XYChart.Series();
            chart.getData().add(series);
            ObservableList datas = series.getData();
            for (double x = values.getX(i); x < values.getX(i + 1); x += dx) {
                double diff = (x - values.getX(i));
                double a = res[i * 4 + 0];
                double b = res[i * 4 + 1];
                double c = res[i * 4 + 2];
                double d = res[i * 4 + 3];
                double y = a + b * diff + c * diff * diff + d * diff * diff * diff;
                datas.add(new XYChart.Data(x, y));
                errSum+=(y-f(x));
                errCount++;
            }
            datas.add(new XYChart.Data(values.getX(i), values.getY(i)));
        }
        System.out.printf("Err:\n\t%f\n",errSum/errCount);

        primaryStage.setScene(new Scene(chart));
        primaryStage.show();
    }

    private static void print(ResultMatrix matrix, int valCount) {
        for (int i = 1; i < valCount; i++) {
            System.out.printf("a%d\t\tb%d\t\tc%d\t\td%d\t\t", i, i, i, i);
        }
        System.out.print("y\n");
        for (int j = 0; j < matrix.getC().length; j++) {
            double[] line = matrix.getA()[j];
            for (double v : line) {
                System.out.printf("%.3f\t", v);
            }
            System.out.printf("%.3f\n", matrix.getC()[j]);
        }
    }

    private static void print(double[] res) {
        System.out.println();
        for (int i = 1; i <= res.length / 4; i++) {
            System.out.printf("a%d\t\tb%d\t\tc%d\t\td%d\t\t", i, i, i, i);
        }
        System.out.println();
        for (double d : res) {
            System.out.printf("%.3f\t", d);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private static double[] generate(double step) {
        double[] res = new double[(int) (4.5 / step + 1) * 2];
        double x = -1.5;
        for (int i = 0; i < res.length; i += 2) {
            double y = f(x);
            res[i] = x;
            res[i + 1] = y;
            x += step;
        }
        return res;
    }

    private static double f(double x){
        return x * x * x - 2 * x * x - 3 * x + 1;
    }

/*    private static double findError(ResultMatrix matrix, InputValues val){
        double dx = (val.getX(val.getCount()-1)- val.getX(0))/200;

    }*/
}
