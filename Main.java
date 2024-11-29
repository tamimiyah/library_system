
import java.io.*;
import java.util.*;

public class Main {

    private static ArrayList<Borrowers> borrowers = new ArrayList<>();
    private static userPrompt userPrompt = new userPrompt();
    private static Scanner input = new Scanner(System.in);
    // private static AssetManager assetManager = new AssetManager();
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
                            continue;
                    }

                    break;

                case 2:
                    ClearScreen();
                    System.out.println("********************************************************");
                    System.out.println("Assets' Information Management");
                    System.out.println("********************************************************");
                    System.out.println("[1] Add");
                    System.out.println("[2] Edit");
                    System.out.println("[3] Delete");
                    System.out.println("[4] View");
                    System.out.println("[5] Back to Main Menu");
                    int aInfoManChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

                    switch (aInfoManChoice) {
                        case 1:
                            ClearScreen();
                            AddMaterial();
                            break;
                        case 2:
                            ClearScreen();
                            EditMaterial();
                            break;
                        case 3:
                            ClearScreen();
                            DeleteMaterial();
                            break;
                        case 4:
                            ClearScreen();
                            ViewMaterials();
                            break;
                        case 5:
                            continue;
                    }

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
                    // viewBorrowerHistory();
                    break;
                case 6:
                    ClearScreen();
                    System.out.println("Assets History");
                    // viewAssetHistory();
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

    private static void AddBorrower() {
        loadBorrowersFromFile();

        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Borrowers' Information Management");
            System.out.println("********************************************************");

            Borrowers borrower = new Borrowers(0, "", "", "", "", "", "", "", "");

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

    private static void AddMaterial() {
        boolean continueAdding = true;
        while (continueAdding) {

            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("           Material Information Management");
            System.out.println("********************************************************");
            System.out.println("Choose Material Type:");
            System.out.println("[1] Book ");
            System.out.println("[2] Journal ");
            System.out.println("[3] Magazine ");
            System.out.println("[4] Thesis Book ");
            System.out.println("[5] Exit ");

            int choice = userPrompt.getValidIntegerInput("Enter Choice: ");

            if (choice == 5) {
                continueAdding = false;
                continue;
            }

            String materialID = getUniqueMaterialID();

            int copies = userPrompt.promptForValidInteger("Enter # of Copies:");
            String yearPublished = userPrompt.promptForValidYear("Enter Year (YYYY):");
            String publisher = userPrompt.promptForValidString("Enter Publisher:");

            Material material = null;

            switch (choice) {
                case 1: // Book
                    String bookTitle = userPrompt.promptForValidString("Enter Title: ");
                    String author = userPrompt.promptForValidString("Enter Author: ");
                    material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                    break;

                case 2: // Journal
                    String journalName = userPrompt.promptForValidString("Enter Journal Name: ");
                    material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                    break;

                case 3: // Magazine
                    String magazineName = userPrompt.promptForValidString("Enter Magazine Name: ");
                    material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                    break;

                case 4: // Thesis Book
                    String title = userPrompt.promptForValidString("Enter Title: ");
                    String authorThesis = userPrompt.promptForValidString("Enter Author: ");
                    material = new ThesisBook(materialID, title, authorThesis, yearPublished, publisher, copies);
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid material type.");
                    continue;
            }

            if (material != null) {
                library.add(material);
                System.out.println(material.getClass().getSimpleName() + " Successfully Added!");
                saveAssets();

                continueAdding = userPrompt.confirmContinue("Adding Another Material");
            }
        }
    }

    // private static void viewBorrowerHistory() {
    //     loadBorrowersFromFile();
    //     System.out.print("Enter Borrower ID to view history: ");
    //     int borrowerId = input.nextInt();
    //     input.nextLine();
    //     Borrowers borrower = findBorrowerById(borrowerId);
    //     if (borrower != null) {
    //         borrower.viewBorrowerHistory();
    //     } else {
    //         System.out.println("Borrower not found.");
    //     }
    // }
    // private static void viewAssetHistory() {
    //     System.out.print("Enter Material ID to view history: ");
    //     String materialID = input.nextLine();
    //     AssetHistory assetHistory = assetManager.getAssetHistory(materialID);
    //     if (assetHistory != null) {
    //         for (AssetHistory.BorrowRecord record : assetHistory.getAssetHistory()) {
    //             System.out.println("Borrower ID: " + record.getBorrowerID());
    //             System.out.println("Borrow Date: " + record.getBorrowDate());
    //             System.out.println("Return Date: " + record.getReturnDate());
    //             System.out.println("-------------------------------------------------");
    //         }
    //     } else {
    //         System.out.println("Material not found.");
    //     }
    // }
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

    private static void EditMaterial() {
        loadAssets();
        boolean continueEditing = true;

        while (continueEditing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Edit Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            // Show the material type and provide options for editing based on the type
            System.out.println("Material Type: " + material.getClass().getSimpleName());
            System.out.println("\nChoose What to Edit:");

            // Show editing options based on the type of the material
            if (material instanceof Book) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Author");
                System.out.println("[3] Year Published");
                System.out.println("[4] Publisher");
                System.out.println("[5] Copies");
            } else if (material instanceof Journal) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Journal Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof Magazine) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Magazine Name");
                System.out.println("[2] Year Published");
                System.out.println("[3] Publisher");
                System.out.println("[4] Copies");
            } else if (material instanceof ThesisBook) {
                System.out.println("[0] Enter Another Material ID to Edit");
                System.out.println("[1] Title");
                System.out.println("[2] Author");
                System.out.println("[3] Year Published");
                System.out.println("[4] Publisher");
                System.out.println("[5] Copies");
            } else {
                System.out.println("[0] Enter Another Material ID to Edit");
                continue;  // In case of unsupported material types
            }

            int editChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (editChoice) {
                case 0:
                    continue;

                case 1: // Title/Name Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String title = userPrompt.promptForValidString("Enter New Title: ");
                        ((Book) material).setTitle(title);
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    } else if (material instanceof Magazine) {
                        String magazineName = userPrompt.promptForValidString("Enter New Magazine Name: ");
                        ((Magazine) material).setMagazineName(magazineName);
                    }
                    break;

                case 2: // Author/Journal/Publisher Editing
                    if (material instanceof Book || material instanceof ThesisBook) {
                        String author = userPrompt.promptForValidString("Enter New Author: ");
                        ((Book) material).setAuthor(author);
                    } else if (material instanceof Journal) {
                        String journalName = userPrompt.promptForValidString("Enter New Journal Name: ");
                        ((Journal) material).setJournalName(journalName);
                    }
                    break;

                case 3: // Year Published Editing
                    String yearPublished = userPrompt.promptForValidYear("Enter New Year Published (YYYY): ");
                    material.setYearPublished(yearPublished);
                    break;

                case 4: // Publisher Editing
                    String publisher = userPrompt.promptForValidString("Enter New Publisher: ");
                    material.setPublisher(publisher);
                    break;

                case 5: // Copies Editing
                    int copies = userPrompt.promptForValidInteger("Enter New # of Copies: ");
                    material.setCopies(copies);
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    continue;
            }

            // Save changes to the library
            saveAssets();
            System.out.println("Material Information Edited Successfully!");
            continueEditing = userPrompt.confirmContinue("Continue Editing Another Material?");
        }
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

    private static void DeleteMaterial() {
        loadAssets();
        boolean continueDeleting = true;
        while (continueDeleting) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              Delete Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            // Show the material type and provide options for editing based on the type
            System.out.println("Material Type: " + material.getClass().getSimpleName());
            System.out.println("Are you sure you want to delete this material?");
            System.out.println("[1] Yes");
            System.out.println("[2] No");

            int deleteChoice = userPrompt.getValidIntegerInput("Enter Choice: ");

            switch (deleteChoice) {
                case 1:
                    library.remove(material);
                    System.out.println("Material Information Deleted Successfully!");
                    saveAssets();
                    break;

                case 2:
                    System.out.println("Material Information Deletion Cancelled.");
                    break;

                default:
                    System.out.println("Invalid choice! Please select a valid option.");
                    continue;
            }

            continueDeleting = userPrompt.confirmContinue("Continue Deleting Another Material?");
        }
    }

    private static void ViewBorrower() {
        loadBorrowersFromFile();

        boolean continueViewing = true;
        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Borrower's Information");
            System.out.println("********************************************************");

            int borrowerId = userPrompt.getValidIntegerInput("ENTER BORROWER ID: ");
            Borrowers borrower = findBorrowersById(borrowerId);

            if (borrower == null) {
                System.out.println("Borrower ID not found.");
            } else {
                System.out.println("   BORROWER:\n\t" + borrower.getId());
                System.out.println("  LAST NAME:\n\t" + borrower.getLastName().toUpperCase());
                System.out.println(" FIRST NAME:\n\t" + borrower.getFirstName().toUpperCase());
                System.out.println("MIDDLE NAME:\n\t" + borrower.getMiddleName().toUpperCase());
                System.out.println("     GENDER:\n\t" + borrower.getGender().toUpperCase());
                System.out.println("   BIRTHDAY:\n\t" + borrower.getBirthday());
                System.out.println("  CONTACT #:\n\t" + borrower.getContactNumber());
                System.out.println("      EMAIL:\n\t" + borrower.getEmail());
                System.out.println("    ADDRESS:\n\t" + borrower.getAddress().toUpperCase());
                System.out.println(" VIOLATIONS:\n\t" + borrower.getViolationNum());
            }
            continueViewing = userPrompt.confirmContinue("Viewing Borrower's Information");
        }
    }

    private static void ViewMaterials() {
        loadAssets();
        boolean continueViewing = true;

        while (continueViewing) {
            ClearScreen();
            System.out.println("********************************************************");
            System.out.println("              View Material Information");
            System.out.println("********************************************************");
            String materialID = userPrompt.promptForValidMaterialID("ENTER MATERIAL ID: ", library);

            // Find the material by ID
            Material material = findMaterialByID(materialID);
            while (material == null) {
                System.out.println("Material ID not found. Please enter a valid Material ID.");
                materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
                material = findMaterialByID(materialID); // Check again with the new input
            }

            System.out.println("MATERIAL TYPE: " + material.getClass().getSimpleName().toUpperCase());
            if (material instanceof Book) {
                System.out.println("TITLE:\n\t" + ((Book) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((Book) material).getAuthor().toUpperCase());
            } else if (material instanceof Journal) {
                System.out.println("JOURNAL NAME:\n\t" + ((Journal) material).getJournalName().toUpperCase());
            } else if (material instanceof Magazine) {
                System.out.println("MAGAZINE NAME:\n\t" + ((Magazine) material).getMagazineName().toUpperCase());
            } else if (material instanceof ThesisBook) {
                System.out.println("TITLE:\n\t" + ((ThesisBook) material).getTitle().toUpperCase());
                System.out.println("AUTHOR:\n\t" + ((ThesisBook) material).getAuthor().toUpperCase());
            }
            System.out.println("YEAR PUBLISHED:\n\t" + material.getYearPublished());
            System.out.println("PUBLISHER:\n\t" + material.getPublisher());
            System.out.println("AVAILABLE COPIES:\n\t" + material.getCopies());

            continueViewing = userPrompt.confirmContinue("Viewing Material Information");
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
                Borrowers borrower = new Borrowers(0, "", "", "", "", "", "", "", "");
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

    private static Material findMaterialByID(String materialId) {
        if (materialId == null || library == null) {
            return null;
        }
        for (Material material : library) {
            if (material.getMaterialID().equalsIgnoreCase(materialId)) {
                return material;
            }
        }
        return null;
    }

    private static String getUniqueMaterialID() {
        while (true) {
            String materialID = userPrompt.promptForValidMaterialID("Enter Material ID: ", library);
            // Using findMaterialID() to check if the ID exists
            if (!isMaterialIDUnique(materialID)) {
                return materialID;  // Return the valid, unique material ID
            } else {
                System.out.println("Material ID already exists! Please enter a unique Material ID.");
            }
        }
    }

    private static boolean isMaterialIDUnique(String materialID) {
        for (Material material : library) {
            if (material.getMaterialID().equalsIgnoreCase(materialID)) {
                return false;
            }
        }
        return true;
    }

    private static void loadAssets() {
        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new FileReader("assets.txt"));
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length < 5) {
                    System.out.println("Skipping invalid line: " + line);
                    continue;
                }
                String type = parts[0];
                String materialID = parts[1];
                String yearPublished = parts[2];
                String publisher = parts[3];
                int copies = Integer.parseInt(parts[4]);

                Material material;
                switch (type.toLowerCase()) {
                    case "book":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid book line: " + line);
                            continue;
                        }
                        String bookTitle = parts[5];
                        String author = parts[6];
                        material = new Book(materialID, bookTitle, author, yearPublished, publisher, copies);
                        break;
                    case "journal":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid journal line: " + line);
                            continue;
                        }
                        String journalName = parts[5];
                        material = new Journal(materialID, journalName, yearPublished, publisher, copies);
                        break;
                    case "magazine":
                        if (parts.length < 6) {
                            System.out.println("Skipping invalid magazine line: " + line);
                            continue;
                        }
                        String magazineName = parts[5];
                        material = new Magazine(materialID, magazineName, yearPublished, publisher, copies);
                        break;
                    case "thesisbook":
                        if (parts.length < 7) {
                            System.out.println("Skipping invalid thesis book line: " + line);
                            continue;
                        }
                        String thesisTitle = parts[5];
                        String thesisAuthor = parts[6];
                        material = new ThesisBook(materialID, thesisTitle, thesisAuthor, yearPublished, publisher, copies);
                        break;
                    default:
                        System.out.println("Unknown material type: " + type);
                        continue;
                }
                library.add(material);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private static void saveAssets() {
        FileWriter fw = null;
        try {
            fw = new FileWriter("assets.txt");
            BufferedWriter bw = new BufferedWriter(fw);
            for (Material material : library) {
                StringBuilder line = new StringBuilder();

                if (material instanceof Book) {
                    Book book = (Book) material;
                    line.append("book,")
                            .append(book.getMaterialID()).append(",")
                            .append(book.getYearPublished()).append(",")
                            .append(book.getPublisher()).append(",")
                            .append(book.getCopies()).append(",")
                            .append(book.getTitle()).append(",")
                            .append(book.getAuthor());
                } else if (material instanceof Magazine) {
                    Magazine magazine = (Magazine) material;
                    line.append("magazine,")
                            .append(magazine.getMaterialID()).append(",")
                            .append(magazine.getYearPublished()).append(",")
                            .append(magazine.getPublisher()).append(",")
                            .append(magazine.getCopies()).append(",")
                            .append(magazine.getMagazineName());
                } else if (material instanceof Journal) {
                    Journal journal = (Journal) material;
                    line.append("journal,")
                            .append(journal.getMaterialID()).append(",")
                            .append(journal.getYearPublished()).append(",")
                            .append(journal.getPublisher()).append(",")
                            .append(journal.getCopies()).append(",")
                            .append(journal.getJournalName());
                } else if (material instanceof ThesisBook) {
                    ThesisBook thesisBook = (ThesisBook) material;
                    line.append("thesis book,")
                            .append(thesisBook.getMaterialID()).append(",")
                            .append(thesisBook.getYearPublished()).append(",")
                            .append(thesisBook.getPublisher()).append(",")
                            .append(thesisBook.getCopies()).append(",")
                            .append(thesisBook.getTitle()).append(",")
                            .append(thesisBook.getAuthor());
                } else {
                    System.out.println("Unknown material type: " + material.getClass().getSimpleName());
                    continue; // Skip unknown material types
                }

                bw.write(line.toString());
                bw.newLine(); // Add a new line for each material
            }
            bw.close();
        } catch (IOException e) {
            System.out.println("Error saving assets to file: " + e.getMessage());
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
