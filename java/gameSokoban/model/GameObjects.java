package gameSokoban.model;

import java.util.HashSet;
import java.util.Set;

public class GameObjects {
    private Player player;
    private Set<Wall> walls;
    private Set<Home> homes;
    private Set<Box>  boxes;

    public GameObjects(Player player, Set<Wall> walls, Set<Home> homes, Set<Box> boxes) {
        this.player = player;
        this.walls = walls;
        this.homes = homes;
        this.boxes = boxes;
    }

    public Player getPlayer() {
        return player;
    }

    public Set<Wall> getWalls() {
        return walls;
    }

    public Set<Home> getHomes() {
        return homes;
    }

    public Set<Box> getBoxes() {
        return boxes;
    }

    public Set<AbstractObject> getGameObjects() {
        Set<AbstractObject> result = new HashSet<>(walls);
        result.addAll(homes);
        result.addAll(boxes);
        result.add(player);
        return result;
    }
}
