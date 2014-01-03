package wissolsoft.porada.webapp.model;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

//TODO use builder pattern

@Entity
@Table(name="deputy")
public class Deputy {

    /**
     * ID of deputat in on the rada.gov.ua. Url typically is http://gapp.rada.gov.ua/mps/info/page/<ID>
     */
    @Id
    private long id;

    private String link;

    private String name;

    /**
     * For example, Булочкин А. Є.
     */
    private String shortName;

    @OneToMany(mappedBy="deputy")
    private Set<DeputyVote> votes = new HashSet<DeputyVote>();

    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public String getLink() {
        return link;
    }

    public void setVotes(Set<DeputyVote> votes) {
        this.votes = votes;
    }

    public String getShortName() {
        return shortName;
    }

    public void setShortName(String shortName) {
        this.shortName = shortName;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Deputy [id=" + id + ", name=" + name + " (" + shortName + "), link=" + link + "]";
    }

}
