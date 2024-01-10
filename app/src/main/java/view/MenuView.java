package view;

import model.MenuOptions;

public class MenuView implements View {
    public void display() {
        System.out.println("====================================");
        System.out.println();

        String[] options = MenuOptions.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }

        System.out.println();
        System.out.println("====================================");
    }
}
