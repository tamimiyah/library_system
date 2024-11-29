public class Material {

    private String materialID, yearPublished, publisher;

    private int copies;



    public Material(String materialID, String yearPublished, String publisher, int copies) {

        this.materialID = materialID;

        this.yearPublished = yearPublished;

        this.publisher = publisher;

        this.copies = copies;

    }



    public String getMaterialID() {

        return materialID;

    }



    public void setMaterialID(String materialID) {

        this.materialID = materialID;

    }



    public String getYearPublished() {

        return yearPublished;

    }



    public void setYearPublished(String yearPublished) {

        this.yearPublished = yearPublished;

    }



    public String getPublisher() {

        return publisher;

    }



    public void setPublisher(String publisher) {

        this.publisher = publisher;

    }



    public int getCopies() {

        return copies;

    }



    public void setCopies(int copies) {

        this.copies = copies;

    }

}
