package com.alex.isthisevenabill;

import com.alex.isthisevenabill.services.insurance.models.ServiceCost;
import com.alex.isthisevenabill.services.insurance.models.ServiceType;
import com.alex.isthisevenabill.services.insurance.plans.PPOHealthPlan;
import com.alex.isthisevenabill.services.insurance.services.ServiceCostCalculator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class IsThisActuallyABillApplicationTests {

    private PPOHealthPlan healthPlan;

    @BeforeEach
    void setup() {
        // Set up a basic PPO Health Plan for testing
        healthPlan = new PPOHealthPlan(7000.0, 1500.0); // OOP Max = $7000, Deductible = $1500

        // Add basic service costs for testing purposes
        healthPlan.addServiceCost(ServiceType.PCP, new ServiceCost(20.0, null, false)); // Copay for PCP
        healthPlan.addServiceCost(ServiceType.ER, new ServiceCost(150.0, 0.2, true));  // ER subject to deductible
    }

    // Context Load Test (Default Test)
    @Test
    void contextLoads() {
        assertNotNull(healthPlan, "HealthPlan should not be null when context is loaded.");
    }

    // Test: PCP Visit Cost Calculation (Copay)
    @Test
    void testPCPVisitCost() {
        Double cost = healthPlan.calculateOutOfPocketCost(ServiceType.PCP, 200.0);
        assertEquals(20.0, cost, "PCP visit should only incur a copay of $20.");
    }

    // Test: ER Visit with Full Deductible Remaining
    @Test
    void testERVisitFullDeductible() {
        Double cost = healthPlan.calculateOutOfPocketCost(ServiceType.ER, 1000.0);
        assertEquals(1000.0, cost, "ER visit should charge full cost towards deductible.");
    }

    // Test: ER Visit with Deductible Already Met (Coinsurance)
    @Test
    void testERVisitPartialDeductible() {
        // Test implementation...
    }

    // Test: Invalid Service Type
    @Test
    void testInvalidServiceType() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            healthPlan.calculateOutOfPocketCost(ServiceType.DRUG_TIER_1, 100.0);
        });

        String expectedMessage = "Service type not covered under this plan.";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage), "Exception should indicate service type is not covered.");
    }

    // Test: ServiceCostCalculator Basic Calculation (without deductibles)
    @Test
    void testServiceCostCalculatorWithoutDeductible() {
        ServiceCost serviceCost = new ServiceCost(20.0, null, false); // No deductible, $20 copay
        Double cost = ServiceCostCalculator.calculateOutOfPocket(serviceCost, 100.0, 1500.0, 0.0, 7000.0);
        assertEquals(20.0, cost, "Service should only charge the copay if it does not apply to deductible.");
    }

    // Test: ServiceCostCalculator with Deductible Remaining
    @Test
    void testServiceCostCalculatorWithDeductibleRemaining() {
        ServiceCost serviceCost = new ServiceCost(null, 0.2, true); // 20% coinsurance after deductible
        Double cost = ServiceCostCalculator.calculateOutOfPocket(serviceCost, 1000.0, 1500.0, 500.0, 7000.0); // $500 spent towards deductible

        Double expectedCost = 1000.0 - 500.0; // Full charge towards remaining deductible
        assertEquals(expectedCost, cost, "Service should charge full amount towards deductible.");
    }

    // Test: ServiceCostCalculator with Deductible Met
    @Test
    void testServiceCostCalculatorWithDeductibleMet() {
        ServiceCost serviceCost = new ServiceCost(null, 0.2, true); // 20% coinsurance after deductible
        Double cost = ServiceCostCalculator.calculateOutOfPocket(serviceCost, 1000.0, 1500.0, 1500.0, 7000.0); // Deductible fully met

        Double expectedCost = 1000.0 * 0.2; // 20% coinsurance
        assertEquals(expectedCost, cost, "Once deductible is met, the service should apply coinsurance.");
    }

    // Test: ServiceCostCalculator with OOP Max Reached
    @Test
    void testServiceCostCalculatorWithOOPMaxReached() {
        ServiceCost serviceCost = new ServiceCost(null, 0.2, true);
        Double cost = ServiceCostCalculator.calculateOutOfPocket(serviceCost, 5000.0, 1500.0, 7000.0, 7000.0); // OOP Max reached

        assertEquals(0.0, cost, "No additional cost should be applied when OOP max is reached.");
    }
}
