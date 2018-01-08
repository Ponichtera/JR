package gameSokoban.model;

import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class LevelLoader {
    private Path levels;

    public LevelLoader(Path levels) {
        this.levels = levels;
    }

    public GameObjects getLevel(int level) {
        Player player;
        Set<Wall> walls = new HashSet<>();
        Set<Home> homes = new HashSet<>();
        Set<Box> boxes = new HashSet<>();

        player = new Player(20, 20);
        walls.add(new Wall(40, 40));
        walls.add(new Wall(60, 60));
        homes.add(new Home(80, 80));
        boxes.add(new Box(100, 100));

        return new GameObjects(player, walls, homes, boxes);
    }
}
