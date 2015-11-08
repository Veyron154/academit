package ru.courses.morozov;

/**
 * Created by Veyron on 07.11.2015.
 */
public class ResultGauss {
    private Vector resultVector;
    private GaussResultCode code = GaussResultCode.ONE_SOLUTION;

    public ResultGauss(GaussResultCode code) {
        this.code = code;
    }

    public ResultGauss(Vector resultVector) {
        this.resultVector = resultVector;
    }

    public Vector getResultVector() {
        return this.resultVector;
    }

    public GaussResultCode getCode() {
        return this.code;
    }

    public String toString() {
        switch (this.code) {
            case NO_SOLUTION:
                return "Система не имеет решений";
            case A_LOT_OF_SOLUTIONS:
                return "Система имеет бесконечное число решений";
            default:
                return resultVector.toString();
        }
    }
}
