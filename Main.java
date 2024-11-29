

import java.io.*;
import java.util.*;

public class Main {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);
    private static AssetManager assetManager = new AssetManager();
    private static ArrayList<Material> library = new ArrayList<>();
    // private static final String FILE_NAME = "borrowers.txt";

    FileReader fr = null;  
    FileWriter fw = null;

    public static void main(String[] args) {
        boolean continueRunning = true;

        while (continueRunning) {
            ClearScreen();
            System.out.println("\n********************************************************");
            System.out.println("\t\tCodeX Library Management Database");
            System.out.println("********************************************************");
            System.out.println("[1] Borrowers Management");
            System.out.println("[2] Assets Management");
            System.out.println("[3] Borrow");
            System.out.println("[4] Return");
            System.out.println("[5] Borrowers History");
            System.out.println("[6] Assets History");
            System.out.println("[7] Exit");

            int mainChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (mainChoice) {
                case 1:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Borrowers' Information Management");
                    System.out.println("********************************************************");
                    System.out.println("[1] Add");
                    System.out.println("[2] Edit");
                    System.out.println("[3] Delete");
                    System.out.println("[4] View");
                    System.out.println("[5] Back to Main Menu");
                    int bInfoManChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

                    switch (bInfoManChoice) {
                        case 1:
                            ClearScreen();
                            AddBorrower();
                            break;
                        case 2:
                            ClearScreen();
                            EditBorrower();
                            break;
                        case 3:
                            ClearScreen();
                            DeleteBorrower();
                            break;
                        case 4:
                            ClearScreen();
                            ViewBorrower();
                            break;
                        case 5:
                            ClearScreen();
                            viewAssetHistory();
                            break;
                    }

                    break;

                case 2:
                    ClearScreen();
                    System.out.println("Assets Management");
                    break;
                case 3:
                    ClearScreen();
                    System.out.println("Borrow");
                    break;
                case 4:
                    ClearScreen();
                    System.out.println("Return");
                    break;

                case 5:
                    ClearScreen();
                    System.out.println("Borrowers History");
                    viewBorrowerHistory();
                    break;
                case 6:
                    ClearScreen();
                    System.out.println("Assets History");
                    viewAssetHistory();
                    break;

                case 7:
                    ClearScreen();
                    continueRunning = false;
                    exitProgram();

                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }
        }

        userPrompt.closeScanner();
    }
    private static void manageAssets() {
        while (true) {
            ClearScreen();
            System.out.println("Assets Management");
            System.out.println("1. Add Material");
            System.out.println("2. Edit Material");
            System.out.println("3. Delete Material");
            System.out.println("4. View Materials");
            System.out.println("5. List All Materials");
            System.out.println("6. Back to Main Menu");
            System.out.print("Enter your choice: ");
            int choice = input.nextInt();
            input.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    ClearScreen();
                    addMaterial();
                    break;
                case 2:
                    ClearScreen();
                    editMaterial();
                    break;
                case 3:
                    ClearScreen();
                    deleteMaterial();
                    break;
                    case 4:
                    ClearScreen();
                    viewMaterials();
                    break;
                case 5:
                    assetManager.listAllMaterials();
                    break;
                case 6:
                    return;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    private static void AddBorrower() {
        loadBorrowersFromFile();

        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Borrowers' Information Management");
            System.out.println("********************************************************");

            Borrowers borrower = new Borrowers();

            while (true) {
                int borrowerId = userPrompt.promptForValidBorrowerId("Enter Borrower ID: ", borrowers);
                if (borrowerId > 0 && findBorrowersById(borrowerId) == null) {
                    borrower.setId(borrowerId);
                    break;
                } else {
                    System.out.println("Invalid ID!!! It must be a positive integer and unique.");
                }
            }

            String lastName = userPrompt.promptForValidString("Enter Last Name: ");
            borrower.setLastName(lastName);

            String firstName = userPrompt.promptForValidString("Enter First Name: ");
            borrower.setFirstName(firstName);

            String middleName = userPrompt.promptForValidMidName("Enter Middle Name (Press Space/Enter if None): ");
            borrower.setMiddleName(middleName);

            String gender = userPrompt.promptForValidGender("Enter Gender (Male/Female): ");
            borrower.setGender(gender);

            String birthday = userPrompt.promptForValidDate("Enter Birthday (YYYY-MM-DD): ");
            borrower.setBirthday(birthday);

            String contactNum = userPrompt.promptForValidContactNum("Enter Contact Number: ");
            borrower.setContactNumber(contactNum);

            String email = userPrompt.promptForValidEmail("Enter Email: ");
            borrower.setEmail(email);

            String address = userPrompt.promptForValidString("Enter Address: ");
            borrower.setAddress(address);

            borrowers.add(borrower);
            System.out.println("Borrower's Information Added Successfully!!!");

            saveBorrowersToFile();

            continueAdding = userPrompt.confirmContinue("Adding Borrower's Information");
        }

    }

    private static void addMaterial() {
        System.out.print("Enter Material ID (9 digits): ");
        String materialID = input.nextLine();

        if (materialID.length() != 9 || !materialID.matches("\\d+")) {
            System.out.println("Error! Material ID must be exactly 9 digits long and contain only numbers!");
            return;
        }

        boolean exists = false;
        for (Material mat : library) {
            if (mat.getMaterialID().equals(materialID)) {
                exists = true;
                break;
            }
        }

        if (exists) {
            System.out.println("Error! Material ID already exists!");
            return;
        }
    
        System.out.println("Choose Material Type:");
        System.out.println("1. Book\n2. Journal\n3. Magazine\n4. Thesis Book");
        System.out.print("Enter your choice: ");
        int type = input.nextInt();
        input.nextLine(); // Consume newline

        System.out.print("Enter Number of Copies: ");
        int copies = input.nextInt();
        input.nextLine(); // Consume newline

        Material material = null;
        switch (type) {
            case 1:
                System.out.print("Enter Author: ");
                String bookAuthor = input.nextLine();
                System.out.print("Enter Year Published: ");
                String bookYear = input.nextLine();
                System.out.print("Enter Publisher: ");
                String bookPublisher = input.nextLine();
                material = new Book(materialID, bookAuthor, bookYear, bookPublisher, copies);
                break;
            case 2:
                System.out.print("Enter Name of Journal: ");
                String journalName = input.nextLine();
                System.out.print("Enter Year Published: ");
                String journalYear = input.nextLine();
                System.out.print("Enter Publisher: ");
                String journalPublisher = input.nextLine();
                material = new Journal(materialID, journalName, journalYear, journalPublisher, copies);
                break;
                case 3:
                System.out.print("Enter Magazine Name: ");
                String magazineName = input.nextLine();
                System.out.print("Enter Year Published: ");
                String magazineYear = input.nextLine();
                System.out.print("Enter Publisher: ");
                String magazinePublisher = input.nextLine();
                material = new Magazine(materialID, magazineName, magazineYear, magazinePublisher, copies);
                break;
            case 4:
                System.out.print("Enter Title: ");
                String thesisTitle = input.nextLine();
                System.out.print("Enter Author: ");
                String thesisAuthor = input.nextLine();
                System.out.print("Enter Year Published: ");
                String thesisYear = input.nextLine();
                System.out.print("Enter Publisher: ");
                String thesisPublisher = input.nextLine();
                material = new ThesisBook(materialID, thesisTitle, thesisAuthor, thesisYear, thesisPublisher, copies);
                break;
            default:
                System.out.println("Invalid Material Type!");
                return;
        }

        library.add(material);
        System.out.println("Material added successfully!");
    }

    private static void editMaterial() {
        System.out.print("Enter Material ID to Edit: ");
        String editID = input.nextLine();
        Material foundMaterial = null;

        for (Material mat : library) {
            if (mat.getMaterialID().equals(editID)) {
                foundMaterial = mat;
                break;
            }
        }

        if (foundMaterial == null) {
            System.out.println("Material not found!");
            return;
        }

        System.out.println("Edit Material: " + foundMaterial);
        System.out.print("Enter New Number of Copies: ");
        int newCopies = input.nextInt();
        input.nextLine();
        foundMaterial.setCopies(newCopies);
        System.out.println("Material updated successfully!");
    }

    private static void deleteMaterial() {
        System.out.print("Enter Material ID to Delete: ");
        String deleteID = input.nextLine();
        boolean removed = library.removeIf(mat -> mat.getMaterialID().equals(deleteID));

        if (removed) {
            System.out.println("Material deleted successfully!");
        } else {
            System.out.println("Error: Material not found!");
        }
    }

    private static void viewMaterials() {
        if (library.isEmpty()) {
            System.out.println("No materials found in the library.");
        } else {
            System.out.println("\n--- Library Materials ---");
            for (Material mat : library) {
                System.out.println(mat + "\n");
            }
        }
    }

    
    private static void viewBorrowerHistory() {
        loadBorrowersFromFile();

        System.out.print("Enter Borrower ID to view history: ");
        int borrowerId = input.nextInt();
        input.nextLine();

        Borrowers borrower = findBorrowerById(borrowerId);
        if (borrower != null) {
            borrower.viewBorrowerHistory();
        } else {
            System.out.println("Borrower not found.");
        }
    }

    private static void viewAssetHistory() {
        System.out.print("Enter Material ID to view history: ");
        String materialID = input.nextLine();

        AssetHistory assetHistory = assetManager.getAssetHistory(materialID);
        if (assetHistory != null) {
            for (AssetHistory.BorrowRecord record : assetHistory.getAssetHistory()) {
                System.out.println("Borrower ID: " + record.getBorrowerID());
                System.out.println("Borrow Date: " + record.getBorrowDate());
                System.out.println("Return Date: " + record.getReturnDate());
                System.out.println("-------------------------------------------------");
            }
        } else {
            System.out.println("Material not found.");
        }
    }

    private static void EditBorrower() {

        boolean continueEditing = true;

        loadBorrowersFromFile();
        int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to edit: ");
        Borrowers borrower = findBorrowersById(borrowerId);

        if (borrower == null) {
            System.out.println("Borrower ID not found.");

            continueEditing = userPrompt.confirmContinue("Editing Borrower's Information");
            return;
        }

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Borrower's Information");
            System.out.println("********************************************************");
            System.out.println("Choose What to Edit:");
            System.out.println("[1] Last Name");
            System.out.println("[2] First Name");
            System.out.println("[3] Middle Name");
            System.out.println("[4] Gender");
            System.out.println("[5] Birthday");
            System.out.println("[6] Contact Number");
            System.out.println("[7] Email");
            System.out.println("[8] Address");
            System.out.println("[9] Back to Previous Menu");

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 1:
                    String lastName = userPrompt.promptForValidString("Enter New Last Name: ");
                    borrower.setLastName(lastName);
                    break;
                case 2:
                    String firstName = userPrompt.promptForValidString("Enter New First Name: ");
                    borrower.setFirstName(firstName);
                    break;
                case 3:
                    String middleName = userPrompt
                            .promptForValidMidName("Enter New Middle Name (Press Space/Enter if None): ");
                    borrower.setMiddleName(middleName);
                    break;
                case 4:
                    String gender = userPrompt.promptForValidGender("Enter New Gender (Male/Female): ");
                    borrower.setGender(gender);
                    break;
                case 5:
                    String birthday = userPrompt.promptForValidDate("Enter New Birthday (YYYY-MM-DD): ");
                    borrower.setBirthday(birthday);
                    break;
                case 6:
                    String contactNum = userPrompt.promptForValidContactNum("Enter New Contact Number: ");
                    borrower.setContactNumber(contactNum);
                    break;
                case 7:
                    String email = userPrompt.promptForValidEmail("Enter New Email: ");
                    borrower.setEmail(email);
                    break;
                case 8:
                    String address = userPrompt.promptForValidString("Enter New Address: ");
                    borrower.setAddress(address);
                    break;
                case 9:
                    continueEditing = false;
                    break;
                default:
                    System.out.println("Invalid Choice!!! Please Try Again!!!");
            }
        }
        System.out.println("Borrower's Information Edited Successfully!!!");
        saveBorrowersToFile();

        continueEditing = userPrompt.confirmContinue("Editing Borrower's Information");
    }

    private static void DeleteBorrower() {
        loadBorrowersFromFile();

        boolean continueDeleting = true;
        while (continueDeleting) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Delete Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to delete: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
                break;
            } else {
                borrowers.remove(borrower);
                System.out.println("Borrower's Information Deleted Successfully!!!");
                break;
            }
        }

        saveBorrowersToFile();
        continueDeleting = userPrompt.confirmContinue("Deleting Borrower's Information");
    }

    private static void ViewBorrower() {
        loadBorrowersFromFile();

        boolean continueViewing = true;
        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("Enter Borrower ID to view: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
            } else {
                System.out.println("Borrower ID: " + borrower.getId());
                System.out.println("Last Name: " + borrower.getLastName());
                System.out.println("First Name: " + borrower.getFirstName());
                System.out.println("Middle Name: " + borrower.getMiddleName());
                System.out.println("Gender: " + borrower.getGender());
                System.out.println("Birthday: " + borrower.getBirthday());
                System.out.println("Contact Number: " + borrower.getContactNumber());
                System.out.println("Email: " + borrower.getEmail());
                System.out.println("Address: " + borrower.getAddress());
            }
            continueViewing = userPrompt.confirmContinue("Viewing Borrower's Information");
        }
    }

    private static void exitProgram() {
        System.out.println("****************************************************************");
        System.out.println("  Thank You for Visiting CodeX Library! Come Again Next Time!!");
        System.out.println("****************************************************************");

        System.out.println("LIBRARIAN:\t Valera, Tamiyah Gale ");
        System.out.println("RECORD KEEPERS:\t Conde, Kiesha ");
        System.out.println("\t\t Masiglat, Mikaella Ann ");
        System.out.println("ASSISTANT:\t Vinoya, Davee Kendra");

        System.exit(0);
    }

    private static Borrowers findBorrowersById(int id) {
        for (Borrowers borrower : borrowers) {
            if (borrower.getId() == id) {
                return borrower;
            }
        }
        return null;
    }

    private static void ClearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private static void loadBorrowersFromFile() {
        FileReader fr = null;
        try {
            fr = new FileReader("borrowers.txt");
            BufferedReader br = new BufferedReader(fr);
            String line;
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");
                Borrowers borrower = new Borrowers();
                borrower.setId(Integer.parseInt(data[0]));
                borrower.setLastName(data[1]);
                borrower.setFirstName(data[2]);
                borrower.setMiddleName(data[3]);
                borrower.setGender(data[4]);
                borrower.setBirthday(data[5]);
                borrower.setContactNumber(data[6]);
                borrower.setEmail(data[7]);
                borrower.setAddress(data[8]);
                borrowers.add(borrower);
            }
            br.close();
        } catch (IOException e) {
            System.out.println("Error loading borrowers from file: " + e.getMessage());
        } finally {
            try {
                if (fr != null) {
                    fr.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileReader: " + e.getMessage());
            }
        }
    }

    private static void saveBorrowersToFile() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("borrowers.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Borrowers borrower : borrowers) {
                bw.write(borrower.getId() + "," + borrower.getLastName() + "," + borrower.getFirstName() + ","
                        + borrower.getMiddleName() + "," + borrower.getGender() + "," + borrower.getBirthday() + ","
                        + borrower.getContactNumber() + "," + borrower.getEmail() + "," + borrower.getAddress());
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving borrowers to file: " + e.getMessage());
        } finally {
            try {
                if (fw != null) {
                    fw.close();
                }
            } catch (IOException e) {
                System.out.println("Error closing FileWriter: " + e.getMessage());
            }
        }  
    }
}
