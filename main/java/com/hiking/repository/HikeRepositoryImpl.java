package com.hiking.repository;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.hiking.repository.util.HikeRowMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import com.hiking.model.Hike;

@Repository("hikeRepository")
public class HikeRepositoryImpl implements HikeRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;


	@Override
	public Hike addHike(Hike hike){
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		insert.setGeneratedKeyName("id");

		Map<String, Object> data = new HashMap();

		data.put("name", hike.getName());
		data.put("distance", hike.getDistance());
		data.put("starting_point", hike.getStarting_point());
		data.put("ending_point", hike.getEnding_point());
		data.put("duration", hike.getDuration());
		data.put("difficulty", hike.getDifficulty());
		data.put("user_id", hike.getUser_id());


		List<String> columns = new ArrayList();
		columns.add("name");
		columns.add("distance");
		columns.add("starting_point");
		columns.add("ending_point");
		columns.add("duration");
		columns.add("difficulty");
		columns.add("user_id");


		insert.setTableName("hike");
		insert.setColumnNames(columns);

		Number id = insert.executeAndReturnKey(data);

		return getHike(id.intValue());
	}


	@Override
	public Hike createDoneHike(Hike hike){
		SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);

		insert.setGeneratedKeyName("id");

		Map<String, Object> data = new HashMap();

		data.put("name", hike.getName());
		data.put("distance", hike.getDistance());
		data.put("starting_point", hike.getStarting_point());
		data.put("ending_point", hike.getEnding_point());
		data.put("duration", hike.getDuration());
		data.put("difficulty", hike.getDifficulty());
		data.put("user_id", hike.getUser_id());

		data.put("description", hike.getDescription());
		data.put("done", hike.isDone());

		//data.put("rating", hike.getRating());

		List<String> columns = new ArrayList();
		columns.add("name");
		columns.add("distance");
		columns.add("starting_point");
		columns.add("ending_point");
		columns.add("duration");
		columns.add("difficulty");
		columns.add("user_id");
		columns.add("description");
		columns.add("done");
		columns.add("rating");


		insert.setTableName("hike");
		insert.setColumnNames(columns);

		Number id = insert.executeAndReturnKey(data);

		return getHike(id.intValue());
	}


	@Override
	public Hike getHike(int id) {
		Hike hike = jdbcTemplate.queryForObject("select * from hike where id = ?", new HikeRowMapper(), id);

		return hike;
	}

	@Override
	public List<Hike> getUserHikes(int id_user) {

		List<Hike> hikes = jdbcTemplate.query("select * from hike where user_id = ? and done = 0", new HikeRowMapper(), id_user);

		return hikes;

	}

	@Override
	public boolean updateHike(Hike hike) {

		int success = jdbcTemplate.update("UPDATE hike set name = ?, distance = ?, starting_point = ?, ending_point = ?, duration = ?, difficulty = ? where id = ? ", hike.getName(), hike.getDistance(), hike.getStarting_point(), hike.getEnding_point(), hike.getDuration(), hike.getDifficulty(), hike.getId());

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean checkHike(int hike_id){
		int success = jdbcTemplate.update("UPDATE hike set done = 1 where id = ? ", hike_id);

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public List<Hike> getUserDoneHikes(int user_id) {

		List<Hike> hikes = jdbcTemplate.query("select * from hike where user_id = ? and done = 1", new HikeRowMapper(), user_id);

		return hikes;

	}

	@Override
	public boolean updateHikeWithRating(int hike_id, int rating) {

		int success = jdbcTemplate.update("UPDATE hike set rating = ? where id = ? ",  rating, hike_id);

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean deleteHike(int hike_id) {

		int success = jdbcTemplate.update("DELETE from hike  where id = ? ", hike_id);

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateHikeWithDescription(int hike_id, String description) {

		int success = jdbcTemplate.update("UPDATE hike set description = ? where id = ? ",  description, hike_id);

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}

	@Override
	public boolean updateHikeWithImage(int hike_id, String image) {

		int success = jdbcTemplate.update("UPDATE hike set image = ? where id = ? ",  image, hike_id);

		if(success == 1){
			return true;
		}else{
			return false;
		}
	}



}
