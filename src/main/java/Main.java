import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("echo", 1);
        map.put("exit 0",0);
        map.put("type",2);
        map.put("exit",3);
        String path = System.getenv("PATH");
        HashMap<String, Integer> pathmap = new HashMap<>();
        String[] paths = path.trim().split(":");

//        for (String dir:paths) {
//            List<String> a = Files.walk(Paths.get(dir)).filter(Files::isDirectory)
//                    .map(s -> s.getParent().getFileName().toString()).toList();
//            HashSet<String> b = new HashSet<>(a);
//            b.contains(comm)
//        }
        
        File curDir = new File(".");
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
                for (String dir:paths) {
                    List<String> a = Files.walk(Paths.get(dir)).filter(Files::isDirectory)
                            .map(Path::toString).toList();
                    System.out.println(a);
                    HashSet<String> b = new HashSet<>(a);
                    String ex = dir + command[1];
                    if (b.contains(ex)){
                        System.out.println(command[1] + " is " + ex);
                        break;
                    }else{
                        System.out.println(command[1] +": not found");
                    }
                }
                if (!path.isBlank()){
                    if (map.containsKey(command[1])){
                        System.out.println(command[1] + " is a shell builtin" );
                    }else{
                        System.out.println(command[1] +": not found");
                    }
                }

            }else{
                System.out.println(input + ": command not found");
            }

        }

    }
}
