import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("echo", 1);
        map.put("exit 0",0);
        while (true){
            System.out.print("$ ");
            //Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] command = input.trim().split(" ");
            if (map.getOrDefault(command[0],100) == 1){
                ///int i = command.length - 1;
                String a = Arrays.asList(command).subList(1, command.length).stream().map(
                        s -> s + " ").reduce("", String::concat).trim();
                System.out.println(a);
            }else if (map.getOrDefault(input.trim(),100) == 0){
                scanner.close();
                break;
            }else{
                System.out.println(input + ": command not found");
            }

        }

    }
}
