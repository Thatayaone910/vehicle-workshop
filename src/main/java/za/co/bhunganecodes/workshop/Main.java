package za.co.bhunganecodes.workshop;

import za.co.bhunganecodes.workshop.model.Part;
import za.co.bhunganecodes.workshop.model.ServiceJob;
import za.co.bhunganecodes.workshop.model.WorkOrder;
import za.co.bhunganecodes.workshop.service.GarageWorkshop;
import za.co.bhunganecodes.workshop.service.MobileWorkshop;

import java.util.List;

/**
 * Entry point for the Vehicle Workshop simulation.
 * <p>
 * This class is provided for manual testing and demonstration purposes.
 * Your grade is determined entirely by the automated JUnit tests — you do
 * not need to modify this file, but feel free to experiment here.
 * </p>
 */
public class Main {

    public static void main(String[] args) {

        // --- Set up service jobs ---
        ServiceJob brakeService = new ServiceJob("Full Brake Service", List.of(
                new Part("Brake Pads", 4.0),
                new Part("Brake Fluid", 1.0)
        ));

        ServiceJob oilService = new ServiceJob("Oil & Filter Change", List.of(
                new Part("Engine Oil", 5.0),
                new Part("Oil Filter", 1.0)
        ));

        // --- Garage Workshop demo ---
        GarageWorkshop garage = new GarageWorkshop("Cape Town Auto Centre");
        garage.addServiceJob(brakeService);

        System.out.println("=== " + garage.workshopName() + " ===");
        WorkOrder w1 = garage.placeOrder("Jane", "Full Brake Service");
        WorkOrder w2 = garage.placeOrder("Peter", "Full Brake Service");

        System.out.println("Queue size: " + garage.workOrderQueue().size());
        garage.processNextOrder();
        garage.processNextOrder();

        System.out.println("Order 1 status: " + w1.status());
        System.out.println("Order 2 status: " + w2.status());

        System.out.println();

        // --- Mobile Workshop demo ---
        MobileWorkshop mobile = new MobileWorkshop("RoadFix Mobile Unit");
        mobile.addServiceJob(oilService);

        System.out.println("=== " + mobile.workshopName() + " ===");
        mobile.placeOrder("Thabo", "Oil & Filter Change");
        mobile.processNextOrder();
    }
}
