package pea.menu;

import pea.algorithms.NearestNeighbor;
import pea.algorithms.SimulatedAnnealing;
import pea.graph.Graph;

import java.io.IOException;
import java.util.Scanner;


public class Menu {

    private final Graph instance = Graph.getInstance();
    public static Integer timeInSeconds;
    public static double coolingParameter;
    public Menu() throws IOException {
        mainWindow();
    }

    public void mainWindow() throws IOException {

        switch (choice()) {
            case 1: {
                System.out.print("Enter file name: ");
                Scanner scan = new Scanner(System.in);
                String path = scan.nextLine();
                String filePath = "/Users/kamilbonkowski/Downloads/instancjeAll/"+path;
                instance.setGraph(Graph.getInstance().fromFile(filePath));
                NearestNeighbor.run(instance.getGraph());
                mainWindow();
                break;
            }
            case 2: {
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter time [seconds] :");
                timeInSeconds = scan.nextInt();
                mainWindow();
                break;
            }
            case 3:{
                Scanner scan = new Scanner(System.in);
                System.out.println("Enter cooling parameter :");
                coolingParameter = scan.nextDouble();
                mainWindow();
                break;
            }
            case 4:{
                SimulatedAnnealing sa = new SimulatedAnnealing();
                sa.run();
                mainWindow();
                break;
            }
            case 5:{

            }

            case 0: {
                System.exit(0);
                break;
            }
        }
    }

    public int choiceMessage(String content, int choiceLimit) {
        int choice;
        System.out.println(content);

        Scanner scanner = new Scanner(System.in);

        do {
            choice  = scanner.nextInt();
        }while(choice < 0 || choice > choiceLimit);

        return choice;
    }

    @Override
    public String toString() {
        String menu;
        menu =  "1. Read graph from file \n" +
                "2. Set run time \n" +
                "3. Set cooling parameter [a]\n" +
                "4. Run Simulated Annealing \n"+
                "5. Show graph \n"+
                "0. Exit";
        return menu;
    }

    private int choice() {
        return choiceMessage(toString(), 4);
    }
}


