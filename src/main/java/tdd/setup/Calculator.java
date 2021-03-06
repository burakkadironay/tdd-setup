package tdd.setup;

// behaviour inspired by https://www.online-calculator.com/
public class Calculator {

    private String screen = "0";

    private double latestValue;

    private String latestOperation = "";

    public String readScreen() {
        return screen;
    }

    public void pressDigitKey(int digit) {
        if(digit > 9 || digit < 0) throw new IllegalArgumentException();

        //Bug-Fix No 2
        if(latestOperation.isEmpty() || !(latestValue == 0.0)) {
            screen = screen + digit;
        } else {
            latestValue = Double.parseDouble(screen);
            screen = Integer.toString(digit);
        }
    }

    public void pressClearKey() {
        screen = "0";
        latestOperation = "";
        latestValue = 0.0;
    }

    public void pressOperationKey(String operation)  {
        latestOperation = operation;
    }

    public void pressDotKey() {
        if(!screen.endsWith(".")) screen = screen + ".";
    }

    //Bug-Fix No 3
    public void pressNegative() {
        if (screen.startsWith("-")) {
            screen = screen.substring(1);
        } else{
            screen = "-" + screen.substring(1);
        }
    }

    public void pressEquals() {
        var result = switch(latestOperation) {
            case "" -> Double.parseDouble(screen); //Bug-Fix No 1
            case "+" -> latestValue + Double.parseDouble(screen);
            case "-" -> latestValue - Double.parseDouble(screen);
            case "x" -> latestValue * Double.parseDouble(screen);
            case "/" -> latestValue / Double.parseDouble(screen);
            default -> throw new IllegalArgumentException();
        };
        screen = Double.toString(result);
        if(screen.endsWith(".0")) screen = screen.substring(0,screen.length()-2);
    }
}
