package view;

public class MenuView implements View {
    public void display() {
        System.out.println("====================================");
        System.out.println();

        System.out.println("1. Create New Game");
        System.out.println("2. Load Game");
        System.out.println("3. View Scores");
        System.out.println("4. Exit");

        System.out.println();
        System.out.println("====================================");
    }
}
