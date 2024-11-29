public abstract class Material {
    private String materialID;
    private int copies;

    public Material(String materialID, int copies) {
        this.materialID = materialID;
        this.copies = copies;
    }

    public String getMaterialID() {
        return materialID;
    }

    public int getCopies() {
        return copies;
    }

    public void setCopies(int copies) {
        this.copies = copies;
    }

    @Override
    public abstract String toString();
}
