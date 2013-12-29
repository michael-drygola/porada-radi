package wissolsoft.porada.webapp.model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="law")
public class Law {

    public enum Status {
        ACCEPTED;
    }

    @Id
    private long id;
    private String caption;
    private Date date;
    @Column(length=2048)
    private String description;
    private String link;
    private Status status;

    @OneToMany(mappedBy="law")
    private List<Vote> votes = new ArrayList<Vote>();

    public Law() {
    }
    public String getCaption() {
        return caption;
    }
    public Date getDate() {
        return date;
    }
    public String getDescription() {
        return description;
    }
    public long getId() {
        return id;
    }
    public String getLink() {
        return link;
    }
    public Status getStatus() {
        return status;
    }
    public List<Vote> getVotes() {
        return votes;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public void setId(long id) {
        this.id = id;
    }
    public void setLink(String link) {
        this.link = link;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
    public void setVotes(List<Vote> votes) {
        this.votes = votes;
    }



}
