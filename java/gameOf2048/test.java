package gameOf2048;

import org.junit.Assert;
import org.junit.Test;

public class test {

    @Test
    public void model_compressTilesTest() {
        Tile t0 = new Tile(0);
        Tile t2 = new Tile(2);
        Tile t4 = new Tile(4);


        Tile[] tiles = {t0, t2, t0, t4};
        Tile[] expectation = {t2, t4, t0, t0 };

        Model model = new Model();
       // model.compressTiles(tiles);
        Assert.assertArrayEquals(expectation, tiles);

    }
}
