package br.com.ntconsult.DevBackNTSicredi.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "TB_VOTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote implements Serializable {

    private enum Answer {
        SIM, NAO
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Enumerated(EnumType.ORDINAL)
    @Column(name = "VALUE", nullable = false)
    private Answer value;

    @Column(name = "ASSOCIATE_ID", unique = true, nullable = false)
    private String associateId;

    @ManyToOne
    @JoinColumn(name = "SESSION", referencedColumnName = "ID")
    @JsonBackReference
    @NotNull
    private Session session;
}
