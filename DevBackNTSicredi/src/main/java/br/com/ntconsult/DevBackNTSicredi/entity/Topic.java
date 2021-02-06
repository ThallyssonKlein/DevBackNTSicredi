package br.com.ntconsult.DevBackNTSicredi.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@Entity
@Table(name = "TB_TOPIC")
@AllArgsConstructor
@NoArgsConstructor
public class Topic implements Serializable {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column(name = "ID")
    private long id;

    @Column(name = "VALUE", nullable = false)
    private String value;
}
