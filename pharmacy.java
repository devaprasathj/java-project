import java.util.ArrayList;
import java.util.Scanner;

class Medicine {
    String name;
    double price;
    int quantity;

    Medicine(String name, double price, int quantity) {
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}

class Sale {
    String customerName;
    String medicineName;
    double price;
    int quantity;
    double subtotal;

    Sale(String customerName, String medicineName, double price, int quantity, double subtotal) {
        this.customerName = customerName;
        this.medicineName = medicineName;
        this.price = price;
        this.quantity = quantity;
        this.subtotal = subtotal;
    }
}

public class pharmacy{
    static ArrayList<Medicine> medicines = new ArrayList<>();
    static ArrayList<Sale> sales = new ArrayList<>(); // List to track sales
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        while (true) {
            System.out.println("\nPharmacy Management System");
            System.out.println("1. Add Medicine");
            System.out.println("2. Sell Medicine");
            System.out.println("3. Generate Sales Receipt");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1 -> addMedicine();
                case 2 -> sellMedicine();
                case 3 -> generateSalesReceipt();
                case 4 -> {
                    System.out.println("Thank You");
                    return;
                }
                default -> System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void addMedicine() {
        System.out.print("Enter Medicine Name: ");
        scanner.nextLine(); // Consume newline
        String name = scanner.nextLine();
        System.out.print("Enter Medicine Price: ");
        double price = scanner.nextDouble();
        System.out.print("Enter Medicine Quantity: ");
        int quantity = scanner.nextInt();
        medicines.add(new Medicine(name, price, quantity));
        System.out.println("Medicine added successfully!");

        // Display medicines in rows
        System.out.println("\nCurrent Medicines:");
        System.out.println("Name\t\tPrice\tQuantity");
        System.out.println("---------------------------------------");
        for (Medicine med : medicines) {
            System.out.printf("%-10s\t%.2f\t%d\n", med.name, med.price, med.quantity);
        }
    }

    static void sellMedicine() {
        System.out.print("Enter Customer Name: ");
        scanner.nextLine(); // Consume newline
        String customerName = scanner.nextLine();
        System.out.print("Enter Medicine Name to sell: ");
        String name = scanner.nextLine();
        System.out.print("Enter Quantity to sell: ");
        int quantity = scanner.nextInt();
        for (Medicine med : medicines) {
            if (med.name.equalsIgnoreCase(name)) {
                if (med.quantity >= quantity) {
                    med.quantity -= quantity;
                    double subtotal = med.price * quantity;
                    sales.add(new Sale(customerName, med.name, med.price, quantity, subtotal));
                    System.out.println("Medicine sold successfully!");
                } else {
                    System.out.println("Insufficient stock!");
                }
                return;
            }
        }
        System.out.println("Medicine not found!");
    }

    static void generateSalesReceipt() {
        if (sales.isEmpty()) {
            System.out.println("No sales have been made.");
            return;
        }

        System.out.println("Sales Receipt:");
        System.out.println("Customer Name\tMedicine Name\tPrice\tQuantity\tSubtotal");
        System.out.println("---------------------------------------------------------------");
        for (Sale sale : sales) {
            System.out.printf("%-15s\t%-15s\t%.2f\t%d\t\t%.2f\n", sale.customerName, sale.medicineName, sale.price, sale.quantity, sale.subtotal);
        }
    }
}