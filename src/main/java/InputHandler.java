import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class InputHandler {
    public static String readAndPrintFile(String args[]) throws FileNotFoundException {
        File file = new File(args[0]);
        Scanner sc = new Scanner(file);
        String content = null;
        while (sc.hasNextLine()){
            content = sc.nextLine();
        }
        System.out.println("Input file:");
        System.out.println(content);
        System.out.println("-------");
        System.out.println();
        return content;
    }


    public static String readCommandFromUser(boolean includeMessage) throws FileNotFoundException {
        Scanner sc= new Scanner(System.in);    //System.in is a standard input stream
        if (includeMessage) {
            System.out.println("Menu:\n" +
                    "a. Print current state\n" +
                    "b. Change a value");
        }
        return sc.nextLine();
    }

    public static String updateCells(String cells, String update) {
        String[] parts = update.replace("#", "").split(" ");
        String[] cellsList = cells.replace("#", "").split(",");
        cellsList[Integer.parseInt(parts[2])] = parts[3];
        System.out.println("cell #" + parts[2] + " changed to " + parts[3]);
        System.out.println();
        return String.join(",", cellsList);
    }

}
