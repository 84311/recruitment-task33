package org.example.structures;

import org.example.blocks.Block;
import org.example.blocks.CompositeBlock;
import org.junit.Test;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import static java.util.Collections.emptyList;
import static java.util.Collections.singletonList;
import static org.junit.Assert.*;

public class WallTest {

    @Test
    public void test_instantiation_without_arguments() {
        Wall wall = new Wall();

        assertNotNull(wall);
    }

    @Test
    public void test_instantiation_null_list_of_blocks() {
        assertThrows(IllegalArgumentException.class, () -> new Wall(null));
    }

    @Test
    public void test_instantiation_with_empty_list_of_blocks() {
        List<Block> blocks = new ArrayList<>();

        Wall wall = new Wall(blocks);

        assertNotNull(wall);
    }

    @Test
    public void test_instantiation_with_list_of_blocks() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new BlockTestImpl("red", "wood"));
        blocks.add(new BlockTestImpl("blue", "stone"));

        Wall wall = new Wall(blocks);

        assertNotNull(wall);
    }

    @Test
    public void test_instantiation_with_linked_list_with_blocks() {
        List<Block> blocks = new LinkedList<>();
        blocks.add(new BlockTestImpl("red", "wood"));
        blocks.add(new BlockTestImpl("blue", "stone"));

        Wall wall = new Wall(blocks);

        assertNotNull(wall);
    }

    @Test
    public void test_findBlockByColor_empty_list_of_blocks() {
        Wall wall = new Wall(new ArrayList<>());

        Optional<Block> block = wall.findBlockByColor("red");

        assertFalse(block.isPresent());
    }

    @Test
    public void test_findBlockByMaterial_empty_list_of_blocks() {
        Wall wall = new Wall(new ArrayList<>());

        List<Block> blocks = wall.findBlocksByMaterial("stone");

        assertTrue(blocks.isEmpty());
    }

    @Test
    public void test_findBlockByColor() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new BlockTestImpl("red", "wood"));
        blocks.add(new BlockTestImpl("blue", "stone"));
        Wall wall = new Wall(blocks);

        Optional<Block> block = wall.findBlockByColor("red");

        assertTrue(block.isPresent());
        assertEquals("red", block.get().getColor());
    }

    @Test
    public void test_findBlockByColor_not_present() {
        List<Block> blocks = new ArrayList<>();
        blocks.add(new BlockTestImpl("red", "wood"));
        blocks.add(new BlockTestImpl("blue", "stone"));
        Wall wall = new Wall(blocks);

        Optional<Block> block = wall.findBlockByColor("black");

        assertFalse(block.isPresent());
    }

    @Test
    public void test_findBlocksByMaterial() {
        Block block1 = new BlockTestImpl("red", "wood");
        Block block2 = new BlockTestImpl("blue", "wood");
        Block block3 = new BlockTestImpl("green", "stone");
        Wall wall = new Wall(List.of(block1, block2, block3));

        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");

        assertEquals(2, foundBlocks.size());
        assertTrue(foundBlocks.contains(block1));
        assertTrue(foundBlocks.contains(block2));
    }

    @Test
    public void test_findBlocksByMaterial_not_present() {
        Block block1 = new BlockTestImpl("red", "wood");
        Block block2 = new BlockTestImpl("blue", "wood");
        Block block3 = new BlockTestImpl("green", "stone");
        Wall wall = new Wall(List.of(block1, block2, block3));

        List<Block> foundBlocks = wall.findBlocksByMaterial("plastic");

        assertEquals(emptyList(), foundBlocks);
    }

    @Test
    public void test_handles_single_block_input() {
        Wall wall = new Wall(singletonList(new BlockTestImpl("red", "wood")));

        Optional<Block> foundBlock = wall.findBlockByColor("red");
        assertTrue(foundBlock.isPresent());
        assertEquals("red", foundBlock.get().getColor());

        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");
        assertEquals(1, foundBlocks.size());
        assertEquals("wood", foundBlocks.get(0).getMaterial());

        int count = wall.count();
        assertEquals(1, count);
    }

    @Test
    public void test_handles_composite_block() {
        Block block1 = new BlockTestImpl("red", "wood");
        Block block2 = new BlockTestImpl("blue", "wood");
        CompositeBlock compositeBlock =
                new CompositeBlockTestImpl(null, null, List.of(block1, block2));
        Wall wall = new Wall(singletonList(compositeBlock));

        Optional<Block> foundBlock = wall.findBlockByColor("blue");
        assertTrue(foundBlock.isPresent());
        assertEquals("blue", foundBlock.get().getColor());

        List<Block> foundBlocks = wall.findBlocksByMaterial("wood");
        assertEquals(2, foundBlocks.size());
        assertEquals("wood", foundBlocks.get(0).getMaterial());
        assertEquals("wood", foundBlocks.get(1).getMaterial());

        int count = wall.count();
        assertEquals(2, count);
    }

    @Test
    public void test_handles_multiple_levels_of_composite_blocks() {
        Block block1 = new BlockTestImpl("red", "wood");
        Block block2 = new BlockTestImpl("green", "plastic");
        Block block3 = new BlockTestImpl("blue", "metal");
        CompositeBlock innerCompositeBlock = new CompositeBlockTestImpl(null, null, List.of(block1, block2));
        CompositeBlock outerCompositeBlock = new CompositeBlockTestImpl(null, null, List.of(innerCompositeBlock, block3));
        Wall wall = new Wall(List.of(outerCompositeBlock, outerCompositeBlock));

        Optional<Block> foundBlock = wall.findBlockByColor("blue");
        assertTrue(foundBlock.isPresent());
        assertEquals("blue", foundBlock.get().getColor());

        List<Block> foundBlocks = wall.findBlocksByMaterial("plastic");
        assertEquals(2, foundBlocks.size());
        assertEquals("plastic", foundBlocks.get(0).getMaterial());
        assertEquals("plastic", foundBlocks.get(1).getMaterial());

        int blockCount = wall.count();
        assertEquals(6, blockCount);
    }

    @Test
    public void test_handles_multiple_levels_of_composite_blocks_with_null_and_empty_list() {
        Block block1 = new BlockTestImpl("red", "wood");
        Block block2 = new BlockTestImpl("green", "plastic");
        Block block3 = new BlockTestImpl("blue", "metal");
        CompositeBlock innerCompositeBlock = new CompositeBlockTestImpl(null, null, List.of(block1, block2));
        CompositeBlock innerCompositeBlockNullBlockList = new CompositeBlockTestImpl(null, null, null);
        CompositeBlock innerCompositeBlockEmptyBlockList = new CompositeBlockTestImpl(null, null, emptyList());
        CompositeBlock outerCompositeBlock =
                new CompositeBlockTestImpl(null, null, List.of(innerCompositeBlock, innerCompositeBlockNullBlockList, innerCompositeBlockEmptyBlockList, block3));
        Wall wall = new Wall(List.of(outerCompositeBlock, outerCompositeBlock));

        Optional<Block> foundBlock = wall.findBlockByColor("blue");
        assertTrue(foundBlock.isPresent());
        assertEquals("blue", foundBlock.get().getColor());

        List<Block> foundBlocks = wall.findBlocksByMaterial("plastic");
        assertEquals(2, foundBlocks.size());
        assertEquals("plastic", foundBlocks.get(0).getMaterial());
        assertEquals("plastic", foundBlocks.get(1).getMaterial());

        int blockCount = wall.count();
        assertEquals(6, blockCount);
    }

    @Test
    public void test_handles_blocks_with_nulls() {
        Block block1 = new BlockTestImpl(null, "wood");
        Block block2 = new BlockTestImpl("red", null);
        Block block3 = new BlockTestImpl(null, null);
        Wall wall = new Wall(List.of(block1, block2, block3));

        Optional<Block> foundBlock = wall.findBlockByColor(null);
        assertTrue(foundBlock.isPresent());
        assertNull(foundBlock.get().getColor());
        assertEquals("wood", foundBlock.get().getMaterial());

        List<Block> foundBlocks = wall.findBlocksByMaterial(null);
        assertEquals(2, foundBlocks.size());
        assertEquals("red", foundBlocks.get(0).getColor());
        assertNull(foundBlocks.get(0).getMaterial());

        int count = wall.count();
        assertEquals(3, count);
    }
}