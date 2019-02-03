package com.hiking.service;

import java.util.List;

import com.hiking.model.Hike;

public interface HikeService {


	Hike addHike(Hike hike);

	List<Hike> getUserHikes(int user_id);

	boolean updateHike(Hike hike);

	Hike getHike(int id);

	boolean checkHike(int hike_id);

	List<Hike> getUserDoneHikes(int user_id);

	boolean updateHikeWithRating(int hike_id, int rating);

	boolean deleteHike(int hike_id);

	boolean updateHikeWithDescription(int hike_id, String description);

	boolean updateHikeWithImage(int hike_id, String image);
}
