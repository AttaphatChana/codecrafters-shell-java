import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;


public class Main {
    public static void find_command(String command, String[] paths) {
        String message = "";
        for (String dir : paths) {
            String ex = dir + "/" + command;
            if (Files.exists(Path.of(ex))) {
                message = command + " is " + ex;
                break;
            }
        }
        if (message.isBlank()) {
            System.out.println(command + ": not found");
        } else {
            System.out.println(message);
        }
    }
    public static boolean exe_command(String command, String[] paths, String cmd) throws IOException, InterruptedException {
        for (String dir : paths) {
            String ex = dir + "/" + command;
            if (Files.exists(Path.of(ex))) {
                //ArrayList<String> ex1 = (ArrayList<String>) Arrays.stream(cmd).toList();
                String ex1 = ex + " " + cmd;
                String[] f_cmd = ex1.split("\\s+");
//                System.out.println("exe");
//                System.out.println(Arrays.toString(f_cmd));
                Process process1 =Runtime.getRuntime().exec(f_cmd);
                process1.waitFor();
                //System.out.println("exe!");

               return true;
            }
        }
        return false;
    }
    public static void main(String[] args) throws Exception {
        // Uncomment this block to pass the first stage
        Scanner scanner = new Scanner(System.in);
        HashMap<String, Integer> map = new HashMap<>();
        map.put("echo", 1);
        map.put("exit 0", 0);
        map.put("type", 2);
        map.put("exit", 3);
        String path = System.getenv("PATH");
        //HashMap<String, Integer> pathmap = new HashMap<>();
        String[] paths = path.trim().split(":");

//        for (String dir:paths) {
//            List<String> a = Files.walk(Paths.get(dir)).filter(Files::isDirectory)
//                    .map(s -> s.getParent().getFileName().toString()).toList();
//            HashSet<String> b = new HashSet<>(a);
//            b.contains(comm)
//        }

        //File curDir = new File(".");
        while (true) {
            System.out.print("$ ");
            //Scanner scanner = new Scanner(System.in);
            String input = scanner.nextLine();
            String[] command = input.trim().split("\\s+");
            int result = map.getOrDefault(command[0], 100);
            if (map.getOrDefault(input.trim(), 100) == 0) {
                scanner.close();
                break;
            }
            if (input.isBlank()){
                //System.out.println("qwwjj");
                continue;
            }
            switch (result) {
                case 0:
                    scanner.close();
                    break;
                case 1:
                    String a = Arrays.asList(command).subList(1, command.length).stream().map(
                            s -> s + " ").reduce("", String::concat).trim();
                    System.out.println(a);
                    break;
                case 2:
                    if (map.containsKey(command[1])) {
                        System.out.println(command[1] + " is a shell builtin");
                    } else {
                        find_command(command[1],paths);
                    }
                    break;
                case 100:
                    a = Arrays.asList(command).subList(1, command.length).stream().map(
                            s -> s + " ").reduce("", String::concat).trim();
                    try{
                        int i = 0;
                        if(exe_command(command[0],paths,a)){
                            for (String name : command){
                                System.out.println("Arg #" + i + ": " + name);
                                i += 1;

                            }
                        }else{
                            System.out.println(command[0] + ": command not found");
                            break;
                        }
                    }catch (IOException e){
                        System.out.println(command[0] + ": command not found");
                    }

                    break;
            }
//            if (map.getOrDefault(command[0], 100) == 1) {
//                ///int i = command.length - 1;
//                String a = Arrays.asList(command).subList(1, command.length).stream().map(
//                        s -> s + " ").reduce("", String::concat).trim();
//                System.out.println(a);
//            } else if (map.getOrDefault(input.trim(), 100) == 0) {
//                scanner.close();
//                break;
//            } else if (map.getOrDefault(command[0], 100) == 2) {
//                //String message = "";
//                if (map.containsKey(command[1])) {
//                    System.out.println(command[1] + " is a shell builtin");
//                } else {
//                    find_command(command[1],paths);
//                }
//            }
//            else{
//                System.out.println(input + ": command not found");
//            }

        }

    }
}
