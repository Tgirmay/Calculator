
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import java.util.*;

/**
 * this class contains the code for the GUI of the calculator
 */
public class CalcGUI extends Application {
    private TextField result = new TextField();
    int parenthasesCount = 0;

    /**
     * Constructs the GUI
     * @param primaryStage name of the whole interface
     */
    public void start(Stage primaryStage){
        primaryStage.setTitle("Thomas' Calculator!");
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);

        result.setEditable(false);

        //adds all buttons (0-9) and operators (,),+,-,*, and =, sets the dimensions, and adds functionality
        Button button9 = new Button("9");
        button9.setPrefSize(50, 50);
        button9.setOnAction(e ->{
                buttonOutput(9);
        });

        Button button8 = new Button("8");
        button8.setPrefSize(50, 50);
        button8.setOnAction(e ->{
            buttonOutput(8);
        });

        Button button7 = new Button("7");
        button7.setPrefSize(50, 50);
        button7.setOnAction(e ->{
            buttonOutput(7);
        });

        Button button6 = new Button("6");
        button6.setPrefSize(50, 50);
        button6.setOnAction(e ->{
            buttonOutput(6);
        });

        Button button5 = new Button("5");
        button5.setPrefSize(50, 50);
        button5.setOnAction(e ->{
            buttonOutput(5);
        });

        Button button4 = new Button("4");
        button4.setPrefSize(50, 50);
        button4.setOnAction(e ->{
            buttonOutput(4);
        });

        Button button3 = new Button("3");
        button3.setPrefSize(50, 50);
        button3.setOnAction(e ->{
            buttonOutput(3);
        });

        Button button2 = new Button("2");
        button2.setPrefSize(50, 50);
        button2.setOnAction(e ->{
            buttonOutput(2);
        });

        Button button1 = new Button("1");
        button1.setPrefSize(50, 50);
        button1.setOnAction(e ->{
            buttonOutput(1);
        });

        Button button0 = new Button("0");
        button0.setPrefSize(50, 50);
        button0.setOnAction(e ->{
            buttonOutput(0);
        });

        Button buttonOpenP = new Button("(");
        buttonOpenP.setPrefSize(50, 50);
        buttonOpenP.setOnAction(e ->{
            this.parenthasesCount++;
            buttonOutput("(");
        });

        Button buttonClosedP = new Button(" )");
        buttonClosedP.setPrefSize(50, 50);
        buttonClosedP.setOnAction(e ->{
            this.parenthasesCount--;
            buttonOutput(")");

        });

        Button buttonEquals = new Button("=");
        buttonEquals.setPrefSize(50, 50);
        buttonEquals.setOnAction(e ->{
            result.setText(calculate());
        });

        Button buttonMult = new Button("*");
        buttonMult.setPrefSize(50, 50);
        buttonMult.setOnAction(e ->{
            buttonOutput("*");
        });

        Button buttonSub = new Button("- ");
        buttonSub.setPrefSize(50, 50);
        buttonSub.setOnAction(e ->{
            buttonOutput("-");
        });

        Button buttonAdd = new Button("+ ");
        buttonAdd.setPrefSize(50, 50);
        buttonAdd.setOnAction(e ->{
            buttonOutput("+");
        });


        //sets the GridPane of Calculator (scene dimensions 200x200) and positions all of the buttons and the textbox
        GridPane gridPane = new GridPane();

        gridPane.add(result, 0,0,500,1);

        gridPane.add(button7, 0, 50, 50, 50);
        gridPane.add(button8, 50, 50, 50, 50);
        gridPane.add(button9, 100, 50, 50, 50);
        gridPane.add(buttonMult, 150, 50, 60, 50);
        gridPane.add(button4, 0, 100, 50, 50);
        gridPane.add(button5, 50, 100, 50, 50);
        gridPane.add(button6, 100, 100, 50, 50);
        gridPane.add(buttonSub, 150, 100, 60, 50);
        gridPane.add(button1, 0, 150, 50, 50);
        gridPane.add(button2, 50, 150, 50, 50);
        gridPane.add(button3, 100, 150, 50, 50);
        gridPane.add(buttonAdd, 150, 150, 60, 50);
        gridPane.add(button0, 0, 200, 50, 50);
        gridPane.add(buttonOpenP, 50, 200, 50, 50);
        gridPane.add(buttonClosedP, 100, 200, 50, 50);
        gridPane.add(buttonEquals, 150, 200, 60, 50);


        Scene scene = new Scene(gridPane, 200, 200);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * called by button press and displays the corresponding number in the box
     * @param i number displayed on button
     */

    public void buttonOutput(Integer i ){
        result.setText( result.getCharacters() + i.toString());
        return;
    }

    /**
     * called by button press and displays the corresponding operator in the box
     * @param s -, +, or *
     */
    public void buttonOutput(String s ){
        result.setText( result.getCharacters() + s);
        return;
    }

    /**
     * Checks what is typed into the calc for valid input
     * if valid, passes expression t obe converted to Reverse Polish Notation
     * Carries out the operations by adding numbers to stack and operating once an operation has been reached
     * @return String that is the answer
     */
    public String calculate() {
        //run the check for valid input
        if (!isValidInput()) {
            return "Invalid Input";
        }

        else {
            ArrayList <String> infixEquation = new ArrayList<>();
            String input = result.getCharacters().toString();
            ArrayList<Object> equationinRPN = new ArrayList<>();
            Stack<MyLong> longStack = new Stack<>();
            for (int i = 0; i < input.length(); i++){
                infixEquation.add("" + input.charAt(i));
            }
            //convert infix equation to RPN so computer can work on it
            equationinRPN = convertRPN(infixEquation);
            System.out.println(equationinRPN);
            for(int i = 0; i < equationinRPN.size(); i++){
                if(!longStack.isEmpty() && ( longStack.peek().numberContainer.isEmpty())){
                    longStack.pop();
                }
                System.out.println(longStack);
                if(equationinRPN.get(i) instanceof MyLong ){
                   longStack.push(((MyLong) equationinRPN.get(i)));
                }
                else if (equationinRPN.get(i).equals("*")){
                    MyLong second = longStack.pop();
                    MyLong first = longStack.pop();
                    longStack.add(first.multiply(second));
                }
                else if (equationinRPN.get(i).equals("+")){
                    MyLong second= longStack.pop();
                    MyLong first = longStack.pop();
                    longStack.add(first.add(second));
                }
                else if (equationinRPN.get(i).equals("-")){
                    MyLong second= longStack.pop();
                    MyLong first = longStack.pop();
                    longStack.add(first.subtract(second));
                }
            }
            return longStack.pop().toString();
        }

    }

    /**
     * takes an expression written in infix notation and converts to Reverse Polish Notation
     * If a number is reached, put to output
     * if an operator is reached, add to stack if lowe precedence than top of stack
     * if not, keep popping until true then push them all back in
     * push them into the output
     * if a ) is reached, remove until ( is reached then shove operations to output
     * @param equation ArrayList that was created in the calculate function. Holds what was put into equation
     * @return
     */

    public ArrayList<Object> convertRPN(ArrayList<String> equation){
        String longSetter = new String();
        Stack operators =new Stack<>();
        ArrayList<Object> equationWithMyLong = new ArrayList<>();
        ArrayList<Object> equationInRPN = new ArrayList<>();
        Map<String, Integer> precedence = new HashMap<>();
        precedence.put("*", 5);
        precedence.put("+", 4);
        precedence.put("-", 4);
        precedence.put("(", 0);

        for (int i = 0; i < equation.size(); i++) {
            if (!(equation.get(i).equals("+") || equation.get(i).equals("-") || equation.get(i).equals("*") || equation.get(i).equals("(") || equation.get(i).equals(")"))) {
                longSetter += equation.get(i);
                if (i == equation.size() - 1){
                    MyLong temp = new MyLong();
                    temp.setLong(longSetter);
                    equationWithMyLong.add(temp);
                    longSetter = "";
                }
            }
            else {
                MyLong temp = new MyLong();
                temp.setLong(longSetter);
                equationWithMyLong.add(temp);
                equationWithMyLong.add(equation.get(i));
                longSetter = "";
            }

        }
        System.out.println("here: " + equationWithMyLong);

        for (int i =0; i < equationWithMyLong.size(); i++){

            if (! (equationWithMyLong.get(i).equals("+") || equationWithMyLong.get(i).equals("-") ||equationWithMyLong.get(i).equals("*") || equationWithMyLong.get(i).equals("(") ||equationWithMyLong.get(i).equals(")"))){
                equationInRPN.add(equationWithMyLong.get(i));
            }
            else if( equationWithMyLong.get(i).equals("(")){
                operators.push(equationWithMyLong.get(i));

            }
            else if (equationWithMyLong.get(i).equals(")")){
                while(!operators.peek().equals("(")){
                    equationInRPN.add(operators.pop());

                }
                operators.pop();
            }

            else{

                while ((!operators.empty()) && ((precedence.get(operators.peek()) >= precedence.get(equationWithMyLong.get(i))))) {
                    equationInRPN.add(operators.pop());
                }
                operators.push(equationWithMyLong.get(i));
            }

        }
        while (!operators.empty()) {
            equationInRPN.add(operators.pop());
        }


        return equationInRPN;
    }


    /**
     * Checks validity of the passed problem by calculate()
     * @return boolean
     */
    public boolean isValidInput(){
        String problem = result.getCharacters().toString();
        try {

            if (this.parenthasesCount != 0) {
                throw new Exception("Invalid Input");

            }

            //checks if first pos is not ( or a digit
            Character first = problem.charAt(0);
            if (first.equals(')') || first.equals('+') || first.equals('-') || first.equals('*')) {
                throw new Exception("Invalid Input");
            }

            for (Integer i = 1; i < result.getCharacters().length(); i++) {

                //current and prev inputs
                Character index = problem.charAt(i);
                Character prev = problem.charAt(i - 1);



                //checks () and )( cases
                if ((index.equals('(') &&  prev.equals(')'))) {
                    throw new Exception("Invalid Input");
                }

                if(index.equals(')') && prev.equals('(')){
                    throw new Exception("Invalid Input");
                }

                // checks (+ case
                if (prev.equals('(')) {
                    if (index.equals('+') || index.equals('-') || index.equals('*')) {
                        throw new Exception("Invalid Input");
                    }
                }

                //checks )3 case
                if (prev.equals(')')) {
                    if (index.isDigit(i)) {
                        throw new Exception("Invalid Input");
                    }
                }

                //checks ++ case
                if(index.equals('+') || index.equals('-') || index.equals('*')){
                        if (prev.equals('+') || prev.equals('-') || prev.equals('*')){
                            throw new Exception("Invalid Input");
                        }
                }

                else {i++;}
            }
        }
        catch(Exception expt){
            return false;
        }
        return true;
    }

    public static void main(String[] args) {
        Application.launch(args);

    }


}