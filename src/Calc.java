import java.util.Arrays;

public class Calc {
    private int[] leftNumbers;
    private int[] rightNumbers;
    private float result;
    private boolean isCalculated;
    private char sign;

    Calc() {
        clear();
    }

    public float getResult() {
        return result;
    }

    public char getSign() {
        return sign;
    }

    public void setSign(char sign) {
        if (isCalculated) {
            clear();
        }
        this.sign = sign;
    }

    public void addNumber(int number) {
        if (isCalculated) {
            clear();
        }
        if(sign == 0) {
            leftNumbers = addInArray(leftNumbers, number);
        } else {
            rightNumbers = addInArray(rightNumbers, number);
        }
    }

    public void clear() {
        sign = 0;
        leftNumbers = new int[]{0};
        rightNumbers = new int[]{0};
        result = 0.0f;
        isCalculated = false;
    }

    public void calculate() {
        float  leftMultiplier;
        float  rightMultiplier;
        leftMultiplier = getMultiplier(leftNumbers);
        rightMultiplier = getMultiplier(rightNumbers);

        switch (sign) {
            case '+': {
                result = leftMultiplier +  rightMultiplier;
                break;
            }
            case '-': {
                result = leftMultiplier - rightMultiplier;
                break;
            }
            case '*': {
                result = leftMultiplier * rightMultiplier;
                break;
            }
            case '/': {
                result = leftMultiplier / rightMultiplier;
                break;
            }
        }
        isCalculated = true;

    }

    private int getMultiplier(int[] array) {
        int multiplier = 0;
        for ( int i = 0; i < array.length; i++ ) {
            multiplier += array[i] * Math.pow(10, array.length - 1 - i);
        }
        return multiplier;
    }

    private int[] addInArray(int[] array, int number) {
        int[] newArray;
        if (isNumberEmpty(array)) {
            newArray = new int[]{number};
        } else {
            newArray = Arrays.copyOf(array, array.length + 1);
            newArray[array.length] = number;

        }
        return  newArray;
    }

    private boolean isNumberEmpty(int[] array) {
        return array.length == 1 && array[0] == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        if( leftNumbers.length > 0){
            sb.append(getMultiplier(leftNumbers));
        }
        if (sign != 0) {
            sb.append(sign);
        }
        if(!isNumberEmpty(rightNumbers)){
            sb.append(getMultiplier(rightNumbers));
        }
        if (isCalculated) {
            sb.append("=");
            sb.append(result);

        }
        return sb.toString();
    }
}
