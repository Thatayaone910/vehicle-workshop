package za.co.bhunganecodes.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.workshop.model.Part;
import za.co.bhunganecodes.workshop.model.ServiceJob;
import za.co.bhunganecodes.workshop.model.WorkOrder;
import za.co.bhunganecodes.workshop.model.WorkOrder.WorkOrderStatus;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link WorkOrder}.
 *
 * Run with: mvn clean test -Dtest=WorkOrderTest
 */
@DisplayName("WorkOrder")
class WorkOrderTest {

    private ServiceJob brakeService;
    private WorkOrder workOrder;

    @BeforeEach
    void setUp() {
        brakeService = new ServiceJob("Full Brake Service", new ArrayList<>(List.of(
                new Part("Brake Pads", 4.0),
                new Part("Brake Fluid", 1.0)
        )));
        workOrder = new WorkOrder(1, "Jane", brakeService);
    }

    // =========================================================================
    // Constructor & accessors
    // =========================================================================
    @Nested
    @DisplayName("Constructor and accessors")
    class ConstructorTests {

        @Test
        @DisplayName("workOrderId() returns the ID supplied to the constructor")
        void workOrderId_returnsConstructorValue() {
            assertEquals(1, workOrder.workOrderId());
        }

        @Test
        @DisplayName("client() returns the client name supplied to the constructor")
        void client_returnsConstructorValue() {
            assertEquals("Jane", workOrder.client());
        }

        @Test
        @DisplayName("serviceJob() returns the ServiceJob supplied to the constructor")
        void serviceJob_returnsConstructorValue() {
            assertSame(brakeService, workOrder.serviceJob());
        }

        @Test
        @DisplayName("Status defaults to PENDING after construction")
        void status_defaultsPending() {
            assertEquals(WorkOrderStatus.PENDING, workOrder.status());
        }

        @Test
        @DisplayName("Different work order IDs are stored correctly")
        void workOrderId_multipleOrders() {
            WorkOrder order2 = new WorkOrder(42, "Peter", brakeService);
            assertEquals(42, order2.workOrderId());
        }
    }

    // =========================================================================
    // updateStatus()
    // =========================================================================
    @Nested
    @DisplayName("updateStatus()")
    class UpdateStatusTests {

        @Test
        @DisplayName("Updates status to IN_PROGRESS")
        void updateStatus_toInProgress() {
            workOrder.updateStatus(WorkOrderStatus.IN_PROGRESS);
            assertEquals(WorkOrderStatus.IN_PROGRESS, workOrder.status());
        }

        @Test
        @DisplayName("Updates status to COMPLETED")
        void updateStatus_toCompleted() {
            workOrder.updateStatus(WorkOrderStatus.COMPLETED);
            assertEquals(WorkOrderStatus.COMPLETED, workOrder.status());
        }

        @Test
        @DisplayName("Status transitions correctly: PENDING → IN_PROGRESS → COMPLETED")
        void updateStatus_fullLifecycle() {
            assertEquals(WorkOrderStatus.PENDING, workOrder.status());
            workOrder.updateStatus(WorkOrderStatus.IN_PROGRESS);
            assertEquals(WorkOrderStatus.IN_PROGRESS, workOrder.status());
            workOrder.updateStatus(WorkOrderStatus.COMPLETED);
            assertEquals(WorkOrderStatus.COMPLETED, workOrder.status());
        }

        @Test
        @DisplayName("Status can be set back to PENDING (no lifecycle enforcement)")
        void updateStatus_backToPending() {
            workOrder.updateStatus(WorkOrderStatus.IN_PROGRESS);
            workOrder.updateStatus(WorkOrderStatus.PENDING);
            assertEquals(WorkOrderStatus.PENDING, workOrder.status());
        }
    }

    // =========================================================================
    // WorkOrderStatus enum
    // =========================================================================
    @Nested
    @DisplayName("WorkOrderStatus enum")
    class WorkOrderStatusEnumTests {

        @Test
        @DisplayName("Enum has exactly three values")
        void enum_hasThreeValues() {
            assertEquals(3, WorkOrderStatus.values().length);
        }

        @Test
        @DisplayName("Enum contains PENDING")
        void enum_containsPending() {
            assertEquals(WorkOrderStatus.PENDING, WorkOrderStatus.valueOf("PENDING"));
        }

        @Test
        @DisplayName("Enum contains IN_PROGRESS")
        void enum_containsInProgress() {
            assertEquals(WorkOrderStatus.IN_PROGRESS, WorkOrderStatus.valueOf("IN_PROGRESS"));
        }

        @Test
        @DisplayName("Enum contains COMPLETED")
        void enum_containsCompleted() {
            assertEquals(WorkOrderStatus.COMPLETED, WorkOrderStatus.valueOf("COMPLETED"));
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Contains the work order ID")
        void toString_containsWorkOrderId() {
            assertTrue(workOrder.toString().contains("1"),
                    "Expected workOrderId '1' in: " + workOrder.toString());
        }

        @Test
        @DisplayName("Contains the client name")
        void toString_containsClientName() {
            assertTrue(workOrder.toString().contains("Jane"),
                    "Expected 'Jane' in: " + workOrder.toString());
        }

        @Test
        @DisplayName("Contains the service job name")
        void toString_containsServiceJobName() {
            assertTrue(workOrder.toString().contains("Full Brake Service"),
                    "Expected 'Full Brake Service' in: " + workOrder.toString());
        }

        @Test
        @DisplayName("Contains the current status")
        void toString_containsStatus() {
            assertTrue(workOrder.toString().contains("PENDING"),
                    "Expected 'PENDING' in: " + workOrder.toString());
        }

        @Test
        @DisplayName("toString reflects updated status")
        void toString_updatedStatus() {
            workOrder.updateStatus(WorkOrderStatus.COMPLETED);
            assertTrue(workOrder.toString().contains("COMPLETED"),
                    "Expected 'COMPLETED' after update in: " + workOrder.toString());
        }
    }
}
