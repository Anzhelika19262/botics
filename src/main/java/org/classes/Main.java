package org.classes;

public class Main {
    public static void main(String[] args) {
        boolean onGUI = true;
        for (String arg : args) {
            System.out.println("Arg: " + arg);
            onGUI = !arg.toLowerCase().equals("nogui");
        }
        System.out.println(String.format("Args length: %d", args.length));
        if (onGUI) {
            //Start window
        }

        System.exit(0);
    }
}


