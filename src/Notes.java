import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Notes {
    private String note;
    private Date creationDate;

    public Notes(String title, String text) {
        this.note = note;
        this.creationDate = getCreationDate();
    }

    public Notes(){
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getNote() {
        return note;
    }

    public void setCreationDate(String creationDate) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");

            Date date = dateFormat.parse(creationDate);
            this.creationDate = date;




    }

    public Date getCreationDate() {
        return creationDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MM-yyyy");
        return note + " " + simpleDateFormat.format(creationDate) ;
    }
}