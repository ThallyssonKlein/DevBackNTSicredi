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
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
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
    private LocalDateTime start;

    @Column(name = "END", nullable = true)
    private LocalDateTime end;

    @OneToMany(mappedBy = "session")
    @JsonManagedReference
    private List<Vote> votes;

    @PrePersist
    public void prePersist(){
        this.start = LocalDateTime.now(ZoneId.of( "Brazil/East" ));
        if(this.end == null){
            this.end = this.start.plusMinutes(1);
        }
    }

}
