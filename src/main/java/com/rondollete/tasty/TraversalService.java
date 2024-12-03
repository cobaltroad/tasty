package com.rondollete.tasty;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

@Service
public class TraversalService {

    Set<ColorNode> visitedNodes = new HashSet<>();

    public HashMap<Integer, CardinalEnum> findProgram(int[][] grid, ColorNode node) {
        HashMap<Integer, CardinalEnum> program = new HashMap<>();
        for (int i = 1; i <= 10; i++) {
            program.put(i, CardinalEnum.SOUTH);
        }

        while(traverse(grid, node, program) == null) {
            // ....

        }
        return null;
    }

    public ColorNode traverse(int[][] grid, ColorNode node, HashMap<Integer, CardinalEnum> program) {
        int currX = node.getLocX();
        int currY = node.getLocY();

        int currGridColor = grid[currY][currX];

        // test to see if node is on the star
        if (currGridColor == 0) {
            return node;
        } else {
            if (visitedNodes.contains(node)) {
                // program is running in circles
                return null;
            } else if (node.getLocX() < 0 || node.getLocY() < 0 || node.getLocX() > 9 || node.getLocY() > 9) {
                // direction is off the grid
                return null;
            } else {
                visitedNodes.add(node);

                /**
                 *    x, y
                    N(0, -1),
                    E(1, 0),
                    S(0, 1),
                    W(-1, 0);
                */

                CardinalEnum programDirection = program.get(currGridColor);
                ColorNode newNode = switch (programDirection) {
                    case NORTH -> new ColorNode(node.getLocX(), node.getLocY() - 1);
                    case EAST -> new ColorNode(node.getLocX() + 1, node.getLocY());
                    case SOUTH -> new ColorNode(node.getLocX(), node.getLocY() + 1);
                    case WEST -> new ColorNode(node.getLocX() - 1, node.getLocY());
                };

                return traverse(grid, newNode, program);
            }
        }
    }
}
