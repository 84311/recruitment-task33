package org.example.structures;

import org.example.blocks.Block;

import java.util.List;
import java.util.Optional;

interface Structure {
    Optional<Block> findBlockByColor(String color);

    List<Block> findBlocksByMaterial(String material);

    int count();
}