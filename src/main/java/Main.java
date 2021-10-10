import cashmachine.command.Command;
import cashmachine.Parser;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Enter command. Type end to quit");


        String input = scanner.nextLine();

        while (!"end".equals(input)){
            Command command = Parser.parseInput(input);
            if(command != null && command.execute() ){
                System.out.println("OK");
            }
            else {
                System.out.println("ERROR");
           }
             input = scanner.nextLine();

        }


    }
}
