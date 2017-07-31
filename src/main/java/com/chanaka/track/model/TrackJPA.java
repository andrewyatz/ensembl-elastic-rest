package com.chanaka.track.model;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.util.List;

/**
 * Created by chanaka on 7/27/17.
 */
@Entity
@Table(name = "track")
public class TrackJPA {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "track_id")
    private Integer id;

    @Size(max = 45)
    @Column(name = "version")
    private String version;

    @Size(max = 50)
    @Column(name = "short_name")
    private String short_name;

    @Size(max = 100)
    @Column(name = "long_name")
    private String long_name;

    @Size(max = 1000)
    @Column(name = "description")
    private String description;

    @Size(max = 11)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="genome_id")
    private GenomeJPA genome;

    @Size(max = 11)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name = "location_id")
    private LocationJPA location;

    @Size(max = 11)
    @ManyToOne(fetch=FetchType.EAGER)
    @JoinColumn(name="track_type_id")
    private TrackTypeJPA trackType;

    @Size(max = 11)
    @ManyToMany(fetch=FetchType.EAGER)
    @JoinTable(
        name="track_has_release",
        joinColumns=@JoinColumn(name="track_id", referencedColumnName="track_id"),
        inverseJoinColumns=@JoinColumn(name="data_release_id", referencedColumnName="data_release_id")
    )
    private List<ReleaseJPA> releases;


    public TrackJPA() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public String getShort_name() {
        return short_name;
    }

    public void setShort_name(String short_name) {
        this.short_name = short_name;
    }

    public String getLong_name() {
        return long_name;
    }

    public void setLong_name(String long_name) {
        this.long_name = long_name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public GenomeJPA getGenome() {
        return genome;
    }

    public void setGenome(GenomeJPA genome) {
        this.genome = genome;
    }

    public LocationJPA getLocation() {
        return location;
    }

    public void setLocation(LocationJPA location) {
        this.location = location;
    }

    public TrackTypeJPA getTrackType() {
        return trackType;
    }

    public void setTrackType(TrackTypeJPA trackType) {
        this.trackType = trackType;
    }

    public List<ReleaseJPA> getReleases() {
        return releases;
    }

    public void setReleases(List<ReleaseJPA> releases) {
        this.releases = releases;
    }

    

    @Override
    public String toString() {
        return "TrackJPA{" + "track_id=" + id + ", version=" + version + ", short_name=" + short_name + ", long_name=" + long_name + ", description=" + description + ", genome_id=" + genome.getId() + ", location_id=" + location.getId() + ", track_type_id=" + trackType.getId() + '}';
    }

   
}
