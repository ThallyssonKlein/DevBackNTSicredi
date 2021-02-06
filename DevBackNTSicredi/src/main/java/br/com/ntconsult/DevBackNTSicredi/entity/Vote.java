package br.com.ntconsult.DevBackNTSicredi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table(name = "TB_VOTE")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Vote {

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
    private long associateId;

    @ManyToOne
    @JoinColumn(name = "SESSION", referencedColumnName = "ID")
    private Session session;
}
