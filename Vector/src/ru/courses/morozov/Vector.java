package ru.courses.morozov;

/**
 * Created by Veyron on 12.10.2015.
 */
class Vector {
    private double[] vectorComponents;

    public Vector(int size) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }
        this.vectorComponents = new double[size];
    }

    public Vector(Vector copiedVector) {
        this.vectorComponents = new double[copiedVector.vectorComponents.length];
        System.arraycopy(copiedVector.vectorComponents, 0, this.vectorComponents, 0,
                copiedVector.vectorComponents.length);
    }

    public Vector(int size, double[] vectorComponents) {
        if (size <= 0) {
            throw new IllegalArgumentException("Размерность вектора должна быть больше нуля");
        }
        this.vectorComponents = new double[size];
        if (size < vectorComponents.length) {
            System.arraycopy(vectorComponents, 0, this.vectorComponents, 0, size);
        } else {
            System.arraycopy(vectorComponents, 0, this.vectorComponents, 0, vectorComponents.length);
        }

    }

    public Vector(double[] vectorComponents) {
        this.vectorComponents = new double[vectorComponents.length];
        System.arraycopy(vectorComponents, 0, this.vectorComponents, 0, vectorComponents.length);
    }

    public int getSize() {
        return this.vectorComponents.length;
    }

    public String toString() {
        StringBuilder builder = new StringBuilder();
        builder.append("{ ");
        for (double d : this.vectorComponents) {
            builder.append(d)
                    .append(", ");
        }
        builder.append("}");
        return builder.deleteCharAt(builder.lastIndexOf(",")).toString();
    }

    public Vector addition(Vector addedVector) {
        this.vectorComponents = Vector.addition(this, addedVector).vectorComponents;
        return this;
    }

    public Vector subtraction(Vector deductibleVector) {
        this.vectorComponents = Vector.subtraction(this, deductibleVector).vectorComponents;
        return this;
    }

    public Vector multiplyByScalar(double scalar) {
        for (int i = 0; i < this.vectorComponents.length; ++i) {
            this.vectorComponents[i] *= scalar;
        }
        return this;
    }

    public Vector turn() {
        final int TURN_COEFFICIENT = -1;
        return this.multiplyByScalar(TURN_COEFFICIENT);
    }

    public double getLength() {
        double sumOfSquares = 0;
        for (double vectorComponent : this.vectorComponents) {
            sumOfSquares += Math.pow(vectorComponent, 2);
        }
        return Math.sqrt(sumOfSquares);
    }

    public void setVectorComponent(int numberOfComponent, double valueOfComponent) {
        this.vectorComponents[numberOfComponent] = valueOfComponent;
    }

    public double getVectorComponent(int numberOfComponent) {
        return this.vectorComponents[numberOfComponent];
    }

    @Override
    public boolean equals(Object comparedObject) {
        if (this == comparedObject) {
            return true;
        }
        if (comparedObject == null) {
            return false;
        }
        if (this.getClass() != comparedObject.getClass()) {
            return false;
        }
        Vector comparedVector = (Vector) comparedObject;
        if (this.vectorComponents.length != comparedVector.vectorComponents.length) {
            return false;
        }
        for (int i = 0; i < this.vectorComponents.length; ++i) {
            if (!UserFunctions.testToEquality(this.vectorComponents[i], comparedVector.vectorComponents[i])) {
                return false;
            }
        }
        return true;
    }

    public int hashCode() {
        final int PRIME = 31;
        int result = 1;
        result = result * PRIME + vectorComponents.length;
        for (double vectorComponent : vectorComponents) {
            result = result * PRIME + (int) (vectorComponent / UserFunctions.EPSILON);
        }
        return result;
    }

    private static double[] extensionVector(double[] vectorComponents, int size) {
        double[] auxiliaryArray = new double[size];
        System.arraycopy(vectorComponents, 0, auxiliaryArray, 0, vectorComponents.length);
        return auxiliaryArray;
    }

    public static Vector subtraction(Vector vector1, Vector vector2) {
        Vector auxiliaryVector1 = vector1.vectorComponents.length > vector2.vectorComponents.length ?
                new Vector(vector1) :
                new Vector(Vector.extensionVector(vector1.vectorComponents, vector2.vectorComponents.length));
        Vector auxiliaryVector2 = vector2.vectorComponents.length > vector1.vectorComponents.length ?
                new Vector(vector2) :
                new Vector(Vector.extensionVector(vector2.vectorComponents, vector1.vectorComponents.length));
        for (int i = 0; i < auxiliaryVector1.vectorComponents.length; ++i) {
            auxiliaryVector1.vectorComponents[i] -= auxiliaryVector2.vectorComponents[i];
        }
        return auxiliaryVector1;
    }

    public static Vector addition(Vector vector1, Vector vector2) {
        Vector auxiliaryVector1 = vector1.vectorComponents.length > vector2.vectorComponents.length ?
                new Vector(vector1) : new Vector(vector2);
        Vector auxiliaryVector2 = vector1.vectorComponents.length > vector2.vectorComponents.length ?
                new Vector(Vector.extensionVector(vector2.vectorComponents, vector1.vectorComponents.length)) :
                new Vector(Vector.extensionVector(vector1.vectorComponents, vector2.vectorComponents.length));
        for (int i = 0; i < auxiliaryVector1.vectorComponents.length; ++i) {
            auxiliaryVector1.vectorComponents[i] += auxiliaryVector2.vectorComponents[i];
        }
        return auxiliaryVector1;
    }

    public static double scalarMultiply(Vector vector1, Vector vector2) {
        double sum = 0;
        int minLength = Math.min(vector1.vectorComponents.length, vector2.vectorComponents.length);
        for (int i = 0; i < minLength; ++i) {
            sum += vector1.vectorComponents[i] * vector2.vectorComponents[i];
        }
        return sum;
    }
}
