package view;

import model.GameData;

public class MenuView implements View {
    GameData gameData;

    public MenuView(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void display() {
    }
}
