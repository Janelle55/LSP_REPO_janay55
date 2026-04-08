package org.howard.edu.lsp.assignment5;

import java.util.ArrayList;
import java.util.Collections;

/**
 * IntegerSet represents a mathematical set of integers with no duplicates.
 * It supports standard set operations such as union, intersection, difference,
 * and complement.
 */
public class IntegerSet {

    private ArrayList<Integer> set;

    /**
     * Constructs an empty IntegerSet.
     */
    public IntegerSet() {
        set = new ArrayList<>();
    }

    /**
     * Removes all elements from the set.
     */
    public void clear() {
        set.clear();
    }

    /**
     * Returns the number of elements in the set.
     *
     * @return the size of the set
     */
    public int length() {
        return set.size();
    }

    /**
     * Checks if the set is equal to another IntegerSet.
     *
     * @param b the other IntegerSet
     * @return true if both sets contain the same elements, false otherwise
     */
    public boolean equals(IntegerSet b) {
        if (b == null) {
            return false;
        }
        return set.containsAll(b.set) && b.set.containsAll(set);
    }
    /**
     * Checks if the set contains a given value.
     *
     * @param value the value to check
     * @return true if the value exists in the set, false otherwise
     */
    public boolean contains(int value) {
        return set.contains(value);
    }

    /**
     * Returns the largest value in the set.
     *
     * @return the largest integer
     * @throws RuntimeException if the set is empty
     */
    public int largest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.max(set);
    }

    /**
     * Returns the smallest value in the set.
     *
     * @return the smallest integer
     * @throws RuntimeException if the set is empty
     */
    public int smallest() {
        if (set.isEmpty()) {
            throw new RuntimeException("Set is empty");
        }
        return Collections.min(set);
    }

    /**
     * Adds an item to the set if it is not already present.
     *
     * @param item the item to add
     */
    public void add(int item) {
        if (!set.contains(item)) {
            set.add(item);
        }
    }

    /**
     * Removes an item from the set if it exists.
     *
     * @param item the item to remove
     */
    public void remove(int item) {
        set.remove(Integer.valueOf(item));
    }

    /**
     * Returns a new set that is the union of this set and another set.
     *
     * @param intSetb the other set
     * @return a new IntegerSet containing all elements from both sets
     */
    public IntegerSet union(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        result.set.addAll(this.set);
        for (int val : intSetb.set) {
            if (!result.set.contains(val)) {
                result.set.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new set that is the intersection of this set and another set.
     *
     * @param intSetb the other set
     * @return a new IntegerSet containing common elements
     */
    public IntegerSet intersect(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) {
            if (intSetb.set.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new set containing elements in this set but not in the other set.
     *
     * @param intSetb the other set
     * @return a new IntegerSet representing the difference
     */
    public IntegerSet diff(IntegerSet intSetb) {
        IntegerSet result = new IntegerSet();
        for (int val : this.set) {
            if (!intSetb.set.contains(val)) {
                result.add(val);
            }
        }
        return result;
    }

    /**
     * Returns a new set containing elements in the other set but not in this set.
     *
     * @param intSetb the other set
     * @return a new IntegerSet representing the complement
     */
    public IntegerSet complement(IntegerSet intSetb) {
        return intSetb.diff(this);
    }

    /**
     * Checks if the set is empty.
     *
     * @return true if the set has no elements, false otherwise
     */
    public boolean isEmpty() {
        return set.isEmpty();
    }

    /**
     * Returns a string representation of the set in ascending order.
     *
     * @return formatted string of elements
     */
    @Override
    public String toString() {
        ArrayList<Integer> sorted = new ArrayList<>(set);
        Collections.sort(sorted);
        return sorted.toString();
    }
}