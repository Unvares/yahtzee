package view;

import java.util.List;

import model.MenuOption;

public class MenuView {
    public void display(List<MenuOption> options) {
        for (int i = 0; i < options.size(); i++) {
            System.out.println((i + 1) + ". " + options.get(i).getDescription());
        }
    }
}
