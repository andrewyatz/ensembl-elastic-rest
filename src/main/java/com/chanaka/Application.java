package com.chanaka;

import com.chanaka.track.model.GenomeJPA;
import com.chanaka.track.model.LocationJPA;
import com.chanaka.track.model.ReleaseJPA;
import com.chanaka.track.model.Track;
import com.chanaka.track.model.TrackJPA;
import com.chanaka.track.model.TrackTypeJPA;
import com.chanaka.track.service.TrackJPAService;
import com.chanaka.track.service.TrackService;
import org.elasticsearch.client.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;

import java.util.List;
import java.util.Map;

@SpringBootApplication
public class Application implements CommandLineRunner {

    @Autowired
    private ElasticsearchOperations es;

    @Autowired
    private TrackJPAService trackJPAService;

    @Autowired
    private TrackService trackService;

    public static void main(String args[]) {
        SpringApplication.run(Application.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        printElasticSearchInfo();

        List<TrackJPA> tracks = trackJPAService.findAllTracks();

        for (TrackJPA t : tracks) {
            Track esTrack = new Track();
            esTrack.setShort_name(t.getShort_name());
            esTrack.setLong_name(t.getLong_name());
            esTrack.setDescription(t.getDescription());
            esTrack.setTrack_type(t.getTrackType().getName());

            GenomeJPA g = t.getGenome();
            esTrack.setGenome_species(g.getSpecies().getName());
            esTrack.setGenome_assembly(g.getAssembly());
            esTrack.setGenome_strain(g.isStrain());

            LocationJPA l = t.getLocation();
            esTrack.setLocation_type(l.getType());
            esTrack.setLocation_object_type(l.getObjectType());
            esTrack.setLocation_species(l.getSpecies());
            esTrack.setLocation_dbtype(l.getDbtype());

            List<ReleaseJPA> releases = t.getReleases();
            if(!releases.isEmpty()) {
                ReleaseJPA r = releases.iterator().next();
                esTrack.setRelease_division(r.getDivision());
                esTrack.setRelease_version(r.getVersion());
            }

            trackService.save(esTrack);
        }

    }

    // useful for debug
    private void printElasticSearchInfo() {

        System.out.println("--ElasticSearch-->");
        Client client = es.getClient();
        Map<String, String> asMap = client.settings().getAsMap();

        asMap.forEach((k, v) -> {
            System.out.println(k + " = " + v);
        });
        System.out.println("<--ElasticSearch--");
    }

}
