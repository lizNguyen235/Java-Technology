
public class Program {  
    public static void main(String[] args) {
        if(args.length != 3) {
                System.out.println("Invalid Expression");
                return;
            }
            
        try {
            double a = Double.parseDouble(args[0]);
            double b =  Double.parseDouble(args[2]);
            System.out.println(computing(a, b, args[1]));
        } catch (Exception e) {
            System.out.println(e.toString());
        }   
    }
    public static double computing(double a, double b, String operate) throws IllegalArgumentException {
        switch (operate) {
            case "+":
                return a + b;
            case "-":
                return a - b;
            case "x":
                return a * b;
            case ":":
                if (b == 0) {
                    throw new IllegalArgumentException("Invalid Value");
                }
                return a / b;
            case "^":
                return Math.pow(a , b);
            default:
                throw new IllegalArgumentException("Unsuport Operate");
        }
    }
}
