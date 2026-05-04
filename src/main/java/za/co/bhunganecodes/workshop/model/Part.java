package za.co.bhunganecodes.workshop.model;

/**
 * Represents a single part used in a vehicle service job.
 * <p>
 * Examples: "Brake Pads" at 4.0 units, "Engine Oil" at 5.0 units.
 * </p>
 *
 * <b>Encapsulation rule:</b> both fields must be {@code private}; access
 * is provided only through the methods listed below.
 */
public class Part {

    // TODO Step 1a: Declare a private String field called `name`

    // TODO Step 1b: Declare a private double field called `quantity`

    /**
     * Constructs a Part with the given name and quantity.
     *
     * @param partName     the display name of the part (e.g. "Brake Pads")
     * @param partQuantity the number of units required (e.g. 4.0)
     */
    public Part(String partName, double partQuantity) {
        // TODO Step 1c: Assign partName  → name
        // TODO Step 1d: Assign partQuantity → quantity
    }

    /**
     * Returns the part name.
     *
     * @return the name of this part
     */
    public String name() {
        // TODO Step 1e: Return name
        return null;
    }

    /**
     * Returns the quantity of units required.
     *
     * @return the quantity of this part
     */
    public double quantity() {
        // TODO Step 1f: Return quantity
        return 0;
    }

    /**
     * Updates the quantity of this part.
     *
     * @param newQuantity the new quantity in units
     * @throws IllegalArgumentException if {@code newQuantity} is negative
     */
    public void updateQuantity(double newQuantity) {
        // TODO Step 1g: If newQuantity < 0, throw new IllegalArgumentException with a meaningful message
        // TODO Step 1h: Otherwise, assign newQuantity → quantity
    }

    /**
     * Returns a human-readable representation of this part.
     * <p>
     * Format: {@code "Brake Pads: 4.0 units"}
     * </p>
     *
     * @return formatted string summary
     */
    @Override
    public String toString() {
        // TODO Step 1i: Return "<name>: <quantity> units"  e.g. "Brake Pads: 4.0 units"
        return null;
    }
}
