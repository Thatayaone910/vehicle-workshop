package za.co.bhunganecodes.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.workshop.model.Part;
import za.co.bhunganecodes.workshop.model.ServiceJob;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link ServiceJob}.
 *
 * Run with: mvn clean test -Dtest=ServiceJobTest
 */
@DisplayName("ServiceJob")
class ServiceJobTest {

    private Part brakePads;
    private Part brakeFluid;
    private ServiceJob brakeService;

    @BeforeEach
    void setUp() {
        brakePads   = new Part("Brake Pads", 4.0);
        brakeFluid  = new Part("Brake Fluid", 1.0);
        brakeService = new ServiceJob("Full Brake Service",
                new ArrayList<>(List.of(brakePads, brakeFluid)));
    }

    // =========================================================================
    // Constructor & name()
    // =========================================================================
    @Nested
    @DisplayName("Constructor and name()")
    class ConstructorTests {

        @Test
        @DisplayName("name() returns the name supplied to the constructor")
        void name_returnsConstructorValue() {
            assertEquals("Full Brake Service", brakeService.name());
        }

        @Test
        @DisplayName("Constructs correctly with an empty parts list")
        void constructor_emptyList() {
            ServiceJob empty = new ServiceJob("Inspection Only", new ArrayList<>());
            assertEquals("Inspection Only", empty.name());
            assertTrue(empty.parts().isEmpty());
        }
    }

    // =========================================================================
    // parts() — defensive copy
    // =========================================================================
    @Nested
    @DisplayName("parts() — defensive copy")
    class PartsTests {

        @Test
        @DisplayName("Returns all parts initially provided")
        void parts_containsAll() {
            List<Part> result = brakeService.parts();
            assertEquals(2, result.size());
        }

        @Test
        @DisplayName("Returned list contains the correct part names")
        void parts_correctNames() {
            List<String> names = brakeService.parts()
                    .stream()
                    .map(Part::name)
                    .toList();
            assertTrue(names.contains("Brake Pads"));
            assertTrue(names.contains("Brake Fluid"));
        }

        @Test
        @DisplayName("Modifying the returned list does NOT affect the internal list")
        void parts_defensiveCopy_externalMutationIgnored() {
            List<Part> copy = brakeService.parts();
            copy.add(new Part("Caliper", 2.0));
            // Internal list should still have only 2 parts
            assertEquals(2, brakeService.parts().size());
        }

        @Test
        @DisplayName("Mutating the original constructor list does NOT affect internal list")
        void parts_defensiveCopy_constructorListMutationIgnored() {
            List<Part> originalList = new ArrayList<>(List.of(brakePads));
            ServiceJob job = new ServiceJob("Partial Brake Job", originalList);
            originalList.add(brakeFluid); // mutate the list passed in
            // Internal list should still have only 1 part
            assertEquals(1, job.parts().size());
        }
    }

    // =========================================================================
    // addPart()
    // =========================================================================
    @Nested
    @DisplayName("addPart()")
    class AddPartTests {

        @Test
        @DisplayName("Increases part count by one")
        void addPart_increasesCount() {
            Part rotor = new Part("Brake Rotor", 2.0);
            brakeService.addPart(rotor);
            assertEquals(3, brakeService.parts().size());
        }

        @Test
        @DisplayName("Added part is present in the list")
        void addPart_partIsPresent() {
            Part caliper = new Part("Brake Caliper", 2.0);
            brakeService.addPart(caliper);
            boolean found = brakeService.parts().stream()
                    .anyMatch(p -> p.name().equals("Brake Caliper"));
            assertTrue(found);
        }

        @Test
        @DisplayName("Can add multiple parts sequentially")
        void addPart_multipleAdds() {
            brakeService.addPart(new Part("Brake Rotor", 2.0));
            brakeService.addPart(new Part("Wheel Bearing", 4.0));
            assertEquals(4, brakeService.parts().size());
        }
    }

    // =========================================================================
    // toString()
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Starts with the service job name")
        void toString_startsWithName() {
            assertTrue(brakeService.toString().startsWith("Full Brake Service"));
        }

        @Test
        @DisplayName("Contains each part's toString representation")
        void toString_containsParts() {
            String result = brakeService.toString();
            assertTrue(result.contains("Brake Pads: 4.0 units"),
                    "Expected 'Brake Pads: 4.0 units' in: " + result);
            assertTrue(result.contains("Brake Fluid: 1.0 units"),
                    "Expected 'Brake Fluid: 1.0 units' in: " + result);
        }

        @Test
        @DisplayName("Each part appears on its own line after the job name")
        void toString_multiLineFormat() {
            String result = brakeService.toString();
            String[] lines = result.split("\n");
            // First line is the job name; subsequent lines are parts
            assertTrue(lines.length >= 3,
                    "Expected at least 3 lines (name + 2 parts), got: " + lines.length);
        }
    }
}
