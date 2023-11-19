package org.example.blocks;

import java.util.List;

public interface CompositeBlock extends Block {
    List<Block> getBlocks();
}