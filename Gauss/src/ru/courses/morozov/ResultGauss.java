package ru.courses.morozov;

/**
 * Created by Veyron on 07.11.2015.
 */
public class ResultGauss {
    private Vector resultVector;
    private int code;

    public ResultGauss(int code){
        if(code != 1 && code != -1){
            throw new IllegalArgumentException("Переданы не верные значения");
        }
        this.code = code;
    }

    public ResultGauss(int code, Vector resultVector){
        if(code != 0){
            throw new IllegalArgumentException("Переданны не верные значения");
        }
        this.code = code;
        this.resultVector = resultVector;
    }

    public String toString(){
        switch (this.code){
            case -1:
                return "Система не имеет решений";
            case 1:
                return "Система имеет бесконечное число решений";
            default:
                return resultVector.toString();
        }
    }
}
