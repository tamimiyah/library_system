import java.io.*;
import java.util.*;

public class BorrowBorrows {

    
    static class Borrower {
        String name;
        int strikes;
        String borrowedMaterial;

        public Borrower(String name, int strikes, String borrowedMaterial) {
            this.name = name;
            this.strikes = strikes;
            this.borrowedMaterial = borrowedMaterial;
        }

        public boolean canBorrow() {
            return strikes < 3 && borrowedMaterial.equals("");
        }

        public void incrementStrikes() {
            strikes++;
        }
    }

   
    abstract static class Material {
        String title;
        int availableCopies;
        int borrowDuration;

        public Material(String title, int availableCopies, int borrowDuration) {
            this.title = title;
            this.availableCopies = availableCopies;
            this.borrowDuration = borrowDuration;
        }

        public boolean canBorrow() {
            return availableCopies > 0;
        }

        public void borrow() {
            availableCopies--;
        }

        public void returnMaterial() {
            availableCopies++;
        }
    }

   
    static class Book extends Material {
        public Book(String title, int availableCopies) {
            super(title, availableCopies, 7);
        }
    }

    static class Journal extends Material {
        public Journal(String title, int availableCopies) {
            super(title, availableCopies, 3);
        }
    }

    static class Magazine extends Material {
        public Magazine(String title, int availableCopies) {
            super(title, availableCopies, 0);
        }
    }

    static class ThesisBook extends Material {
        public ThesisBook(String title, int availableCopies) {
            super(title, availableCopies, 2);
        }
    }

   
    static class Library {
        ArrayList<Borrower> borrowers = new ArrayList<>();
        ArrayList<Material> materials = new ArrayList<>();

        public void loadData() {
            
            try (BufferedReader br = new BufferedReader(new FileReader("borrowers.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    borrowers.add(new Borrower(data[0], Integer.parseInt(data[1]), data[2]));
                }
            } catch (IOException e) {
                System.out.println("Error reading borrowers.txt: " + e.getMessage());
            }

            
            try (BufferedReader br = new BufferedReader(new FileReader("materials.txt"))) {
                String line;
                while ((line = br.readLine()) != null) {
                    String[] data = line.split(",");
                    switch (data[0]) {
                        case "Book":
                            materials.add(new Book(data[1], Integer.parseInt(data[2])));
                            break;
                        case "Journal":
                            materials.add(new Journal(data[1], Integer.parseInt(data[2])));
                            break;
                        case "Magazine":
                            materials.add(new Magazine(data[1], Integer.parseInt(data[2])));
                            break;
                        case "Thesis Book":
                            materials.add(new ThesisBook(data[1], Integer.parseInt(data[2])));
                            break;
                    }
                }
            } catch (IOException e) {
                System.out.println("Error reading materials.txt: " + e.getMessage());
            }
        }

        public void saveData() {
           
            try (PrintWriter pw = new PrintWriter(new FileWriter("borrowers.txt"))) {
                for (Borrower b : borrowers) {
                    pw.println(b.name + "," + b.strikes + "," + b.borrowedMaterial);
                }
            } catch (IOException e) {
                System.out.println("Error writing borrowers.txt: " + e.getMessage());
            }

            
            try (PrintWriter pw = new PrintWriter(new FileWriter("materials.txt"))) {
                for (Material m : materials) {
                    String type = m instanceof Book ? "Book" :
                            m instanceof Journal ? "Journal" :
                            m instanceof Magazine ? "Magazine" : "Thesis Book";
                    pw.println(type + "," + m.title + "," + m.availableCopies);
                }
            } catch (IOException e) {
                System.out.println("Error writing materials.txt: " + e.getMessage());
            }
        }

        public Borrower getBorrower(String name) {
            for (Borrower b : borrowers) {
                if (b.name.equalsIgnoreCase(name)) return b;
            }
            return null;
        }

        public Material getMaterial(String title) {
            for (Material m : materials) {
                if (m.title.equalsIgnoreCase(title)) return m;
            }
            return null;
        }

        public void borrowMaterial(String borrowerName, String materialTitle) {
            Borrower borrower = getBorrower(borrowerName);
            if (borrower == null) {
                System.out.println("Borrower not found!");
                return;
            }
            if (!borrower.canBorrow()) {
                System.out.println("Borrower cannot borrow material due to strikes or already borrowing!");
                return;
            }

            Material material = getMaterial(materialTitle);
            if (material == null || !material.canBorrow()) {
                System.out.println("Material not available for borrowing!");
                return;
            }

            borrower.borrowedMaterial = materialTitle;
            material.borrow();
            System.out.println("Borrowing successful!");
        }

        public void returnMaterial(String borrowerName) {
            Borrower borrower = getBorrower(borrowerName);
            if (borrower == null || borrower.borrowedMaterial.equals("")) {
                System.out.println("No material to return for this borrower!");
                return;
            }

            Material material = getMaterial(borrower.borrowedMaterial);
            if (material == null) {
                System.out.println("Material not found!");
                return;
            }

            material.returnMaterial();
            borrower.borrowedMaterial = "";
            if (Math.random() > 0.5) { 
                borrower.incrementStrikes();
                System.out.println("Material returned late! Strike added.");
            } else {
                System.out.println("Material returned on time.");
            }
        }
    }

    public static void main(String[] args) {
        Library library = new Library();
        library.loadData();
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nLibrary Menu:\n");
            System.out.println("1. Borrow Material");
            System.out.println("2. Return Material");
            System.out.println("3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter borrower name: ");
                    String borrowerName = scanner.nextLine();
                    System.out.print("Enter material title: ");
                    String materialTitle = scanner.nextLine();
                    library.borrowMaterial(borrowerName, materialTitle);
                    break;
                case 2:
                    System.out.print("Enter borrower name: ");
                    borrowerName = scanner.nextLine();
                    library.returnMaterial(borrowerName);
                    break;
                case 3:
                    library.saveData();
                    System.out.println("End of Program!");
                    return;
                default:
                    System.out.println("Invalid choice. Try again!");
            }
        }
    }
}


