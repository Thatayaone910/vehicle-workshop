package za.co.bhunganecodes.workshop.service;

import za.co.bhunganecodes.workshop.model.WorkOrder;

/**
 * A concrete {@link Workshop} that services work orders inside a
 * fully equipped garage service bay.
 * <p>
 * This class demonstrates <b>inheritance</b>: it extends the abstract
 * {@code Workshop} and provides a workshop-specific implementation
 * of the {@link #service(WorkOrder)} method.
 * </p>
 */
public class GarageWorkshop extends Workshop {

    /**
     * Constructs a GarageWorkshop with the given display name.
     *
     * @param workshopName the display name of this workshop
     */
    public GarageWorkshop(String workshopName) {
        // TODO Step 5c: Call super(workshopName) to initialise the base class
    }

    /**
     * Services the given work order using an in-garage service bay.
     * <p>
     * Prints to standard output:<br>
     * {@code "[workshopName] completing [jobName] for [clientName] using in-garage service bay."}
     * </p>
     *
     * @param order the work order to service
     */
    @Override
    protected void service(WorkOrder order) {
        // TODO Step 5d: Print the service message in the format above
        //               Hint: workshopName() is inherited from Workshop
        //                     order.serviceJob().name() gives the job name
        //                     order.client() gives the client name
    }
}
