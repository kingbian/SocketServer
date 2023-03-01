import java.util.StringTokenizer;

/**
 * basic math calculator to compute math
 */
public class Calculator  {
    /**
     * method takes in a string and calculates the mathematical operation
     * @param input
     * @return returns the answer to the equation
     * using Scanner class .split(), get the math operators
     */
    public static int compute(String input) {
        int answer = 0;
        try {
            // return a substring of numeric arrays
            String [] parts= input.split("[\\+\\-\\*\\/\\%]"); // split input based on operator
            int num1 = Integer.parseInt(parts[0].trim());// converts the first element of the parts array to an integer
            String operator = input.replaceAll("[^\\+\\-\\*\\/\\%]", ""); // replace all elements but arithmetic operators. get the operator as a string
            int num2 = Integer.parseInt(parts[1].trim());//converts the second element of the parts array to an integer
            answer = 0;
            if (operator.contains("*")) {
                answer = num1 * num2;
            } else if (operator.contains("+")) {
                answer = (num1 + num2);
            } else if (operator.contains("-")) {
                answer = num1 - num2;
            } else if (operator.contains("%")) {
                return answer = num1 % num2;
            } else if (operator.contains("/")) {
                answer = num1 / num2;
            }else {
                answer= num1^num2;
            }
        } catch (NumberFormatException e) {
            //System.out.println("Please enter equation in the form: operand1 operator operand2");
        }

        return answer;
    }
}



