package br.com.ntconsult.DevBackNTSicredi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "TB_SESSION")
@AllArgsConstructor
@NoArgsConstructor
public class Session implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @OneToOne
    @JoinColumn(name = "TOPIC", referencedColumnName = "ID")
    @NotNull
    private Topic topic;

    @Column(name = "START", nullable = true)
    private Date start;

    @Column(name = "END", nullable = true)
    private Date end;

    @OneToMany(mappedBy = "session")
    @JsonManagedReference
    private List<Vote> votes;


    private Date incrementStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }

    @PrePersist
    void preInsert() {
        this.start = new Date();
        if(this.end == null || this.end.before(start)){
            this.end = incrementStartDate();
        }
    }
}
