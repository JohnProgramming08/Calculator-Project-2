class Operations {
    // Complete the calculation when the equals button is pressed
    double performCalculation(double number1, double number2, String operation) {
        if (operation.equals("+")) {
            return number1 + number2;
        }
        else if (operation.equals("-")) {
            return number1 - number2;
        }
        else if (operation.equals("x")) {
            return number1 * number2;
        }
        else if (operation.equals("÷") && number2 != 0) {
            return number1 / number2;
        }
        return 0.;
    }
}    