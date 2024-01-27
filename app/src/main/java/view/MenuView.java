package view;

import model.GameData;

public class MenuView implements View {
    GameData gameData;

    public MenuView(GameData gameData) {
        this.gameData = gameData;
    }

    @Override
    public void display() {
        System.out.println("====================================");
        System.out.println();

        System.out.println("1. Create New Game");
        if (gameData.hasSavedGame()) {
            System.out.println("2. Load Game");
        }
        System.out.println("3. View Score Board");
        System.out.println("4. Exit");

        System.out.println();
        System.out.println("====================================");
    }
}
