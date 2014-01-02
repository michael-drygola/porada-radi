package wissolsoft.porada.webapp.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="user_vote")
public class DeputatVote {

    public enum Value {
        /** Утримався */
        ABSTAIN,

        /** За */
        FOR,

        /** Проти  */
        AGAINST,

        /** Відсутній */
        ABSENT,

        /** Не голосував */
        DIDNT_VOTE
    }

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne
    private Law law;

    @ManyToOne
    private Deputat deputat;

    private Value value;

    private String note;

    private boolean remote;

    public String getNote() {
        return note;
    }

    public boolean isRemote() {
        return remote;
    }

    public long getId() {
        return id;
    }

    public Law getLaw() {
        return law;
    }

    public Deputat getDeputat() {
        return deputat;
    }

    public Value getValue() {
        return value;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public void setRemote(boolean remote) {
        this.remote = remote;
    }

    public void setLaw(Law law) {
        this.law = law;
    }

    public void setDeputat(Deputat deputat) {
        this.deputat = deputat;
    }

    public void setValue(Value value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "DeputatVote [deputat=" + deputat + ", value=" + value
                + ", note=" + note + ", remote=" + remote + "]";
    }

}
