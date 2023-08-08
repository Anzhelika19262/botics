package org.classes;

public class Main {
    public static void main(String[] args) {
        boolean onGUI = true;
        for (String arg : args) {
            System.out.println("Arg: " + arg);
        }
        if (args.length >= 2)
        {
            Bot tgBot = new Bot(args[0],args[1]);
        }

        System.out.println(String.format("Args length: %d", args.length));
        if (onGUI) {
            //Start window
        }

        System.exit(0);
    }
}


