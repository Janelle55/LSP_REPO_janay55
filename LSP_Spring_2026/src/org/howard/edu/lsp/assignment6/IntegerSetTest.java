package org.howard.edu.lsp.assignment6;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

/**
 * JUnit 5 test cases for IntegerSet.
 */
class IntegerSetTest {

    /**
     * Tests clear() for a normal non-empty set.
     */
    @Test
    void testClearNormal() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals("[]", set.toString());
    }

    /**
     * Tests clear() for an already empty set.
     */
    @Test
    void testClearEdgeEmptySet() {
        IntegerSet set = new IntegerSet();
        set.clear();
        assertTrue(set.isEmpty());
        assertEquals(0, set.length());
    }

    /**
     * Tests length() for a normal non-empty set.
     */
    @Test
    void testLengthNormal() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(2);
        assertEquals(2, set.length());
    }

    /**
     * Tests length() for an empty set.
     */
    @Test
    void testLengthEdgeEmptySet() {
        IntegerSet set = new IntegerSet();
        assertEquals(0, set.length());
    }

    /**
     * Tests equals() for same elements in different order.
     */
    @Test
    void testEqualsNormalDifferentOrder() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(3);
        set2.add(2);
        set2.add(1);

        assertTrue(set1.equals(set2));
    }

    /**
     * Tests equals() for mismatched sets.
     */
    @Test
    void testEqualsEdgeMismatch() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(1);
        set2.add(3);

        assertFalse(set1.equals(set2));
    }

    /**
     * Tests contains() when the value is present.
     */
    @Test
    void testContainsNormal() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertTrue(set.contains(5));
    }

    /**
     * Tests contains() when the value is not present.
     */
    @Test
    void testContainsEdgeAbsent() {
        IntegerSet set = new IntegerSet();
        set.add(5);
        assertFalse(set.contains(9));
    }

    /**
     * Tests largest() for a normal set.
     */
    @Test
    void testLargestNormal() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        set.add(9);
        set.add(3);
        assertEquals(9, set.largest());
    }

    /**
     * Tests largest() for a single-element set.
     */
    @Test
    void testLargestEdgeSingleElement() {
        IntegerSet set = new IntegerSet();
        set.add(7);
        assertEquals(7, set.largest());
    }

    /**
     * Tests largest() exception for an empty set.
     */
    @Test
    void testLargestEdgeEmptyException() {
        IntegerSet set = new IntegerSet();
        assertThrows(RuntimeException.class, set::largest);
    }

    /**
     * Tests smallest() for a normal set.
     */
    @Test
    void testSmallestNormal() {
        IntegerSet set = new IntegerSet();
        set.add(4);
        set.add(2);
        set.add(8);
        assertEquals(2, set.smallest());
    }

    /**
     * Tests smallest() for a single-element set.
     */
    @Test
    void testSmallestEdgeSingleElement() {
        IntegerSet set = new IntegerSet();
        set.add(11);
        assertEquals(11, set.smallest());
    }

    /**
     * Tests smallest() exception for an empty set.
     */
    @Test
    void testSmallestEdgeEmptyException() {
        IntegerSet set = new IntegerSet();
        assertThrows(RuntimeException.class, set::smallest);
    }

    /**
     * Tests add() for a normal new value.
     */
    @Test
    void testAddNormal() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        assertTrue(set.contains(10));
        assertEquals(1, set.length());
    }

    /**
     * Tests add() with a duplicate value.
     */
    @Test
    void testAddEdgeDuplicate() {
        IntegerSet set = new IntegerSet();
        set.add(10);
        set.add(10);
        assertEquals(1, set.length());
    }

    /**
     * Tests remove() for a value that is present.
     */
    @Test
    void testRemoveNormal() {
        IntegerSet set = new IntegerSet();
        set.add(2);
        set.add(3);
        set.remove(2);
        assertFalse(set.contains(2));
        assertEquals("[3]", set.toString());
    }

    /**
     * Tests remove() for a value that is not present.
     */
    @Test
    void testRemoveEdgeMissingValue() {
        IntegerSet set = new IntegerSet();
        set.add(2);
        set.remove(9);
        assertEquals("[2]", set.toString());
    }

    /**
     * Tests union() for normal overlapping sets.
     */
    @Test
    void testUnionNormal() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(2);
        set2.add(3);

        assertEquals("[1, 2, 3]", set1.union(set2).toString());
        assertEquals("[1, 2]", set1.toString());
        assertEquals("[2, 3]", set2.toString());
    }

    /**
     * Tests union() with an empty set.
     */
    @Test
    void testUnionEdgeWithEmptySet() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        assertEquals("[1, 2]", set1.union(set2).toString());
        assertEquals("[1, 2]", set1.toString());
    }

    /**
     * Tests intersect() for normal overlapping sets.
     */
    @Test
    void testIntersectNormal() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);

        assertEquals("[2, 3]", set1.intersect(set2).toString());
    }

    /**
     * Tests intersect() when there are no common elements.
     */
    @Test
    void testIntersectEdgeNoCommonElements() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(3);
        set2.add(4);

        assertEquals("[]", set1.intersect(set2).toString());
    }

    /**
     * Tests diff() for a normal case.
     */
    @Test
    void testDiffNormal() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);

        assertEquals("[1]", set1.diff(set2).toString());
    }

    /**
     * Tests diff() for identical sets.
     */
    @Test
    void testDiffEdgeIdenticalSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(1);
        set2.add(2);

        assertEquals("[]", set1.diff(set2).toString());
    }

    /**
     * Tests complement() for a normal case.
     */
    @Test
    void testComplementNormal() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);
        set1.add(3);

        set2.add(2);
        set2.add(3);
        set2.add(4);

        assertEquals("[4]", set1.complement(set2).toString());
    }

    /**
     * Tests complement() for disjoint sets.
     */
    @Test
    void testComplementEdgeDisjointSets() {
        IntegerSet set1 = new IntegerSet();
        IntegerSet set2 = new IntegerSet();

        set1.add(1);
        set1.add(2);

        set2.add(3);
        set2.add(4);

        assertEquals("[3, 4]", set1.complement(set2).toString());
    }

    /**
     * Tests isEmpty() for an empty set.
     */
    @Test
    void testIsEmptyNormalEmptySet() {
        IntegerSet set = new IntegerSet();
        assertTrue(set.isEmpty());
    }

    /**
     * Tests isEmpty() for a non-empty set.
     */
    @Test
    void testIsEmptyEdgeNonEmptySet() {
        IntegerSet set = new IntegerSet();
        set.add(1);
        assertFalse(set.isEmpty());
    }

    /**
     * Tests toString() for a normal unsorted insertion order.
     */
    @Test
    void testToStringNormal() {
        IntegerSet set = new IntegerSet();
        set.add(3);
        set.add(1);
        set.add(2);
        assertEquals("[1, 2, 3]", set.toString());
    }

    /**
     * Tests toString() for an empty set.
     */
    @Test
    void testToStringEdgeEmptySet() {
        IntegerSet set = new IntegerSet();
        assertEquals("[]", set.toString());
    }
}