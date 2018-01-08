package gameOf2048;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class Model {
    private final int FIELD_WIDTH = 4;
    private Tile[][] gameTiles;
    private int maxTile = 0;
    private int score = 0;
    private Stack<Integer> previousScores;
    private Stack<Tile[][]> previousStates;
    private boolean isSaveNeeded = true;

    public Model() {
        resetTiles();
        score = 0;
        maxTile = 2;
        previousStates = new Stack<>();
        previousScores = new Stack<>();
    }

    public int getMaxTile() {
        return maxTile;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public void saveGame(Tile[][] gameTiles) {
        Tile[][] tmp = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for(int row = 0; row < FIELD_WIDTH; row++)
            for(int column = 0; column < FIELD_WIDTH; column++)
                tmp[row][column] = new Tile(gameTiles[row][column].getValue());

        previousStates.push(tmp);
        previousScores.push(score);
        isSaveNeeded = false;
    }

    public void rollback() {
        if (previousScores.isEmpty() || previousStates.isEmpty()) return;

        score = previousScores.pop();
        gameTiles = previousStates.pop();
    }

    public Tile[][] getGameTiles() {
        return gameTiles;
    }

    public boolean canMove() {
        if (getEmptyTiles().size() != 0) return true;

        for(int row = 0; row < FIELD_WIDTH; row++)
            for(int column = 1; column < FIELD_WIDTH; column++)
                if(gameTiles[row][column].getValue() == gameTiles[row][column-1].getValue())
                    return true;

        for(int row = 1; row < FIELD_WIDTH; row++)
            for(int column = 0; column < FIELD_WIDTH; column++)
                if(gameTiles[row][column].getValue() == gameTiles[row-1][column].getValue())
                    return true;

        return false;
    }

    public void addTile() {
        //90% of new tiles has to be "2"
        List<Tile> tiles = getEmptyTiles();
        if (tiles.size() != 0)
            tiles.get((int) (tiles.size() * Math.random())).setValue(Math.random() > 0.9 ? 4 : 2);
    }

    public void resetTiles() {
        gameTiles = new Tile[FIELD_WIDTH][FIELD_WIDTH];
        for (int row = 0; row < FIELD_WIDTH; row++)
            for (int column = 0; column < FIELD_WIDTH; column++)
                gameTiles[row][column] = new Tile();
        addTile();
        addTile();
    }

    public void rotateCW(){
        final int M = FIELD_WIDTH;
        final int N = FIELD_WIDTH;
        Tile[][] result = new Tile[N][M];

        for (int row = 0; row < M; row++) {
            for (int column = 0; column < N; column++) {
                result[column][M-1-row] = gameTiles[row][column];
            }
        }
        gameTiles = result;
    }

    public void left() {
        if (isSaveNeeded) saveGame(gameTiles);

        boolean isChanged = false;

        for (int i = 0; i < FIELD_WIDTH; i++)
            if (compressTiles(gameTiles[i]) | margeTiles(gameTiles[i]))
                isChanged = true;

        /* level easy
        for (int i = 0; i < FIELD_WIDTH; i++)
            if (compressTiles(gameTiles[i]) | margeTiles(gameTiles[i]))
                isChanged = true;
        */

        if(isChanged) {
            addTile();
            isSaveNeeded = true;
        }
    }

    public void right() {
        saveGame(gameTiles);
        rotateCW();
        rotateCW();
        left();
        rotateCW();
        rotateCW();
    }

    public void down() {
        saveGame(gameTiles);
        rotateCW();
        left();
        rotateCW();
        rotateCW();
        rotateCW();
    }

    public void up() {
        saveGame(gameTiles);
        rotateCW();
        rotateCW();
        rotateCW();
        left();
        rotateCW();
    }

    private List<Tile> getEmptyTiles() {
        List<Tile> emptyTilesList = new ArrayList<>();
        for (int row = 0; row < FIELD_WIDTH; row++)
            for (int column = 0; column < FIELD_WIDTH; column++)
                if (gameTiles[row][column].getValue() == 0) emptyTilesList.add(gameTiles[row][column]);

        return  emptyTilesList;
    }

    private boolean compressTiles(Tile[] tiles){
        boolean isChanged= false;
        Tile temp;
        for (int row = 0; row < FIELD_WIDTH-1; row++) {
            for (int column = 0; column < FIELD_WIDTH-1; column++) {
                if (tiles[column].getValue() == 0 && tiles[column + 1].getValue() != 0) {
                    temp = tiles[column];
                    tiles[column] = tiles[column + 1];
                    tiles[column + 1] = temp;
                    isChanged = true;
                }
            }
        }
        return isChanged;
    }

    private boolean margeTiles(Tile[] tiles) {
        boolean isChanged = false;

        for (int i = 0; i < FIELD_WIDTH-1; i++){
            int iValue = tiles[i].getValue();
            if ( iValue != 0 && iValue == tiles[i+1].getValue()) {
                tiles[i].setValue( iValue * 2);
                tiles[i+1].setValue(0);
                isChanged = true;
                score +=  iValue * 2;
                if (tiles[i].getValue() > maxTile) maxTile = tiles[i].getValue();
            }
        }
        return isChanged;
    }


}
