package org.example.structures;

import org.example.blocks.Block;

class BlockTestImpl implements Block {
    String color;
    String material;

    public BlockTestImpl(String color, String material) {
        this.color = color;
        this.material = material;
    }

    @Override
    public String getColor() {
        return this.color;
    }

    @Override
    public String getMaterial() {
        return this.material;
    }
}