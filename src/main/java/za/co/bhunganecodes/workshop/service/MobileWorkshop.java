package za.co.bhunganecodes.workshop.service;

import za.co.bhunganecodes.workshop.model.WorkOrder;

/**
 * A concrete {@link Workshop} that services work orders on-site
 * using a mobile dispatch unit.
 * <p>
 * This class demonstrates <b>inheritance</b>: it extends the abstract
 * {@code Workshop} and provides a workshop-specific implementation
 * of the {@link #service(WorkOrder)} method.
 * </p>
 */
public class MobileWorkshop extends Workshop {

    /**
     * Constructs a MobileWorkshop with the given display name.
     *
     * @param workshopName the display name of this workshop
     */
    public MobileWorkshop(String workshopName) {
        // TODO Step 5a: Call super(workshopName) to initialise the base class
    }

    /**
     * Services the given work order using on-site mobile dispatch.
     * <p>
     * Prints to standard output:<br>
     * {@code "[workshopName] completing [jobName] for [clientName] using on-site mobile dispatch."}
     * </p>
     *
     * @param order the work order to service
     */
    @Override
    protected void service(WorkOrder order) {
        // TODO Step 5b: Print the service message in the format above
        //               Hint: workshopName() is inherited from Workshop
        //                     order.serviceJob().name() gives the job name
        //                     order.client() gives the client name
    }
}
