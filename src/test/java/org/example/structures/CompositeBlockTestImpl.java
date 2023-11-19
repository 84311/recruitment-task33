package org.example.structures;

import org.example.blocks.Block;
import org.example.blocks.CompositeBlock;

import java.util.List;

class CompositeBlockTestImpl implements CompositeBlock {
    String color;
    String material;
    List<Block> blocks;

    public CompositeBlockTestImpl(String color, String material, List<Block> blocks) {
        this.color = color;
        this.material = material;
        this.blocks = blocks;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }

    @Override
    public List<Block> getBlocks() {
        return this.blocks;
    }
}
