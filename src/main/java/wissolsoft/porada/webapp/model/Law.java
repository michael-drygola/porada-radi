package wissolsoft.porada.webapp.model;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="law")
public class Law {

    public enum Status {
        UNKNOWN,
        RECEIVED, //Одержано проект
        //ACCEPTED,
        SIGNED; //Закон підписано
    }

    @Id
    private long id;
    @Column(length=2048)
    private String caption;
    private Date date;
    @Column(length=2048)
    private String description;
    private String link;
    private Status status;

    @OneToMany(mappedBy="law")
    private List<UserVote> votes = new ArrayList<UserVote>();

    @OneToMany(mappedBy="law")
    private Set<DeputatVote> deputatVotes = new HashSet<DeputatVote>();

    public Set<DeputatVote> getDeputatVotes() {
        return deputatVotes;
    }
    public Law() {
    }
    public String getCaption() {
        return caption;
    }
    public Date getDate() {
        return date;
    }
    public String getFormattedDate() {
        DateFormat df = new SimpleDateFormat("dd.MM.yy");
        return df.format(date);
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
    public List<UserVote> getVotes() {
        return votes;
    }
    public void setCaption(String caption) {
        this.caption = caption;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public void setDeputatVotes(Set<DeputatVote> deputatVotes) {
        this.deputatVotes = deputatVotes;
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
    public void setVotes(List<UserVote> votes) {
        this.votes = votes;
    }

    @Override
    public String toString() {
        return "Law [id=" + id + ", caption=" + caption + ", date=" + date
                + ", description=" + description + ", link=" + link
                + ", status=" + status + "]";
    }


}
