
import java.util.*;

public class AssetHistory {
    private String materialID;
    private List<BorrowRecord> borrowRecords;

    public AssetHistory(String materialID) {
        this.materialID = materialID;
        this.borrowRecords = new ArrayList<>();
    }

    public void addBorrowRecord(int borrowerID, Date borrowDate) {
        borrowRecords.add(new BorrowRecord(borrowerID, borrowDate));
    }

    public void returnMaterial(int borrowerID, Date returnDate) {
        for (BorrowRecord record : borrowRecords) {
            if (record.getBorrowerID() == borrowerID && record.getReturnDate() == null) {
                record.setReturnDate(returnDate);
                break;
            }
        }
    }

    public List<BorrowRecord> getAssetHistory() {
        return borrowRecords;
    }

    public static class BorrowRecord {
        private int borrowerID;
        private Date borrowDate;
        private Date returnDate;

        public BorrowRecord(int borrowerID, Date borrowDate) {
            this.borrowerID = borrowerID;
            this.borrowDate = borrowDate;
            this.returnDate = null;
        }

        public int getBorrowerID() {
            return borrowerID;
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
