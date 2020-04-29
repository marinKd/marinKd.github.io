import java.util.*;
import java.util.Scanner;

public class calculator_115 {

    // functions
    static double addition(ArrayList<Double> numbers) {
        double sum = 0;
        for (int i = 0; i < numbers.size(); i++) {
            sum = sum + numbers.get(i);
        }
        return sum;
    }

    static double subtraction(ArrayList<Double> numbers) {
        double result = numbers.get(0);
        for(int i = 1; i < numbers.size(); i++){
          result = result - numbers.get(i);
        }
        return result;
    }

    static double multiplication(ArrayList<Double> numbers) {
        double product = 1;
        for(int i = 0; i < numbers.size(); i++){
          product = product * numbers.get(i);
        }
        return product;
    }

    static double division(ArrayList<Double> numbers) {
      
        return numbers.get(0)/numbers.get(1);

    }

    static double exponents(ArrayList<Double> numbers) {
        double result = numbers.get(0);
        for (int i = 1; i < numbers.get(1); i++) {
            result = result * numbers.get(0);
        }
        return result;
    }

    public static void main(String[] args) throws Exception {
        double storedNum = 0;
        System.out.println("*****Marin's very own calculator*****"); 
        System.out.println("C to clear, Q to quit");
        System.out.println("Calculate:");        
        char action='n';                                                                       //action key, 'a'=addition        
        Scanner input = new Scanner(System.in);                       //take input, convert to string
        String line = input.nextLine();
        String[] tokens = line.split(" ");
        for(int t=0;t<tokens.length;t++){
                                                                                                           //create doubles array, take values if string is double
        ArrayList<Double> numbers = new ArrayList<Double>();
                                                                                                          //while(input.nextInt()!=0){
        do{
            for(int i=0;i<tokens.length;i++){
                                                                                                          //assign action per symbol found
                if(tokens[i].equals("+")){
                    action='a';
                }
                else if(tokens[i].equals("-")){
                    action='b';
                }
                else if(tokens[i].equals("*")){
                    action='c';
                }
                else if(tokens[i].equals("/")){
                    action='d';
                }
                else if(tokens[i].equals("^")){
                    action='e';
                }
                                                                                                            //check for doubles, make array of number values in the input line
                else {
                    try
                    {   
                        numbers.add(Double.parseDouble(tokens[i]));
                        //add double to array
                    }
                    catch(NumberFormatException exception)
                    {
                        //not a double
                    }  
                }
            }
            //perform action
            
            if(action=='a'){
              System.out.println(addition(numbers));
              storedNum = addition(numbers);
            }
            if(action=='b'){
              System.out.println(subtraction(numbers));
              storedNum = subtraction(numbers);
            }
            if(action=='c'){
              System.out.println(multiplication(numbers));
              storedNum = multiplication(numbers);
            }
            if(action=='d'){
              System.out.println(division(numbers));
              storedNum = division(numbers);
            }
            if(action=='e'){
              System.out.println(exponents(numbers));
              storedNum = exponents(numbers);
            }
            //reset array
            numbers.clear();
            System.out.println("");
            //reset action key
            action='n';
            //take input, convert to string
            input = new Scanner(System.in);
            line = input.nextLine();
            if (storedNum != 0){
              numbers.add(0, storedNum);
            }
            tokens = line.split("\\s+");
            //if user enters "C", operand is cleared
            if(tokens[0].equalsIgnoreCase("c")){
              storedNum = 0;
              System.out.println("0");
            }
              
         }while(!tokens[0].equalsIgnoreCase("q"));        //esc program if operand is "Q"
         input.close();
       
         }
    }
}
