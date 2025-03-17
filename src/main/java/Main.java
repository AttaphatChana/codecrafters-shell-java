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
        map.put("type",2);
        map.put("exit",3);
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
            }else if (map.getOrDefault(command[0],100) == 2) {
                if (map.containsKey(command[1])){
                    System.out.println(command[1] + " is a shell builtin" );
                }else{
                    System.out.println(command[1] +": not found");
                }
            }else{
                System.out.println(input + ": command not found");
            }

        }

    }
}
