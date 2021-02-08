package com.example.myapplication;

import org.junit.Assert;
import org.junit.Test;

public class TileUnitTest {

    @Test
    public void TileCreation() {
        Tile t = new Tile.TileBuilder(0,0).build();
        Assert.assertNotNull(t);
        Assert.assertEquals(t.getCandidates().size(),9);
        Tile t1 = new Tile.TileBuilder(0,9).build();
        Assert.assertNull(t1);
        Tile t2 = new Tile.TileBuilder(-1,5).build();
        Assert.assertNull(t2);
        Tile t3 = new Tile.TileBuilder(0,0).value(0).build();
        Assert.assertNotNull(t3);
        Tile t4 = new Tile.TileBuilder(0,0).value(10).build();
        Assert.assertNull(t4);
        Tile t5 = new Tile.TileBuilder(8,8).value(9).build();
        Assert.assertNotNull(t5);
        Assert.assertEquals(t5.getCandidates().size(),0);
    }
}
