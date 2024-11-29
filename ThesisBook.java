import java.util.*;

public class ThesisBook extends Material {
    private String title, author, yearPublished, publisher;

    public ThesisBook(String materialID, String title, String author, String yearPublished, String publisher, int copies) {
        super(materialID, copies);
        this.title = title;
        this.author = author;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Material ID: " + getmaterialID() +
                "\nType: Thesis Book" +
                "\nTitle: " + title +
                "\nAuthor: " + author +
                "\nYear Published: " + yearPublished +
                "\nPublisher: " + publisher +
                "\nCopies: " + getcopies();
    }
}
