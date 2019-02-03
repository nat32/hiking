package com.hiking.repository;

import java.util.List;

import com.hiking.model.Hike;

public interface HikeRepository {


	Hike addHike(Hike hike);

	Hike createDoneHike(Hike hike);

    Hike getHike(int id);

	List<Hike> getUserHikes(int user_id);

	boolean updateHike(Hike hike);

	boolean checkHike(int hike_id);

	List<Hike> getUserDoneHikes(int user_id);

	boolean updateHikeWithRating(int hike_id, int rating);

	boolean deleteHike(int hike_id);

	boolean updateHikeWithDescription(int hike_id, String description);

	boolean updateHikeWithImage(int hike_id, String image);
}
