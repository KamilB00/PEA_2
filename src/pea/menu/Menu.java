package pea.menu;

import pea.algorithms.NearestNeighbor;
import pea.graph.Graph;

import java.io.IOException;
import java.util.Scanner;


public class Menu {

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
                Graph.getInstance().setGraph(Graph.getInstance().fromFile(filePath));
                NearestNeighbor nearestNeighbor = new NearestNeighbor();
                nearestNeighbor.run(Graph.getInstance().getGraph());
                System.out.println(nearestNeighbor.getPath());
                System.out.println(nearestNeighbor.getCost());
                mainWindow();
                break;
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
                "0. Exit";
        return menu;
    }

    private int choice() {
        return choiceMessage(toString(), 4);
    }
}


