package wissolsoft.porada.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.appfuse.model.User;

@Entity
@Table(name="vote")
public class Vote {

    public enum Value {
        /** Утримався */
        ABSTAIN,

        /** За */
        FOR,

        /** Проти  */
        AGAINST
    }

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Law law;

    @ManyToOne
    private User user;

    private Value value;

    public long getId() {
        return id;
    }

    public Law getLaw() {
        return law;
    }

    public User getUser() {
        return user;
    }

    public Value getValue() {
        return value;
    }

    public void setLaw(Law law) {
        this.law = law;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public void setValue(Value value) {
        this.value = value;
    }


}
