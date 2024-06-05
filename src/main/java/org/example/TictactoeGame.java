package org.example;

import com.javarush.engine.cell.Color;
import com.javarush.engine.cell.Game;
import com.javarush.engine.cell.Key;

public class TictactoeGame extends Game {

    private int[][] model = new int[3][3];
    private int currentPlayer = 1;
    private boolean isGameStopped = false;


    public void initialize() {
        setScreenSize(3, 3);
        startGame();
    }

    public void startGame() {
        for (int y=0; y<model.length; y++) {
            for (int x=0; x<model[y].length; x++){
                model[y][x] = 0;
            }
        }
        currentPlayer = 1;
        isGameStopped = false;
        updateView();
    }

    public void updateCellView(int x, int y, int value) {
        switch (value) {
            case 0: setCellValue(x,y, " "); break;
            case 1: setCellValueEx(x,y, Color.NONE, "X", Color.BLUE); break;
            case 2: setCellValueEx(x,y, Color.NONE, "O", Color.RED); break;
        }
    }

    public void updateView() {
        for (int y=0; y<model.length; y++) {
            for (int x=0; x<model[y].length; x++){
                updateCellView(x,y,model[y][x]);
            }
        }
    }

    public void setSignAndCheck(int x, int y) {
        model[y][x] = currentPlayer;
        updateView();
        if (checkWin(x,y,currentPlayer)) {
            isGameStopped = true;
            showMessageDialog(Color.WHITE, currentPlayer + " player won", Color.BLACK, 28);
        }
        else if (!hasEmptyCell()) {
            isGameStopped = true;
            showMessageDialog(Color.WHITE, "A DRAW", Color.BLACK, 28);
        }
        currentPlayer = 3 - currentPlayer;
    }


    public boolean checkWin(int x, int y, int n) {

        for (int i = 0; i < 3; i++) {
            int[] horizontal = model[i];
            if (horizontal[0] == n && horizontal[1] == n && horizontal[2] == n
            || model[0][i] == n && model[1][i] == n && model[2][i] == n) {
                return true;
            }
        }

        return model[0][0] == n && model[1][1] == n && model[2][2] == n
                || model[0][2] == n && model[1][1] == n && model[2][0] == n;
    }

    public boolean hasEmptyCell() {
        for (int y=0; y<model.length; y++) {
            for (int x=0; x<model[y].length; x++){
                if (model[y][x] == 0) {
                    return true;
                }
            }
        }
        return false;
    }

    @Override
    public void onMouseLeftClick(int x, int y) {
        if (!isGameStopped && model[y][x] == 0) {
            setSignAndCheck(x,y);
            computerTurn();
        }
    }

    @Override
    public void onKeyPress(Key key) {
        switch (key) {
            case SPACE -> {
                if (isGameStopped) {
                    startGame();
                }
            }
        }
    }

    public void computerTurn() {
        if (model[1][1] == 0) {
            setSignAndCheck(1, 1);
        }
        else {
            for (int y=0; y<model.length; y++) {
                for (int x=0; x<model[y].length; x++){
                    if (model[y][x] == 0) {
                        setSignAndCheck(x,y);
                        return;
                    }
                }
            }
        }
    }
}
