package com.chanaka.book.service;

import com.chanaka.book.model.Track;
import com.chanaka.book.repository.TrackRepository;
import org.elasticsearch.index.query.QueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.NativeSearchQueryBuilder;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by chanaka on 7/17/17.
 */

@Service
public class TrackServiceImpl implements TrackService {

    private TrackRepository trackRepository;

    @Autowired
    public void setTrackRepository(TrackRepository trackRepository) {
        this.trackRepository = trackRepository;
    }

    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public Track save(Track track) {
        return trackRepository.save(track);
    }

    @Override
    public Track findOne(int id) {
        return trackRepository.findOne(id + "");
    }

    @Override
    public Iterable<Track> findAll() {
        return trackRepository.findAll();
    }

    @Override
    public List<Track> findByDescription(String description, String track_type) {
        return trackRepository.findByDescription(description, track_type);
    }

    @Override
    public List<Track> searchTracks(String text) {

        QueryBuilder query = QueryBuilders.boolQuery()
                .should(QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("long_name")
                        .field("short_name")
                        .field("description")
                        .field("track_type")
                        .field("genome_species")
                        .field("genome_assembly")
                        .field("release_division")
                        .field("location_type")
                        .field("location_object_type")
                        .field("location_species")
                        .field("location_dbtype")
                        .field("location_logic_names")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("long_name")
                        .field("short_name")
                        .field("description")
                        .field("track_type")
                        .field("genome_species")
                        .field("genome_assembly")
                        .field("release_division")
                        .field("location_type")
                        .field("location_object_type")
                        .field("location_species")
                        .field("location_dbtype")
                        .field("location_logic_names"));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Track> tracks = elasticsearchTemplate.queryForList(build, Track.class);

        return tracks;

    }

    @Override
    public List<Track> searchTracksByParams(String text, String species, String type, int release, String division) {

        System.out.println("Text : " + text + " Species : " + species + "Type : " + type + " Release : " + release + " Division : " + division);

        QueryBuilder query = QueryBuilders.boolQuery()
                .must(QueryBuilders.queryStringQuery(text)
                        .lenient(true)
                        .field("long_name")
                        .field("short_name")
                        .field("description")
                        .field("track_type")
                        .field("genome_species")
                        .field("genome_assembly")
                        .field("release_division")
                        .field("location_type")
                        .field("location_object_type")
                        .field("location_species")
                        .field("location_dbtype")
                        .field("location_logic_names")
                ).should(QueryBuilders.queryStringQuery("*" + text + "*")
                        .lenient(true)
                        .field("long_name")
                        .field("short_name")
                        .field("description")
                        .field("track_type")
                        .field("genome_species")
                        .field("genome_assembly")
                        .field("release_division")
                        .field("location_type")
                        .field("location_object_type")
                        .field("location_species")
                        .field("location_dbtype")
                        .field("location_logic_names"))
                .must(QueryBuilders.queryStringQuery(species)
                        .lenient(true)
                        .field("genome_species"))
                .should(QueryBuilders.queryStringQuery(type)
                        .lenient(true)
                        .field("track_type"))
                .should(QueryBuilders.queryStringQuery(Integer.toString(release))
                        .lenient(true)
                        .field("release_version"))
                .should(QueryBuilders.queryStringQuery(division)
                        .lenient(true)
                        .field("release_division"));

        NativeSearchQuery build = new NativeSearchQueryBuilder()
                .withQuery(query)
                .build();

        List<Track> tracks = elasticsearchTemplate.queryForList(build, Track.class);

        return tracks;

    }
}
