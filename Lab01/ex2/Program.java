import vn.edu.tdtu.*;
public class Program {
  public static void main(String[] args) {
    int[] a = {2,3,5,2,0,0,4};
    int[] b = {2,2,1,2,2,0,0,4};
    ArrayOutput.print(a);
    ArrayOutput.print(b);
    int[] c = ArrayHandler.merge(a, b);
    ArrayOutput.print(c);
    ArrayHandler.sort(c);
    ArrayOutput.print(c);
    ArrayOutput.write(c, "output.txt");
  }  
}
