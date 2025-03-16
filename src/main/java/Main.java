import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        while (true){
            System.out.print("$ ");
            //Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            if (input.trim().equals("exit 0")){
                scanner.close();
                break;
            }
            System.out.println(input + ": command not found");
        }

    }
}
