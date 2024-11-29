
public class Journal extends Material {
    private String name, yearPublished, publisher;

    public Journal(String materialID, String name, String yearPublished, String publisher, int copies) {
        super(materialID, copies);
        this.name = name;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
                "\nType: Journal" +
                "\nName: " + name +
                "\nYear Published: " + yearPublished +
                "\nPublisher: " + publisher +
                "\nCopies: " + getCopies();
    }
}
