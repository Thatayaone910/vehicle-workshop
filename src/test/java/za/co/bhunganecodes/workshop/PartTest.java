package za.co.bhunganecodes.workshop;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import za.co.bhunganecodes.workshop.model.Part;

import static org.junit.jupiter.api.Assertions.*;

/**
 * TDD tests for {@link Part}.
 *
 * Run with: mvn clean test -Dtest=PartTest
 */
@DisplayName("Part")
class PartTest {

    private Part brakePads;
    private Part engineOil;

    @BeforeEach
    void setUp() {
        brakePads = new Part("Brake Pads", 4.0);
        engineOil = new Part("Engine Oil", 5.0);
    }

    // =========================================================================
    // Constructor & accessors
    // =========================================================================
    @Nested
    @DisplayName("Constructor and accessors")
    class ConstructorTests {

        @Test
        @DisplayName("name() returns the name supplied to the constructor")
        void name_returnsConstructorValue() {
            assertEquals("Brake Pads", brakePads.name());
        }

        @Test
        @DisplayName("quantity() returns the quantity supplied to the constructor")
        void quantity_returnsConstructorValue() {
            assertEquals(4.0, brakePads.quantity(), 0.001);
        }

        @Test
        @DisplayName("Constructor stores name correctly for a second part")
        void name_secondPart() {
            assertEquals("Engine Oil", engineOil.name());
        }

        @Test
        @DisplayName("Constructor stores quantity correctly for a second part")
        void quantity_secondPart() {
            assertEquals(5.0, engineOil.quantity(), 0.001);
        }

        @Test
        @DisplayName("Zero quantity is valid and stored correctly")
        void quantity_zeroIsValid() {
            Part spare = new Part("Spare Fuse", 0.0);
            assertEquals(0.0, spare.quantity(), 0.001);
        }
    }

    // =========================================================================
    // updateQuantity
    // =========================================================================
    @Nested
    @DisplayName("updateQuantity()")
    class UpdateQuantityTests {

        @Test
        @DisplayName("Updates quantity to a positive value")
        void updateQuantity_positive() {
            brakePads.updateQuantity(8.0);
            assertEquals(8.0, brakePads.quantity(), 0.001);
        }

        @Test
        @DisplayName("Updates quantity to zero (boundary)")
        void updateQuantity_zero() {
            brakePads.updateQuantity(0.0);
            assertEquals(0.0, brakePads.quantity(), 0.001);
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for a negative quantity")
        void updateQuantity_negative_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> brakePads.updateQuantity(-1.0));
        }

        @Test
        @DisplayName("Throws IllegalArgumentException for any negative value, not just -1")
        void updateQuantity_largeNegative_throwsException() {
            assertThrows(IllegalArgumentException.class,
                    () -> brakePads.updateQuantity(-999.99));
        }

        @Test
        @DisplayName("Quantity is unchanged after a failed update")
        void updateQuantity_negative_doesNotChangeQuantity() {
            double original = brakePads.quantity();
            try {
                brakePads.updateQuantity(-5.0);
            } catch (IllegalArgumentException ignored) {}
            assertEquals(original, brakePads.quantity(), 0.001);
        }
    }

    // =========================================================================
    // toString
    // =========================================================================
    @Nested
    @DisplayName("toString()")
    class ToStringTests {

        @Test
        @DisplayName("Returns '<name>: <quantity> units' format")
        void toString_correctFormat() {
            assertEquals("Brake Pads: 4.0 units", brakePads.toString());
        }

        @Test
        @DisplayName("toString reflects updated quantity")
        void toString_afterUpdate() {
            brakePads.updateQuantity(8.0);
            assertEquals("Brake Pads: 8.0 units", brakePads.toString());
        }

        @Test
        @DisplayName("Works for Engine Oil part")
        void toString_engineOil() {
            assertEquals("Engine Oil: 5.0 units", engineOil.toString());
        }
    }
}
