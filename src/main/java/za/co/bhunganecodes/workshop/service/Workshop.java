package za.co.bhunganecodes.workshop.service;

import za.co.bhunganecodes.workshop.model.ServiceJob;
import za.co.bhunganecodes.workshop.model.WorkOrder;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Abstract base class for all vehicle workshops.
 * <p>
 * Manages a catalogue of {@link ServiceJob}s and a queue of {@link WorkOrder}s.
 * The workshop-specific servicing step is delegated to concrete subclasses
 * via the abstract {@link #service(WorkOrder)} method.
 * </p>
 *
 * <b>Inheritance rule:</b> this class is {@code abstract} – it cannot be
 * instantiated directly. Subclasses must implement {@link #service(WorkOrder)}.
 */
public abstract class Workshop {

    // TODO Step 4a: Declare a private String field called `workshopName`

    // TODO Step 4b: Declare a private Map<String, ServiceJob> field called `serviceJobs`

    // TODO Step 4c: Declare a private List<WorkOrder> field called `workOrderQueue`

    // TODO Step 4d: Declare a private int field called `orderCounter` (starts at 0)

    /**
     * Constructs a Workshop with the given display name.
     * Initialises {@code serviceJobs} as a new {@link HashMap} and
     * {@code workOrderQueue} as a new {@link ArrayList}.
     *
     * @param workshopName the display name of this workshop (e.g. "Cape Town Auto Centre")
     */
    public Workshop(String workshopName) {
        // TODO Step 4e: Assign workshopName → this.workshopName
        // TODO Step 4f: Initialise this.serviceJobs as new HashMap<>()
        // TODO Step 4g: Initialise this.workOrderQueue as new ArrayList<>()
        // (orderCounter is 0 by default in Java — no explicit initialisation needed)
    }

    // -------------------------------------------------------------------------
    // Concrete methods — implement all of these
    // -------------------------------------------------------------------------

    /**
     * Adds a service job to the workshop's catalogue.
     * Uses the service job's name as the map key.
     *
     * @param job the service job to add
     */
    public void addServiceJob(ServiceJob job) {
        // TODO Step 4h: Put job into the serviceJobs map using job.name() as the key
    }

    /**
     * Retrieves a service job by name.
     *
     * @param jobName the name to look up
     * @return the matching {@link ServiceJob}, or {@code null} if not found
     */
    public ServiceJob getServiceJob(String jobName) {
        // TODO Step 4i: Return the service job from the map (returns null automatically if not found)
        return null;
    }

    /**
     * Returns an unmodifiable view of the entire service job catalogue.
     *
     * @return unmodifiable map of job name → ServiceJob
     */
    public Map<String, ServiceJob> getAllServiceJobs() {
        // TODO Step 4j: Return Collections.unmodifiableMap(serviceJobs)
        return null;
    }

    /**
     * Places a new work order for the given client and service job name.
     * <p>
     * The order ID is {@code ++orderCounter} (pre-increment, so the first order is 1).
     * </p>
     *
     * @param clientName the name of the client
     * @param jobName    the name of the service job to perform
     * @return the newly created {@link WorkOrder}
     * @throws IllegalArgumentException if no service job with {@code jobName} is found
     */
    public WorkOrder placeOrder(String clientName, String jobName) {
        // TODO Step 4k: Look up the service job by jobName; throw IllegalArgumentException if null
        // TODO Step 4l: Create a new WorkOrder with id = ++orderCounter, clientName, and the service job
        // TODO Step 4m: Add the work order to workOrderQueue
        // TODO Step 4n: Return the work order
        return null;
    }

    /**
     * Processes the next {@link WorkOrder.WorkOrderStatus#PENDING} work order in the queue.
     * <ol>
     *   <li>Find the first PENDING work order in {@code workOrderQueue}.</li>
     *   <li>Set its status to {@link WorkOrder.WorkOrderStatus#IN_PROGRESS}.</li>
     *   <li>Call {@link #service(WorkOrder)} (implemented by the subclass).</li>
     *   <li>Set its status to {@link WorkOrder.WorkOrderStatus#COMPLETED}.</li>
     *   <li>Return the completed work order.</li>
     * </ol>
     *
     * @return the completed {@link WorkOrder}, or {@code null} if no pending orders exist
     */
    public WorkOrder processNextOrder() {
        // TODO Step 4o: Stream (or loop) through workOrderQueue to find the first PENDING order
        // TODO Step 4p: If none found, return null
        // TODO Step 4q: Set status → IN_PROGRESS
        // TODO Step 4r: Call service(order)
        // TODO Step 4s: Set status → COMPLETED
        // TODO Step 4t: Return the order
        return null;
    }

    /**
     * Returns an unmodifiable view of the current work order queue.
     *
     * @return unmodifiable list of work orders
     */
    public List<WorkOrder> workOrderQueue() {
        // TODO Step 4u: Return Collections.unmodifiableList(workOrderQueue)
        return null;
    }

    /**
     * Returns the workshop's display name.
     *
     * @return workshopName
     */
    public String workshopName() {
        // TODO Step 4v: Return workshopName
        return null;
    }

    // -------------------------------------------------------------------------
    // Abstract method — subclasses must implement this
    // -------------------------------------------------------------------------

    /**
     * Performs the workshop-specific servicing step for the given work order.
     * <p>
     * Called automatically by {@link #processNextOrder()} after the work order
     * status is set to {@code IN_PROGRESS}.
     * </p>
     *
     * @param order the work order currently being serviced
     */
    protected abstract void service(WorkOrder order);
}
