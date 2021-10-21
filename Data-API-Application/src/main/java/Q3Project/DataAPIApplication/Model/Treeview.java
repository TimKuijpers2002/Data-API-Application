package Q3Project.DataAPIApplication.Model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.Hibernate;

import javax.persistence.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.Objects;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "treeview")
public class Treeview {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private int treeviewid;

    @Column(name = "object")
    private String object;

    @Column(name = "naam")
    private String name;

    @Column(name = "omschrijving")
    private String omschrijving;

    @Column(name = "boom_volgorde")
    private Integer boomVolgorde;

    @Column(name = "stamkaart")
    private String stamkaart;

    @Column(name = "treeviewtype_id")
    private Integer treeviewTypeId;

    @Column(name = "bouwjaar")
    private String bouwjaar;

    @Column(name = "actief")
    private Integer actief;

    @Column(name = "wijzigactief")
    private Integer wijzigActief;

    @Column(name = "correctief")
    private Integer corrective;

    @Column(name = "werkopdracht")
    private Integer task;

    @Column(name = "parent")
    private Integer parent;

    @Column(name = "new_id")
    private Integer newId;

    @Column(name = "treeviewtype")
    private String treeviewType;

    @Column(name = "onderhoud")
    private Integer maintenance;

    @Column(name = "onderhoudstemplate")
    private Integer maintenanceTemplate;

    @Column(name = "treeviewsoort_id")
    private Integer treeviewSoortId;

    @Column(name = "keuringsplichtig")
    private Integer inspectionObligation;

    @Column(name = "stamkaart_old")
    private String stamkaartOld;

    @Column(name = "koppelpersoon_id")
    private Integer koppelpersoonId;

    @Column(name = "koppelrelatie2_id")
    private Integer koppelrelaties2Id;

    @Column(name = "koppelpersoon2_id")
    private Integer koppelpersoon2Id;

    @Column(name = "omschrijving_id")
    private Integer omschrijvingId;

    @Column(name = "stamkaarten_id")
    private Integer stamkaartenId;

    @Column(name = "laatste_tellerstand")
    private Double laatsteTellerstand;

    @Column(name = "laatste_beurt_datum")
    private String laatsteBeurtDatum;

    @Column(name = "tellerstand_opgenomen")
    private Integer tellerstandOpgenomen;

    @Column(name = "laatste_beurt")
    private Integer laatsteBeurt;

    @Column(name = "laatste_servicebeurt_id")
    private Integer laatsteServicebeurtId;

    @Column(name = "machine_monitoringen_timeout_sec")
    private Integer machineMonitoringTimeoutSec;

    @Column(name = "toegang_type_id")
    private Integer toegangTypeId;

    @JoinColumn(name="treeviewid", nullable=false)
    @OneToMany
    private List<Maintenance> maintenances;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || Hibernate.getClass(this) != Hibernate.getClass(o)) return false;
        Treeview treeview = (Treeview) o;
        return Objects.equals(treeviewid, treeview.treeviewid);
    }

    @Override
    public int hashCode() {
        return 0;
    }
}
