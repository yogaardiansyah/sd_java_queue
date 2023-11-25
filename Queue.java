import java.util.Scanner;

public class Queue {
    static int MAX;          // Maksimum panjang Queue
    static int[] queueArr;   // Array untuk menyimpan elemen-elemen Queue
    static int rear = -1;    // Indeks untuk elemen terakhir di Queue
    static int front = -1;   // Indeks untuk elemen pertama di Queue
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        initializeQueue();   // Inisialisasi Queue

        int choice;
        while (true) {
            showMenu();      // Tampilkan menu operasi pada Queue

            if (scanner.hasNextInt()) {
                choice = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline dari buffer
            } else {
                handleInvalidInput();
                continue; // Restart the loop
            }

            switch (choice) {
                case 1:
                    insert();    // Panggil metode untuk operasi insert
                    break;
                case 2:
                    display();   // Panggil metode untuk operasi display
                    break;
                case 3:
                    delete();    // Panggil metode untuk operasi delete
                    break;
                case 4:
                    closeScanner(); // Panggil metode untuk menutup Scanner dan keluar dari program
                    System.exit(0);
                default:
                    handleInvalidChoice();
                    break;
            }
        }
    }

    static void initializeQueue() {
        System.out.print("Masukkan panjang nilai Queue: ");
        while (true) {
            if (scanner.hasNextInt()) {
                MAX = scanner.nextInt();
                if (MAX > 0) {
                    break;
                } else {
                    handleInvalidInput("Panjang nilai Queue harus lebih besar dari 0.");
                }
            } else {
                handleInvalidInput();
            }
        }
        scanner.nextLine(); // Membersihkan newline dari buffer

        queueArr = new int[MAX]; // Inisialisasi array dengan panjang MAX
    }

    static void showMenu() {
        System.out.println("\n+==================+ ");
        System.out.println("|    Menu Queue    | ");
        System.out.println("+==================+ ");
        System.out.println("| 1. Insert Queue  | ");
        System.out.println("| 2. Display Queue | ");
        System.out.println("| 3. Delete Queue  | ");
        System.out.println("| 4. Quit          | ");
        System.out.println("+==================+ ");
        System.out.print("Masukkan Pilihan : ");
    }

    static void insert() {
        int addedItem;
        if (isFull()) {
            System.out.println("Queue Overflow! Tidak bisa memasukan lagi.");
        } else {
            System.out.print("Masukkan Elemen Kedalam Queue : ");
            if (scanner.hasNextInt()) {
                addedItem = scanner.nextInt();
                scanner.nextLine(); // Membersihkan newline dari buffer

                if (front == -1) {
                    front = 0;
                }

                rear = (rear + 1) % MAX;
                queueArr[rear] = addedItem;
                System.out.println(
                        "Elemen dimasukan adalah : " + addedItem + ". Queue Front: " + front + ", Rear: " + rear);
            } else {
                handleInvalidInput();
            }
        }
    }

    static void delete() {
        if (isEmpty()) {
            System.out.println("Queue Underflow! Nilai Queue kosong");
        } else {
            System.out.println("Element dihapus dari Queue adalah : " + queueArr[front]);

            if (front == rear) {
                front = -1;
                rear = -1;
            } else {
                front = (front + 1) % MAX;
            }

            System.out.println("Setelah dihapus, Queue : Front: " + front + ", Rear: " + rear);
        }
    }

    static void display() {
        if (isEmpty()) {
            System.out.println("Queue Kosong !");
        } else {
            System.out.println("Queue : ");
            int index = front;
            do {
                System.out.println(queueArr[index]);
                index = (index + 1) % MAX;
            } while (index != (rear + 1) % MAX);
        }
    }

    static boolean isFull() {
        return (rear + 1) % MAX == front;
    }

    static boolean isEmpty() {
        return front == -1;
    }

    static void closeScanner() {
        scanner.close();
        System.out.println("Program Selesai.");
        System.out.println("Dibuat oleh Yoga Ardiansyah 2IA25 51422643.");
    }

    // Metode untuk menangani input yang tidak valid
    static void handleInvalidInput() {
        System.out.println("Input Harus Integer");
        scanner.nextLine(); // Membersihkan newline dari buffer
    }

    // Metode untuk menangani pilihan menu yang tidak valid
    static void handleInvalidChoice() {
        System.out.println("Pilihan tidak valid. Silakan coba lagi.");
    }

    // Metode untuk menangani input yang tidak valid dengan pesan khusus
    static void handleInvalidInput(String message) {
        System.out.println(message);
        scanner.nextLine(); // Membersihkan newline dari buffer
    }
}
