import java.io.IOException;
import java.nio.charset.Charset;
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
    public static String execCmd(String[] cmd) throws java.io.IOException {
        try {
            Process proc = Runtime.getRuntime().exec(cmd);
            java.io.InputStream is = proc.getInputStream();
            java.util.Scanner s = new java.util.Scanner(is);
            StringBuilder val = new StringBuilder();

            while (s.hasNextLine()) {
                val.append(s.nextLine());
                val.append("\n");
                val.append(s.nextLine());
            }
            return val.toString();
        } catch (IOException e){
            System.out.println(e);
        return e.toString();
    }
//        java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(cmd).getInputStream()).useDelimiter("\\A");
//        return s.hasNext() ? s.next() : "";
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
                //Process p =Runtime.getRuntime().exec(f_cmd);
                System.out.println(execCmd(f_cmd));

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
                                //System.out.println(Arrays.toString(command));
                                if (i == 0){
                                    String[] c = name.split("/");
                                    System.out.println("woahh" + Arrays.toString(c));
//                                    System.out.println("Arg #" + i + ": " + c[c.length -1]);
//                                    System.out.println();


                                }else{
                                    //System.out.println("Arg #" + i + ": " + name);
                                }
                                i += 1;

                            }
                        }else{
                            System.out.println(command[0] + ": command not found");
                            break;
                        }
                    }catch (IOException e){
                        System.out.println(command[0] + ": command not found");
                        break;
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
