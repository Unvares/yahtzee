package view;

import model.GameData;

public class MenuView extends View {
    GameData gameData;

    public MenuView(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void display() {
        clearScreen();
    }
}
