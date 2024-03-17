//////////////// FILE HEADER (INCLUDE IN EVERY FILE) //////////////////////////
//
// Title:    Node
// Course:   CS 300 Spring 2022
//
// Author:   Steven Ren
// Email:    skren@wisc.edu
// Lecturer: Mouna Kacem
//
//////////////////// PAIR PROGRAMMERS COMPLETE THIS SECTION ///////////////////
//
// Partner Name:    (name of your pair programming partner)
// Partner Email:   (email address of your programming partner)
// Partner Lecturer's Name: (name of your partner's lecturer)
//
// VERIFY THE FOLLOWING BY PLACING AN X NEXT TO EACH TRUE STATEMENT:
//   ___ Write-up states that pair programming is allowed for this assignment.
//   ___ We have both read and understand the course Pair Programming Policy.
//   ___ We have registered our team prior to the team registration deadline.
//
///////////////////////// ALWAYS CREDIT OUTSIDE HELP //////////////////////////
//
// Persons:         NONE
// Online Sources:  NONE
//
///////////////////////////////////////////////////////////////////////////////

public class Node<T> {
    private T data; // The data contained in the Node
    private Node<T> next; //The Node following this one

    /**
     * Basic constructor; creates a node that contains the provided data and no linkages
     *
     * @param data to be stored within this node
     */
    public Node(T data) {
        this.data = data;
    }

    /**
     * A constructor that allows specification of the next node in the chain
     *
     * @param data to be stored within this node
     * @param next node, which comes after this node
     */
    public Node(T data, Node<T> next) {
        this.data = data;
        this.next = next;
    }

    /**
     * Accessor method for this node's data
     *
     * @return the data stored within this node
     */
    public T getData() {
        return data;
    }

    /**
     * Accessor method for the node following this one
     *
     * @return the next node
     */
    public Node<T> getNext() {
        return next;
    }

    /**
     * Mutator method for the node following this one
     *
     * @param next the node to follow this one
     */
    public void setNext(Node<T> next) {
        this.next = next;
    }
}
