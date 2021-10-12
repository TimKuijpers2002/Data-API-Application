package Q3Project.DataAPIApplication.Model;

import lombok.*;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = "tellerstanden")
public class Tellerstanden {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int counterPositionId;

    @Column(name = "waarde")
    private double value;

    @Column(name = "totaal")
    private double total;

    @Column(name = "treeview_id")
    private int treeviewId;

    @Column(name = "datum")
    private int date;

    @Column(name = "tellerbasis_id")
    private double counterBaseId;
}
