package com.hiking.repository.util;

import com.hiking.model.Hike;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;

public class HikeRowMapper implements RowMapper<Hike> {

    @Override
    public Hike mapRow(ResultSet rs, int rowNum) throws SQLException{
        Hike hike = new Hike();
        hike.setId(rs.getInt("id"));
        hike.setUser_id(rs.getInt("user_id"));
        hike.setName(rs.getString("name"));
        hike.setDistance(rs.getDouble("distance"));
        hike.setStarting_point(rs.getString("starting_point"));
        hike.setEnding_point(rs.getString("ending_point"));
        hike.setDuration(rs.getDouble("duration"));
        hike.setDifficulty(rs.getString("difficulty"));
        hike.setRating(rs.getInt("rating"));
        hike.setImage(rs.getString("image"));
        hike.setDescription(rs.getString("description"));
        hike.setDone(rs.getBoolean("done"));
        return hike;
    }
}
