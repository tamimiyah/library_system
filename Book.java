
public class Book extends Material {
    private String author, yearPublished, publisher;

    public Book(String materialID, String author, String yearPublished, String publisher, int copies) {
        super(materialID, copies);
        this.author = author;
        this.yearPublished = yearPublished;
        this.publisher = publisher;
    }

    @Override
    public String toString() {
        return "Material ID: " + getMaterialID() +
                "\nType: Book" +
                "\nAuthor: " + author +
                "\nYear Published: " + yearPublished +
                "\nPublisher: " + publisher +
                "\nCopies: " + getCopies();
    }
}
