import java.util.*;

// Node class for a dynamic stack
class StackNode {
    String name;
    String mobileNumber;
    String email;
    String city;
    int age;
    StackNode next;

    public StackNode(String name, String mobileNumber, String email, String city, int age) {
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.email = email;
        this.city = city;
        this.age = age;
        this.next = null;
    }
}

// Stack class for storing customer details
class CustomerStack {
    StackNode top;

    public CustomerStack() {
        top = null;
    }

    void push(String name, String mobileNumber, String email, String city, int age) {
        StackNode newNode = new StackNode(name, mobileNumber, email, city, age);
        newNode.next = top;
        top = newNode;
    }

    StackNode pop() {
        if (top == null) {
            return null;
        }
        StackNode temp = top;
        top = top.next;
        return temp;
    }

    boolean isEmpty() {
        return top == null;
    }

    void displayAll() {
        StackNode temp = top;
        while (temp != null) {
            System.out.println("Name: " + temp.name + ", Mobile: " + temp.mobileNumber + ", Email: " + temp.email + ", City: " + temp.city + ", Age: " + temp.age);
            temp = temp.next;
        }
    }

    // Method to get an array of ages from the stack
    public int[] getAges() {
        StackNode temp = top;
        int size = 0;
        while (temp != null) {
            size++;
            temp = temp.next;
        }

        int[] ages = new int[size];
        temp = top;
        int index = 0;
        while (temp != null) {
            ages[index++] = temp.age;
            temp = temp.next;
        }
        return ages;
    }

    // Method to display sorted customers by age using Bubble Sort and Selection Sort
    public void displaySortedByAge() {
        int[] ages = getAges();
        int[] bubbleSortAges = Arrays.copyOf(ages, ages.length);
        int[] selectionSortAges = Arrays.copyOf(ages, ages.length);

        BubbleSort bubbleSort = new BubbleSort();
        SelectionSort selectionSort = new SelectionSort();

        System.out.println("-------------------------------------------------");
        System.out.println("Sorting ages using Bubble Sort:");
        bubbleSort.bubbleSort(bubbleSortAges);
        bubbleSort.printArray(bubbleSortAges);
        System.out.println("-------------------------------------------------");
        System.out.println("Sorting ages using Selection Sort:");
        selectionSort.selectionSort(selectionSortAges);
        selectionSort.printArray(selectionSortAges);
        System.out.println("-------------------------------------------------");

        System.out.println("Displaying Customers Sorted by Age (Bubble Sort):");
        for (int age : bubbleSortAges) {
            StackNode node = findCustomerByAge(age);
            if (node != null) {
                System.out.println("Name: " + node.name + ", Mobile: " + node.mobileNumber + ", Email: " + node.email + ", City: " + node.city + ", Age: " + node.age);
                
            }
        }

    }

    // Method to find a customer by age
    private StackNode findCustomerByAge(int age) {
        StackNode temp = top;
        while (temp != null) {
            if (temp.age == age) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
        
    }
}

// Node class for a dynamic queue
class QueueNode {
    String busNumber;
    int totalSeats;
    int availableSeats;
    int[] seatNumbers;
    Set<Integer> reservedSeats; // To keep track of reserved seats
    String startingPoint;
    String endingPoint;
    String startingTime;
    double fare;
    QueueNode next;

    // Constructor to initialize a new queue node
    public QueueNode(String busNumber, int totalSeats, int[] seatNumbers, String startingPoint, String endingPoint, String startingTime, double fare) {
        this.busNumber = busNumber;
        this.totalSeats = totalSeats;
        this.availableSeats = totalSeats;
        this.seatNumbers = seatNumbers;
        this.reservedSeats = new HashSet<>();
        this.startingPoint = startingPoint;
        this.endingPoint = endingPoint;
        this.startingTime = startingTime;
        this.fare = fare;
        this.next = null;
    }
}

// Queue class for storing bus details
class BusQueue {
    QueueNode front, rear;

    // Constructor to initialize an empty queue
    public BusQueue() {
        front = rear = null;
    }

    // Method to enqueue a new bus to the queue
    void enqueue(String busNumber, int totalSeats, String startingPoint, String endingPoint, String startingTime, double fare) {
        int[] seatNumbers = new int[totalSeats];
        for (int i = 0; i < totalSeats; i++) {
            seatNumbers[i] = i + 1;
        }
        QueueNode newNode = new QueueNode(busNumber, totalSeats, seatNumbers, startingPoint, endingPoint, startingTime, fare);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    // Method to display all buses in the queue
    void displayAll() {
        QueueNode temp = front;
        while (temp != null) {
            System.out.print("Bus Number: " + temp.busNumber + ", Total Seats: " + temp.totalSeats + ", Available Seats: " + temp.availableSeats + ", Fare: " + temp.fare + ", Starting Point: " + temp.startingPoint + ", Ending Point: " + temp.endingPoint + ", Starting Time: " + temp.startingTime + ", Seat Numbers: ");
            for (int seat : temp.seatNumbers) {
                System.out.print(seat + " ");
            }
            System.out.println();
            temp = temp.next;
        }
    }

    // Method to search for a bus by its number
    QueueNode search(String busNumber) {
        QueueNode temp = front;
        while (temp != null) {
            if (temp.busNumber.equals(busNumber)) {
                return temp;
            }
            temp = temp.next;
        }
        return null;
    }
}

// Node class for a dynamic queue to handle seat reservations
class ReservationQueueNode {
    String customerName;
    String busNumber;
    int seatNumber;
    ReservationQueueNode next;

    public ReservationQueueNode(String customerName, String busNumber, int seatNumber) {
        this.customerName = customerName;
        this.busNumber = busNumber;
        this.seatNumber = seatNumber;
        this.next = null;
    }
}

// Queue class for handling seat reservations
class ReservationQueue {
    ReservationQueueNode front, rear;

    public ReservationQueue() {
        front = rear = null;
    }

    void enqueue(String customerName, String busNumber, int seatNumber) {
        ReservationQueueNode newNode = new ReservationQueueNode(customerName, busNumber, seatNumber);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    ReservationQueueNode dequeue() {
        if (front == null) {
            return null;
        }
        ReservationQueueNode temp = front;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return temp;
    }

    boolean isEmpty() {
        return front == null;
    }

    void displayAll() {
        ReservationQueueNode temp = front;
        while (temp != null) {
            System.out.println("Customer: " + temp.customerName + ", Bus Number: " + temp.busNumber + ", Seat Number: " + temp.seatNumber);
            temp = temp.next;
        }
    }
}

// Bubble Sort class
class BubbleSort {
    public void bubbleSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            for (int j = 0; j < n - i - 1; j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                }
            }
        }
    }

    public void printArray(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

// Selection Sort class
class SelectionSort {
    public void selectionSort(int arr[]) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) {
                    minIdx = j;
                }
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public void printArray(int arr[]) {
        for (int i : arr) {
            System.out.print(i + " ");
        }
        System.out.println();
    }
}

// Main class to run the bus reservation system
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CustomerStack customerStack = new CustomerStack();
        BusQueue busQueue = new BusQueue();
        ReservationQueue reservationQueue = new ReservationQueue();

        while (true) {
            System.out.println("                            ");
            System.out.println("----------------------------");
            System.out.println("   Bus Reservation System   ");
            System.out.println("----------------------------");
            System.out.println("                            ");
            System.out.println("1. Register Customer");
            System.out.println("2. Register Bus");
            System.out.println("3. Search Bus");
            System.out.println("4. Reserve Seat");
            System.out.println("5. Cancel Reservation");
            System.out.println("6. Display All Reservations");
            System.out.println("7. Display All Buses");
            System.out.println("8. Display All Customers");
            System.out.println("9. Display Sorted Customers by Age");
            System.out.println("10. Exit");
            System.out.println("                            ");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                System.out.print("Enter name: ");
                String name = scanner.nextLine();

                String mobileNumber;
                while (true) {
                    System.out.print("Enter mobile number: ");
                    mobileNumber = scanner.nextLine();
                    if (mobileNumber.matches("\\d{10}")) {
                        break;
                    } else {
                        System.out.println("Invalid mobile number. Please enter a valid contact number !");
                    }
                }

                String email;
                while (true) {
                    System.out.print("Enter email: ");
                    email = scanner.nextLine();
                    if (email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
                        break;
                    } else {
                        System.out.println("Invalid email. Please enter a valid email !");
                    }
                }

                System.out.print("Enter city: ");
                String city = scanner.nextLine();

                int age;
                while (true) {
                    System.out.print("Enter age: ");
                    age = scanner.nextInt();
                    if (age > 17 && age <= 100) {
                        break;
                    } else {
                        System.out.println("Invalid age. Please enter a valid age (18+ and 100-) !");
                    }
                }

                

                customerStack.push(name, mobileNumber, email, city, age);
                System.out.println("-------------------------------------------------");
                System.out.println("Customer registered successfully.");
                System.out.println("-------------------------------------------------");
                
                break;

  


                case 2:
                System.out.print("Enter bus number: ");
                String busNumber = scanner.nextLine();

                int totalSeats;
                while (true) {
                    System.out.print("Enter total seats: ");
                    totalSeats = scanner.nextInt();
                    if (totalSeats > 0) {
                        break;
                    } else {
                        System.out.println("Invalid number of seats. Please enter valid seat count !");
                    }
                }

                scanner.nextLine(); // Consume newline
                System.out.print("Enter starting point: ");
                String startingPoint = scanner.nextLine();

                System.out.print("Enter ending point: ");
                String endingPoint = scanner.nextLine();

                System.out.print("Enter starting time: ");
                String startingTime = scanner.nextLine();

                double fare;
                while (true) {
                    System.out.print("Enter fare: ");
                    fare = scanner.nextDouble();
                    if (fare >= 0) {
                        break;
                    } else {
                        System.out.println("Invalid fare. Please enter valid amount !");
                    }
                }

                busQueue.enqueue(busNumber, totalSeats, startingPoint, endingPoint, startingTime, fare);
                System.out.println("-------------------------------------------------");
                System.out.println("Bus added successfully.");
                System.out.println("-------------------------------------------------");
                break;

                

                case 3:
                    // Search for a bus by its number
                    System.out.print("Enter Bus Number to Search: ");
                    String searchBusNumber = scanner.nextLine();
                    QueueNode bus = busQueue.search(searchBusNumber);
                    if (bus != null) {
                        System.out.print("Bus Number: " + bus.busNumber + ", Total Seats: " + bus.totalSeats + ", Available Seats: " + bus.availableSeats + ", Fare: " + bus.fare + ", Starting Point: " + bus.startingPoint + ", Ending Point: " + bus.endingPoint + ", Starting Time: " + bus.startingTime + ", Reserved Seats: ");
                        for (int i = 0; i < bus.totalSeats; i++) {
                            if (bus.reservedSeats.contains(i + 1)) {
                                System.out.print((i + 1) + " ");
                            }
                        }
                        System.out.println();
                    } else {
                        System.out.println("Bus Not Found!");
                    }
                    break;

                case 4:
                 // Reserve a seat for a customer
                 System.out.print("Enter Customer Name for Reservation: ");
                 String resCustomerName = scanner.nextLine();
                 System.out.print("Enter Bus Number for Reservation: ");
                 String resBusNumber = scanner.nextLine();
                 System.out.print("Enter Seat Number for Reservation: ");
                 int resSeatNumber = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
                 QueueNode resBus = busQueue.search(resBusNumber);
                 if (resBus != null && resBus.availableSeats > 0 && resSeatNumber <= resBus.totalSeats && !resBus.reservedSeats.contains(resSeatNumber)) {
                     reservationQueue.enqueue(resCustomerName, resBusNumber, resSeatNumber);
                     resBus.reservedSeats.add(resSeatNumber);
                     resBus.availableSeats--;
                     System.out.println("-------------------------------------------------");
                     System.out.println("Reservation Made Successfully for Seat Number: " + resSeatNumber);
                     System.out.println("-------------------------------------------------");
                 } else {
                     System.out.println("-------------------------------------------------");
                     System.out.println("Bus Not Found, Seat Not Available, or Seat Already Reserved!");
                     System.out.println("-------------------------------------------------");
                     
                    

                 }
                 break;

                 case 5:
                 // Cancel a reservation
                 System.out.print("Enter Bus Number for Cancellation: ");
                 String cancelBusNumber = scanner.nextLine();
                 System.out.print("Enter Customer Name for Cancellation: ");
                 String cancelCustomerName = scanner.nextLine();
                 System.out.print("Enter Seat Number for Cancellation: ");
                 int cancelSeatNumber = scanner.nextInt();
                 scanner.nextLine(); // Consume newline
             
                 QueueNode cancelBus = busQueue.search(cancelBusNumber);
                 // Search for the customer in the reservation queue
                 ReservationQueueNode prevNode = null;
                 ReservationQueueNode currentNode = reservationQueue.front;
                 boolean reservationFound = false;
                 while (currentNode != null) {
                     if (currentNode.busNumber.equals(cancelBusNumber) &&
                         currentNode.customerName.equals(cancelCustomerName) &&
                         currentNode.seatNumber == cancelSeatNumber) {
             
                         // Remove the reservation node from the queue
                         if (prevNode == null) {
                             reservationQueue.front = currentNode.next; // Dequeue the front node
                         } else {
                             prevNode.next = currentNode.next;
                         }
                         
                         if (currentNode == reservationQueue.rear) {
                             reservationQueue.rear = prevNode; // Update the rear if necessary
                         }
             
                         reservationFound = true;
                         break;
                     }
                     prevNode = currentNode;
                     currentNode = currentNode.next;
                 }
             
                 if (cancelBus != null && reservationFound) {
                     cancelBus.reservedSeats.remove(cancelSeatNumber);
                     cancelBus.availableSeats++;
                     System.out.println("-------------------------------------------------");
                     System.out.println("Reservation Cancelled Successfully for Seat Number: " + cancelSeatNumber);
                     System.out.println("-------------------------------------------------");
             
                     // Notify the next customer waiting for the same seat
                     if (!reservationQueue.isEmpty()) {
                         ReservationQueueNode nextCustomer = reservationQueue.front;
                         if (nextCustomer.seatNumber == cancelSeatNumber && nextCustomer.busNumber.equals(cancelBusNumber)) {
                             System.out.println("Next Customer: " + nextCustomer.customerName + " has been notified about the available seat.");
                             System.out.println("-------------------------------------------------");
                         }
                     }
                 } else {
                     System.out.println("-------------------------------------------------");
                     System.out.println("Reservation Not Found or Seat Number Invalid!");
                     System.out.println("-------------------------------------------------");
                 }
                 break;
             

                case 6:
                    // Display all reservations
                    System.out.println("-------------------------------------------------");
                    System.out.println("Displaying All Reservations:");
                    reservationQueue.displayAll();
                    System.out.println("-------------------------------------------------");
                    break;

                case 7:
                    // Display all buses
                    System.out.println("-------------------------------------------------");
                    System.out.println("Displaying All Buses:");
                    busQueue.displayAll();
                    System.out.println("-------------------------------------------------");
                    break;

                case 8:
                    // Display all customers
                    System.out.println("-------------------------------------------------");
                    System.out.println("Displaying All Customers:");
                    customerStack.displayAll();
                    System.out.println("-------------------------------------------------");
                    break;

                case 9:
                    // Display sorted customers by age
                    System.out.println("-------------------------------------------------");
                    System.out.println("Displaying Sorted Customers by Age:");
                    customerStack.displaySortedByAge();
                    System.out.println("-------------------------------------------------");
                    break;

                case 10:
                    // Exit the program
                    System.out.println("-------------------------------------------------");
                    System.out.println("Exiting...");
                    scanner.close();
                    System.exit(0);
                    System.out.println("-------------------------------------------------");
                    break;

                default:
                    // Handle invalid choices
                    System.out.println("-------------------------------------------------");
                    System.out.println("Invalid Choice! Please Try Again.");
                    System.out.println("-------------------------------------------------");
            }
        }
    }
}
