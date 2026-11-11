
/**
 * Generates a menu of every possible hot dog topping
 * combination and calculates its price.
 */
public class HotDog {

    /**
     * Prints all possible hot dog combinations.
     *
     * @return the total number of combinations
     */
    public long printMenu() {

        // Store the available toppings.
        String[] toppings = {
            "Ketchup",
            "Mustard",
            "Chopped Onions",
            "Tomato Wedge",
            "Sweet Relish",
            "Sport Peppers",
            "Sauerkraut"
        };

        // Store the topping prices in cents.
        int[] prices = {15, 17, 20, 25, 30, 35, 45};

        // Set the base price and combination count.
        int basePrice = 99;
        long count = 0;

        // Calculate the total number of combinations.
        int total = 1 << toppings.length;

        // Process combinations by number of toppings.
        for (int numberOfToppings = 0;
             numberOfToppings <= toppings.length;
             numberOfToppings++) {

            // Check every possible combination.
            for (int combination = 0;
                 combination < total;
                 combination++) {

                // Count the selected toppings.
                if (Integer.bitCount(combination)
                        != numberOfToppings) {
                    continue;
                }

                // Begin with the basic hot dog.
                String description = "Dog in Bun";
                int cost = basePrice;
                boolean first = true;

                // Examine each topping.
                for (int i = 0; i < toppings.length; i++) {

                    // Check whether this topping is selected.
                    if ((combination & (1 << i)) != 0) {

                        // Add the appropriate separator.
                        if (first) {
                            description += " with ";
                            first = false;
                        } else {
                            description += ", ";
                        }

                        // Add the topping and its price.
                        description += toppings[i];
                        cost += prices[i];
                    }
                }

                // Print the menu entry and formatted price.
                System.out.printf(
                    "%3d %-80s $%.2f%n",
                    count,
                    description,
                    cost / 100.0
                );

                // Increase the combination count.
                count++;
            }
        }

        // Return the number of combinations.
        return count;
    }

    /**
     * Starts the hot dog menu program.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {

        // Create the HotDog object.
        HotDog obj = new HotDog();

        // Generate the menu.
        long count = obj.printMenu();

        // Verify the expected number of combinations.
        assert count == 128;
        System.out.println("Total combinations: " + count);
    }
}
