package com.rondollete.tasty.service;

import com.rondollete.tasty.CardinalEnum;
import com.rondollete.tasty.ColorNode;
import com.rondollete.tasty.TraversalService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.assertNotNull;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = TraversalService.class)
public class TraversalServiceTest {

    public static final int[][] SIMPLE_GRID = new int[][]{
            {1, 1, 2, 2, 2, 2, 3, 3, 3, 0},
            {1, 1, 2, 4, 4, 4, 3, 3, 3, 3},
            {1, 5, 5, 4, 6, 6, 6, 3, 3, 3},
            {1, 5, 5, 4, 6, 7, 7, 3, 3, 3},
            {8, 8, 5, 4, 6, 7, 7, 9, 9, 9},
            {8, 8, 5, 4, 6, 7, 7, 9, 10, 10},
            {8, 8, 5, 4, 6, 7, 7, 9, 10, 10},
            {8, 8, 5, 4, 6, 7, 7, 9, 10, 10},
            {8, 8, 5, 4, 6, 7, 7, 9, 10, 10},
            {8, 8, 5, 4, 6, 7, 7, 9, 10, 10}
    };

    @Autowired
    TraversalService service;

    @Test
    public void looksUpColorInMap() {
        // ColorNode
        //   int locX
        //   int locY
        //   int color
        ColorNode initialNode = new ColorNode(0, 0);

        HashMap<Integer, CardinalEnum> program = new HashMap<>();
        // given the simple grid, the trivial solution assigns EAST to 1, 2 and 3
        program.put(1, CardinalEnum.SOUTH);
        program.put(2, CardinalEnum.EAST);
        program.put(3, CardinalEnum.EAST);
        program.put(4, CardinalEnum.WEST); // doesn't matter
        program.put(8, CardinalEnum.SOUTH);

        // { 1 => N, 2 => S, 3 => E, 4 => W}

        ColorNode landingNode = service.traverse(SIMPLE_GRID, initialNode, program);
        assertNotNull(landingNode);
    }

    public void findProgram() {
        ColorNode initialNode = new ColorNode(0, 0);
        assertNotNull(service.findProgram(SIMPLE_GRID, initialNode));
    }
}
