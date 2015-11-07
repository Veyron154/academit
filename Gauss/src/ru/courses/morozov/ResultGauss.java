package ru.courses.morozov;

/**
 * Created by Veyron on 07.11.2015.
 */
public class ResultGauss {
    public enum Code {
        A_LOT_OF_SOLUTION(1),
        NO_SOLUTION(-1);

        private final int code;

        Code(int code) {
            this.code = code;
        }

        public int getCode() {
            return this.code;
        }
    }

    private Vector resultVector;
    private int code = 0;

    public ResultGauss(Code code) {
        this.code = code.getCode();
    }

    public ResultGauss(Vector resultVector) {
        this.resultVector = resultVector;
    }

    public Vector getResultVector() {
        return this.resultVector;
    }

    public int getCode() {
        return this.code;
    }

    public String toString() {
        switch (this.code) {
            case -1:
                return "Система не имеет решений";
            case 1:
                return "Система имеет бесконечное число решений";
            default:
                return resultVector.toString();
        }
    }
}
