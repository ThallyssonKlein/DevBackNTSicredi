package br.com.ntconsult.DevBackNTSicredi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

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
    private Topic topic;

    @Column(name = "START", nullable = true)
    private Date start;

    @Column(name = "END", nullable = true)
    private Date end;

    private Date incrementStartDate(){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(start);
        calendar.add(Calendar.MINUTE, 1);
        return calendar.getTime();
    }

    @PrePersist
    void preInsert() {
        this.start = new Date();
        if(this.end == null){
            this.end = incrementStartDate();
        }
    }

}
