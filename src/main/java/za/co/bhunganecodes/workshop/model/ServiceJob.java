package za.co.bhunganecodes.workshop.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a vehicle service job composed of a name and a list of {@link Part}s.
 * <p>
 * <b>Design principle – Defensive Copies:</b><br>
 * The constructor must store a <em>mutable copy</em> of the supplied list, not the
 * reference itself. If you stored the reference, any caller who still holds the
 * original list could mutate your service job without your knowledge.<br>
 * Similarly, {@link #parts()} must return a <em>defensive copy</em> so
 * callers cannot call {@code .add()} / {@code .remove()} on the internal list.
 * </p>
 */
public class ServiceJob {

    // TODO Step 2a: Declare a private String field called `name`

    // TODO Step 2b: Declare a private List<Part> field called `parts`

    /**
     * Constructs a ServiceJob with the given name and initial parts list.
     * <p>
     * <b>Important:</b> store a <em>copy</em> of {@code partList}, not
     * the reference – see the class-level Javadoc for why.
     * </p>
     *
     * @param name     the display name of the service job (e.g. "Full Brake Service")
     * @param partList the initial list of parts required
     */
    public ServiceJob(String name, List<Part> partList) {
        // TODO Step 2c: Assign name → this.name
        // TODO Step 2d: Store a NEW ArrayList copy of partList into this.parts
        //               Hint: new ArrayList<>(partList)
    }

    /**
     * Returns the service job name.
     *
     * @return the name of this service job
     */
    public String name() {
        // TODO Step 2e: Return name
        return null;
    }

    /**
     * Returns a <em>defensive copy</em> of the parts list.
     * <p>
     * Callers receive their own copy and cannot mutate the internal list.
     * </p>
     *
     * @return a new list containing the same parts as the internal list
     */
    public List<Part> parts() {
        // TODO Step 2f: Return new ArrayList<>(parts)
        return null;
    }

    /**
     * Appends a part to this service job's internal list.
     *
     * @param part the part to add
     */
    public void addPart(Part part) {
        // TODO Step 2g: Add part to the internal parts list
    }

    /**
     * Returns a human-readable summary of this service job.
     * <p>
     * Format:
     * <pre>
     * Full Brake Service
     * Brake Pads: 4.0 units
     * Brake Fluid: 1.0 units
     * </pre>
     * (Job name on the first line, each part on its own line.)
     * </p>
     *
     * @return formatted multi-line string
     */
    @Override
    public String toString() {
        // TODO Step 2h: Build and return a string: name + newline + each part.toString() on its own line
        //               Hint: Use a StringBuilder or String.join / stream
        return null;
    }
}
