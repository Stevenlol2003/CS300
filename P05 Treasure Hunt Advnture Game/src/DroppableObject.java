/**
 * This class extends the DraggableObject class to create droppable objects
 */
public class DroppableObject extends DraggableObject {
    private InteractiveObject target; // target of this droppable object

    /**
     * Creates a new Droppable object with specific name, x and y positions, message, target, and
     * sets its next clue.
     *
     * @param name     name to be assigned to this droppable object
     * @param x        x-position of this droppable object
     * @param y        y-position this droppable object
     * @param message  message to be assigned to this droppable object
     * @param target   target where this droppable object should be dropped
     * @param nextClue reference to an interactive object which will be activated when this
     *                 droppable object is dropped on its target
     */
    public DroppableObject(String name, int x, int y, String message, InteractiveObject target,
                           InteractiveObject nextClue) {
        super(name, x, y, message);
        this.target = target; // sets the next target
        setNextClue(nextClue); // sets the next clue
    }

    /**
     * Stops dragging this droppable object. Also, if this droppable object is over its target and
     * the target is activated, this method (1) deactivates both this object and its target, (2)
     * activates the next clue, and (3) prints the message of this draggable object to the console.
     */
    @Override
    public void mouseReleased() {
        stopDragging(); // stop dragging
        if (isOver(target) && isActive()) {
            deactivate(); // deactivates this object
            target.deactivate(); // deactivates the object's target
            activateNextClue(); // activates the next clue
            System.out.println(message()); // prints the message of this draggable object
        }
    }

    /**
     * Checks whether this object is over another interactive object.
     *
     * @param other reference to another interactive object. We assume that other is NOT null.
     * @return true if this droppable object and other overlap and false otherwise
     */
    public boolean isOver(InteractiveObject other) {
        // uses comparison conditions to check if rectangles of the objects' image overlap
        return getX() < other.getX() + other.image.width && getX() + image.width > other.getX() &&
                getY() < other.getY() + other.image.height && getY() + image.height > other.getY();
    }
}
