import java.util.Random;
import java.io.File;
import processing.core.PImage;

public class Fountain {

    private static Random randGen;

    private static PImage fountainImage;

    private static int positionX;

    private static int positionY;

    private static Droplet[] droplets;

    private static int startColor; // blue: Utility.color(23,141,235)

    private static int endColor; // lighter blue: Utility.color(23,200,255)

    /**
     * This method updates the positions and age of the droplet at a certain index
     *
     * @param index The index of the droplet being updated
     */
    private static void updateDroplet(int index) {
        float newDropletPositionX = droplets[index].getPositionX() + droplets[index].getVelocityX();
        droplets[index].setPositionX(newDropletPositionX);

        droplets[index].setVelocityY(droplets[index].getVelocityY() + 0.3f);

        float newDropletPositionY = droplets[index].getPositionY() + droplets[index].getVelocityY();
        droplets[index].setPositionY(newDropletPositionY);

        Utility.fill(Utility.color(23, 141, 235), 75);

        Utility.circle(droplets[index].getPositionX(), droplets[index].getPositionY(),
                droplets[index].getSize());

        droplets[index].setAge(droplets[index].getAge() + 1);
    }

    /**
     * This method creates new droplets with in the Droplet array
     *
     * @param numDroplets The number of the droplet to create
     */
    private static void createNewDroplets(int numDroplets) {
        int count = 0;

        for (int i = 0; i < droplets.length; i++) {
            if (droplets[i] == null) {
                droplets[i] = new Droplet();
                droplets[i].setPositionX(positionX + (randGen.nextFloat() * 6 - 3));
                droplets[i].setPositionY(positionY + (randGen.nextFloat() * 6 - 3));
                droplets[i].setSize(randGen.nextInt(8) + 4);
                droplets[i].setColor(Utility.lerpColor(startColor, endColor, randGen.nextFloat()));
                droplets[i].setVelocityX(randGen.nextFloat() * 2 - 1);
                droplets[i].setVelocityY(randGen.nextFloat() * 5 - 10);
                droplets[i].setAge(randGen.nextInt(41));
                droplets[i].setTransparency(randGen.nextInt(96) + 32);
                count++;
                if (i == (droplets.length - 1)) {
                    break;
                }
                if (count == numDroplets) {
                    break;
                }
            }
        }
    }

    /**
     * This method sets droplets that are above the maximum age to null
     *
     * @param maxAge The maximum age of droplets
     */
    private static void removeOldDroplets(int maxAge) {
        for (int i = 0; i < droplets.length; i++) {
            if (droplets[i] != null && droplets[i].getAge() > maxAge) {
                droplets[i] = null;
            }
        }
    }

    public static void main(String[] args) {

        Utility.runApplication(); // starts the application
    }

    /**
     * This method sets up the fountain
     */
    public static void setup() {
        randGen = new Random();

        System.out.println("testUpdateDroplet(): " + testUpdateDroplet());
        System.out.println("testRemoveOldDroplets(): " + testRemoveOldDroplets());

        positionX = Utility.width() / 2;
        positionY = Utility.height() / 2;

        // load the image of the fountain
        fountainImage = Utility.loadImage("images" + File.separator + "fountain.png");

        startColor = Utility.color(23, 141, 235);
        endColor = Utility.color(23, 200, 255);

        droplets = new Droplet[800];
    }

    /**
     * This method draws the fountain
     */
    public static void draw() {
        Utility.background(Utility.color(253, 245, 230));

        // Draw the fountain image to the screen at position (positionX, positionY)
        Utility.image(fountainImage, positionX, positionY);

        createNewDroplets(10);

        for (int i = 0; i < droplets.length; i++) {
            if (droplets[i] != null) {
                updateDroplet(i);
            }
        }

        removeOldDroplets(80);
    }

    /**
     * This method moves the fountain to where the mouse pressed
     */
    public static void mousePressed() {
        positionX = Utility.mouseX();
        positionY = Utility.mouseY();
    }

    /**
     * This method takes a screenshot and stores it if the "s" key is pressed
     */
    public static void keyPressed(char pressedKey) {
        String pressedKeyToString = Character.toString(pressedKey);
        if (pressedKeyToString.equalsIgnoreCase("s")) {
            Utility.save("screenshot.png");
        }
    }

    /**
     * This tester initializes the droplets array to hold at least three droplets. Creates a single
     * droplet at position (3,3) with velocity (-1,-2). Then checks whether calling updateDroplet()
     * on this droplet’s index correctly results in changing its position to (2.0, 1.3).
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testUpdateDroplet() {
        float expectedPositionX = 2.0f;
        float expectedPositionY = 1.3f;

        droplets = new Droplet[3];

        droplets[0] = new Droplet();
        droplets[0].setPositionX(3);
        droplets[0].setPositionY(3);
        droplets[0].setVelocityX(-1);
        droplets[0].setVelocityY(-2);
        updateDroplet(0);

        if (Float.compare(expectedPositionX, droplets[0].getPositionX()) != 0) {
            return false;
        } else if (Float.compare(expectedPositionY, droplets[0].getPositionY()) != 0) {
            return false;
        }
        return true;
    }

    /**
     * This tester initializes the droplets array to hold at least three droplets. Calls
     * removeOldDroplets(6) on an array with three droplets (two of which have ages over six and
     * another that does not). Then checks whether the old droplets were removed and the young
     * droplet was left alone.
     *
     * @return true when no defect is found, and false otherwise
     */
    private static boolean testRemoveOldDroplets() {
        droplets = new Droplet[3];

        droplets[0] = new Droplet();
        droplets[1] = new Droplet();
        droplets[2] = new Droplet();

        droplets[0].setAge(10);
        droplets[1].setAge(7);
        droplets[2].setAge(2);

        removeOldDroplets(6);

        return droplets[2].getAge() == 2 && droplets[0] == null && droplets[1] == null;
        // implement this test
    }

}
