/**
 * This class extends the Button class to create the screenshot button
 */
public class ScreenshotButton extends Button {
    /**
     * Creates a new ScreenshotButton object labeled "Take a screenshot" at a specific position on
     * the screen.
     *
     * @param x x-position to be assigned to this screenshot button
     * @param y y-position to be assigned to this screenshot button
     */
    public ScreenshotButton(int x, int y) {
        super("Take a screenshot", x, y);
    }

    /**
     * Defines the behavior of this button when the mouse is pressed. When it is clicked, this
     * button takes a screenshot of the game screen. This method calls the PApplet.save() method to
     * save the screenshot with the filename "screenshot.png".
     */
    @Override
    public void mousePressed() {
        if (isMouseOver()) {
            processing.save("screenshot.png");
        }
    }
}
