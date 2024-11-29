import java.util.*;

public class Magazine extends Material {
    private String magazineName, yearPublished, publisher;

    public Magazine(String materialID, String magazineName, String yearPublished, String publisher, int copies) {
        super(materialID, copies);
        this.magazineName = magazineName;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Material ID: " + materialID +
                "\nType: Magazine" +
                "\nMagazine Name: " + magazineName +
                "\nYear Published: " + yearPublished +
                "\nPublisher: " + publisher +
                "\nCopies: " + copies;
    }
}
