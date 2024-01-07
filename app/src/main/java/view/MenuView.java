package view;

import model.MenuOptions;

public class MenuView implements View {
    public void display() {
        String[] options = MenuOptions.getOptions();
        for (int i = 0; i < options.length; i++) {
            System.out.println((i + 1) + ". " + options[i]);
        }
    }
}
