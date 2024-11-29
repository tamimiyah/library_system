import java.util.*;

public class BorrowerHistory {
    private String borrowerID;
    private List<BorrowedMaterial> borrowedMaterials;

    public BorrowerHistory(String borrowerID) {
        this.borrowerID = borrowerID;
        this.borrowedMaterials = new ArrayList<>();
    }

    public void addBorrowedMaterial(Material material, Date borrowDate) {
        borrowedMaterials.add(new BorrowedMaterial(material, borrowDate));
    }

    public void returnMaterial(String materialID, Date returnDate) {
        for (BorrowedMaterial borrowedMaterial : borrowedMaterials) {
            if (borrowedMaterial.getMaterial().getMaterialID().equals(materialID) && borrowedMaterial.getReturnDate() == null) {
                borrowedMaterial.setReturnDate(returnDate);
                break;
            }
        }
    }

    public List<BorrowedMaterial> getBorrowerHistory() {
        return borrowedMaterials;
    }

    public static class BorrowedMaterial {
        private Material material;
        private Date borrowDate;
        private Date returnDate;

        public BorrowedMaterial(Material material, Date borrowDate) {
            this.material = material;
            this.borrowDate = borrowDate;
            this.returnDate = null;
        }

        public Material getMaterial() {
            return material;
        }

        public Date getBorrowDate() {
            return borrowDate;
        }

        public Date getReturnDate() {
            return returnDate;
        }

        public void setReturnDate(Date returnDate) {
            this.returnDate = returnDate;
        }
    }
}
