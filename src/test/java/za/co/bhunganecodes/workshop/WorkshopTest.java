package za.co.bhunganecodes.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.workshop.model.Part;
import za.co.bhunganecodes.workshop.model.ServiceJob;
import za.co.bhunganecodes.workshop.model.WorkOrder;
import za.co.bhunganecodes.workshop.model.WorkOrder.WorkOrderStatus;
import za.co.bhunganecodes.workshop.service.GarageWorkshop;
import za.co.bhunganecodes.workshop.service.MobileWorkshop;
import za.co.bhunganecodes.workshop.service.Workshop;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link Workshop}, {@link GarageWorkshop}, and {@link MobileWorkshop}.
 * <p>
 * Because {@code Workshop} is abstract, it is tested through its concrete subclasses.
 * </p>
 *
 * Run with: mvn clean test -Dtest=WorkshopTest
 */
@DisplayName("Workshop (via GarageWorkshop & MobileWorkshop)")
class WorkshopTest {

    private GarageWorkshop garage;
    private MobileWorkshop mobile;
    private ServiceJob brakeService;
    private ServiceJob oilService;

    @BeforeEach
    void setUp() {
        garage = new GarageWorkshop("Cape Town Auto Centre");
        mobile = new MobileWorkshop("RoadFix Mobile Unit");

        brakeService = new ServiceJob("Full Brake Service", new ArrayList<>(List.of(
                new Part("Brake Pads", 4.0),
                new Part("Brake Fluid", 1.0)
        )));

        oilService = new ServiceJob("Oil & Filter Change", new ArrayList<>(List.of(
                new Part("Engine Oil", 5.0),
                new Part("Oil Filter", 1.0)
        )));
    }

    // =========================================================================
    // workshopName()
    // =========================================================================
    @Nested
    @DisplayName("workshopName()")
    class WorkshopNameTests {

        @Test
        @DisplayName("GarageWorkshop returns its name correctly")
        void workshopName_garage() {
            assertEquals("Cape Town Auto Centre", garage.workshopName());
        }

        @Test
        @DisplayName("MobileWorkshop returns its name correctly")
        void workshopName_mobile() {
            assertEquals("RoadFix Mobile Unit", mobile.workshopName());
        }
    }

    // =========================================================================
    // Inheritance
    // =========================================================================
    @Nested
    @DisplayName("Inheritance structure")
    class InheritanceTests {

        @Test
        @DisplayName("GarageWorkshop is an instance of Workshop")
        void garage_isInstanceOfWorkshop() {
            assertInstanceOf(Workshop.class, garage);
        }

        @Test
        @DisplayName("MobileWorkshop is an instance of Workshop")
        void mobile_isInstanceOfWorkshop() {
            assertInstanceOf(Workshop.class, mobile);
        }
    }

    // =========================================================================
    // addServiceJob() & getServiceJob()
    // =========================================================================
    @Nested
    @DisplayName("addServiceJob() and getServiceJob()")
    class ServiceJobCatalogueTests {

        @Test
        @DisplayName("getServiceJob() returns the job after it has been added")
        void getServiceJob_afterAdd() {
            garage.addServiceJob(brakeService);
            assertNotNull(garage.getServiceJob("Full Brake Service"));
        }

        @Test
        @DisplayName("getServiceJob() returns the correct ServiceJob object")
        void getServiceJob_correctObject() {
            garage.addServiceJob(brakeService);
            assertSame(brakeService, garage.getServiceJob("Full Brake Service"));
        }

        @Test
        @DisplayName("getServiceJob() returns null for an unknown job name")
        void getServiceJob_unknownName_returnsNull() {
            assertNull(garage.getServiceJob("Tyre Rotation"));
        }

        @Test
        @DisplayName("Multiple service jobs can be added and retrieved independently")
        void addServiceJob_multiple() {
            garage.addServiceJob(brakeService);
            garage.addServiceJob(oilService);
            assertSame(brakeService, garage.getServiceJob("Full Brake Service"));
            assertSame(oilService,   garage.getServiceJob("Oil & Filter Change"));
        }
    }

    // =========================================================================
    // getAllServiceJobs()
    // =========================================================================
    @Nested
    @DisplayName("getAllServiceJobs()")
    class GetAllServiceJobsTests {

        @Test
        @DisplayName("Returns empty map when no jobs have been added")
        void getAllServiceJobs_empty() {
            assertTrue(garage.getAllServiceJobs().isEmpty());
        }

        @Test
        @DisplayName("Returns map with correct number of entries")
        void getAllServiceJobs_count() {
            garage.addServiceJob(brakeService);
            garage.addServiceJob(oilService);
            assertEquals(2, garage.getAllServiceJobs().size());
        }

        @Test
        @DisplayName("Returned map is unmodifiable")
        void getAllServiceJobs_unmodifiable() {
            garage.addServiceJob(brakeService);
            assertThrows(UnsupportedOperationException.class,
                    () -> garage.getAllServiceJobs().put("Hack", brakeService));
        }
    }

    // =========================================================================
    // placeOrder()
    // =========================================================================
    @Nested
    @DisplayName("placeOrder()")
    class PlaceOrderTests {

        @BeforeEach
        void addServiceJob() {
            garage.addServiceJob(brakeService);
        }

        @Test
        @DisplayName("Returns a non-null WorkOrder")
        void placeOrder_returnsWorkOrder() {
            assertNotNull(garage.placeOrder("Jane", "Full Brake Service"));
        }

        @Test
        @DisplayName("Returned work order has status PENDING")
        void placeOrder_statusPending() {
            WorkOrder o = garage.placeOrder("Jane", "Full Brake Service");
            assertEquals(WorkOrderStatus.PENDING, o.status());
        }

        @Test
        @DisplayName("Returned work order has the correct client name")
        void placeOrder_correctClient() {
            WorkOrder o = garage.placeOrder("Jane", "Full Brake Service");
            assertEquals("Jane", o.client());
        }

        @Test
        @DisplayName("Returned work order references the correct service job")
        void placeOrder_correctServiceJob() {
            WorkOrder o = garage.placeOrder("Jane", "Full Brake Service");
            assertEquals("Full Brake Service", o.serviceJob().name());
        }

        @Test
        @DisplayName("First work order has ID 1")
        void placeOrder_firstId_isOne() {
            WorkOrder o = garage.placeOrder("Jane", "Full Brake Service");
            assertEquals(1, o.workOrderId());
        }

        @Test
        @DisplayName("Work order IDs increment with each placed order")
        void placeOrder_incrementingIds() {
            WorkOrder o1 = garage.placeOrder("Jane",  "Full Brake Service");
            WorkOrder o2 = garage.placeOrder("Peter", "Full Brake Service");
            WorkOrder o3 = garage.placeOrder("Thabo", "Full Brake Service");
            assertEquals(1, o1.workOrderId());
            assertEquals(2, o2.workOrderId());
            assertEquals(3, o3.workOrderId());
        }

        @Test
        @DisplayName("Work order is added to the queue after placement")
        void placeOrder_addedToQueue() {
            garage.placeOrder("Jane", "Full Brake Service");
            assertEquals(1, garage.workOrderQueue().size());
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for unknown service job")
        void placeOrder_unknownJob_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> garage.placeOrder("Jane", "Tyre Rotation"));
        }
    }

    // =========================================================================
    // workOrderQueue()
    // =========================================================================
    @Nested
    @DisplayName("workOrderQueue()")
    class WorkOrderQueueTests {

        @Test
        @DisplayName("Queue is empty before any orders are placed")
        void workOrderQueue_initiallyEmpty() {
            assertTrue(garage.workOrderQueue().isEmpty());
        }

        @Test
        @DisplayName("Queue is unmodifiable")
        void workOrderQueue_unmodifiable() {
            garage.addServiceJob(brakeService);
            garage.placeOrder("Jane", "Full Brake Service");
            WorkOrder dummy = new WorkOrder(99, "Hack", brakeService);
            assertThrows(UnsupportedOperationException.class,
                    () -> garage.workOrderQueue().add(dummy));
        }
    }

    // =========================================================================
    // processNextOrder()
    // =========================================================================
    @Nested
    @DisplayName("processNextOrder()")
    class ProcessNextOrderTests {

        @BeforeEach
        void addServiceJob() {
            garage.addServiceJob(brakeService);
        }

        @Test
        @DisplayName("Returns null when there are no pending orders")
        void processNextOrder_noPending_returnsNull() {
            assertNull(garage.processNextOrder());
        }

        @Test
        @DisplayName("Returns the processed work order (not null)")
        void processNextOrder_returnsWorkOrder() {
            garage.placeOrder("Jane", "Full Brake Service");
            assertNotNull(garage.processNextOrder());
        }

        @Test
        @DisplayName("Processed work order has status COMPLETED")
        void processNextOrder_statusCompleted() {
            WorkOrder o = garage.placeOrder("Jane", "Full Brake Service");
            garage.processNextOrder();
            assertEquals(WorkOrderStatus.COMPLETED, o.status());
        }

        @Test
        @DisplayName("Processes work orders in FIFO order")
        void processNextOrder_fifoOrder() {
            WorkOrder o1 = garage.placeOrder("Jane",  "Full Brake Service");
            WorkOrder o2 = garage.placeOrder("Peter", "Full Brake Service");

            WorkOrder processed = garage.processNextOrder();
            assertEquals(o1.workOrderId(), processed.workOrderId());
        }

        @Test
        @DisplayName("Second call processes the second work order")
        void processNextOrder_secondCall_processesSecondOrder() {
            garage.placeOrder("Jane", "Full Brake Service");
            WorkOrder o2 = garage.placeOrder("Peter", "Full Brake Service");

            garage.processNextOrder();
            WorkOrder processed = garage.processNextOrder();
            assertEquals(o2.workOrderId(), processed.workOrderId());
        }

        @Test
        @DisplayName("Returns null after all orders are processed")
        void processNextOrder_allDone_returnsNull() {
            garage.placeOrder("Jane", "Full Brake Service");
            garage.processNextOrder();
            assertNull(garage.processNextOrder());
        }

        @Test
        @DisplayName("Skips already-completed orders and returns null when none are pending")
        void processNextOrder_skipsCompleted() {
            garage.placeOrder("Jane", "Full Brake Service");
            garage.processNextOrder(); // completes it
            // No more pending → null
            assertNull(garage.processNextOrder());
        }
    }

    // =========================================================================
    // GarageWorkshop service() output
    // =========================================================================
    @Nested
    @DisplayName("GarageWorkshop — service() output")
    class GarageServiceOutputTests {

        @Test
        @DisplayName("service() prints the correct in-garage service bay message")
        void service_garageMessage() {
            garage.addServiceJob(brakeService);
            garage.placeOrder("Jane", "Full Brake Service");

            // Capture stdout
            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            garage.processNextOrder();
            System.setOut(System.out);

            String output = out.toString().trim();
            assertTrue(output.contains("Cape Town Auto Centre"),
                    "Expected workshop name in output: " + output);
            assertTrue(output.contains("Full Brake Service"),
                    "Expected job name in output: " + output);
            assertTrue(output.contains("Jane"),
                    "Expected client name in output: " + output);
            assertTrue(output.contains("in-garage service bay"),
                    "Expected service method in output: " + output);
        }
    }

    // =========================================================================
    // MobileWorkshop service() output
    // =========================================================================
    @Nested
    @DisplayName("MobileWorkshop — service() output")
    class MobileServiceOutputTests {

        @Test
        @DisplayName("service() prints the correct on-site mobile dispatch message")
        void service_mobileMessage() {
            mobile.addServiceJob(oilService);
            mobile.placeOrder("Thabo", "Oil & Filter Change");

            ByteArrayOutputStream out = new ByteArrayOutputStream();
            System.setOut(new PrintStream(out));
            mobile.processNextOrder();
            System.setOut(System.out);

            String output = out.toString().trim();
            assertTrue(output.contains("RoadFix Mobile Unit"),
                    "Expected workshop name in output: " + output);
            assertTrue(output.contains("Oil & Filter Change"),
                    "Expected job name in output: " + output);
            assertTrue(output.contains("Thabo"),
                    "Expected client name in output: " + output);
            assertTrue(output.contains("on-site mobile dispatch"),
                    "Expected dispatch method in output: " + output);
        }
    }
}
