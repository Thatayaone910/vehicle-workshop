package za.co.bhunganecodes.workshop.model;

/**
 * Represents a client work order that links a client name to a {@link ServiceJob}
 * and tracks the lifecycle of the order via {@link WorkOrderStatus}.
 * <p>
 * Lifecycle: {@code PENDING → IN_PROGRESS → COMPLETED}
 * </p>
 */
public class WorkOrder {

    /**
     * Represents the possible states of a {@link WorkOrder}.
     */
    public enum WorkOrderStatus {
        /** Work order has been placed but not yet started. */
        PENDING,
        /** Work order is currently being serviced. */
        IN_PROGRESS,
        /** Work order has been fully completed and is ready for collection. */
        COMPLETED
    }

    // TODO Step 3a: Declare a private int field called `workOrderId`

    // TODO Step 3b: Declare a private String field called `clientName`

    // TODO Step 3c: Declare a private ServiceJob field called `serviceJob`

    // TODO Step 3d: Declare a private WorkOrderStatus field called `status`

    /**
     * Constructs a WorkOrder with the given ID, client name, and service job.
     * The status defaults to {@link WorkOrderStatus#PENDING}.
     *
     * @param workOrderId  the unique numeric identifier for this work order
     * @param clientName   the name of the client who placed the work order
     * @param serviceJob   the service job to be performed
     */
    public WorkOrder(int workOrderId, String clientName, ServiceJob serviceJob) {
        // TODO Step 3e: Assign workOrderId → this.workOrderId
        // TODO Step 3f: Assign clientName → this.clientName
        // TODO Step 3g: Assign serviceJob → this.serviceJob
        // TODO Step 3h: Set this.status to WorkOrderStatus.PENDING
    }

    /**
     * Returns the unique work order ID.
     *
     * @return workOrderId
     */
    public int workOrderId() {
        // TODO Step 3i: Return workOrderId
        return 0;
    }

    /**
     * Returns the client's name.
     *
     * @return clientName
     */
    public String client() {
        // TODO Step 3j: Return clientName
        return null;
    }

    /**
     * Returns the service job associated with this work order.
     *
     * @return the ServiceJob
     */
    public ServiceJob serviceJob() {
        // TODO Step 3k: Return serviceJob
        return null;
    }

    /**
     * Returns the current status of this work order.
     *
     * @return the WorkOrderStatus
     */
    public WorkOrderStatus status() {
        // TODO Step 3l: Return status
        return null;
    }

    /**
     * Updates the status of this work order.
     *
     * @param newStatus the new {@link WorkOrderStatus} to set
     */
    public void updateStatus(WorkOrderStatus newStatus) {
        // TODO Step 3m: Assign newStatus → this.status
    }

    /**
     * Returns a human-readable summary of this work order.
     * <p>
     * Example: {@code "Work Order #1 | Client: Jane | Job: Full Brake Service | Status: PENDING"}
     * </p>
     *
     * @return formatted string summary
     */
    @Override
    public String toString() {
        // TODO Step 3n: Return a formatted string including workOrderId, clientName, serviceJob.name(), and status
        //               e.g. "Work Order #1 | Client: Jane | Job: Full Brake Service | Status: PENDING"
        return null;
    }
}
