package ssa;

import java.util.Stack;

public class RPNCalculator {

    public static void main(String[] args) {

        // hard coded problem
        char[] problem = new char[] { '1', '2', '+', '3', '*', '2', '-', '7', '/', '8', '*' };
        // char[] problem = new char[] {'1','2','3','+','*'};
        Stack<Integer> st = new Stack<Integer>();

        // contains the problem in the form of a string
        String writtenProblem = String.valueOf(problem);

        // this for loop will iterate through each item in the char array and
        // check if the item is an int or a math operator symbol
        // if the item is a math operator symbol, it will calculate the value
        for (char input : problem) {

            int value = Character.getNumericValue(input);

            if (value != -1) {
                st.push(value);

            } else {

                if (input == '+') {
                    int p1 = st.pop();
                    int p2 = st.pop();
                    int answer = p2 + p1;
                    st.push(answer);
                }

                if (input == '*') {
                    int p1 = st.pop();
                    int p2 = st.pop();
                    int answer = p2 * p1;
                    st.push(answer);

                }

                if (input == '-') {
                    int p1 = st.pop();
                    int p2 = st.pop();
                    int answer = p2 - p1;
                    st.push(answer);
                }

                if (input == '/') {
                    int p1 = st.pop();
                    int p2 = st.pop();
                    int answer = p2 / p1;
                    st.push(answer);
                }

            }

        }

        System.out.println("Calculating " + writtenProblem + ". Answer is " + st.pop());

    }

}
