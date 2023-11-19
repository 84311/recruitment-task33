package org.example.structures;

import org.example.blocks.Block;
import org.example.blocks.CompositeBlock;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Stream;

public class Wall implements Structure {
    private final List<Block> blocks;

    public Wall() {
        blocks = new ArrayList<>();
    }

    public Wall(List<Block> blocks) {
        this.blocks = blocks;
        validateBlocksList();
    }

    @Override
    public Optional<Block> findBlockByColor(String color) {
        return getAllBlocksFlatStream()
                .filter(b -> Objects.equals(b.getColor(), color))
                .findAny();
    }

    @Override
    public List<Block> findBlocksByMaterial(String material) {
        return getAllBlocksFlatStream()
                .filter(b -> Objects.equals(b.getMaterial(), material))
                .toList();
    }

    @Override
    public int count() {
        return (int) getAllBlocksFlatStream().count();
    }

    private Stream<Block> getAllBlocksFlatStream() {
        return blocks.stream()
                .flatMap(this::flattenBlock);
    }

    private Stream<Block> flattenBlock(Block block) {
        if (block instanceof CompositeBlock compositeBlock && compositeBlock.getBlocks() != null) {
            return compositeBlock.getBlocks().stream()
                    .flatMap(this::flattenBlock);
        } else if (block instanceof CompositeBlock) {
            return Stream.empty();
        } else {
            return Stream.of(block);
        }
    }

    private void validateBlocksList() {
        if (blocks == null) {
            throw new IllegalArgumentException("Blocks list cannot be null");
        }
    }
}