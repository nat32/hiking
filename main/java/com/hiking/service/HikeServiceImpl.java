package com.hiking.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hiking.model.Hike;
import com.hiking.repository.HikeRepository;

@Service("hikeService")
public class HikeServiceImpl implements HikeService {

	@Autowired
	private HikeRepository hikeRepository;

	@Override
	public Hike addHike(Hike hike) {
		return  hikeRepository.addHike(hike);
	}

	@Override
	public List<Hike> getUserHikes(int user_id) {
		return hikeRepository.getUserHikes(user_id);
	}

	@Override
	public boolean updateHike(Hike hike) {
		return hikeRepository.updateHike(hike);
	}

	@Override
	public Hike getHike(int id){
		return hikeRepository.getHike(id);
	}

	@Override
	public boolean checkHike(int hike_id){
		return hikeRepository.checkHike(hike_id);
	}

	@Override
	public List<Hike> getUserDoneHikes(int user_id){
		return hikeRepository.getUserDoneHikes(user_id);
	}

	@Override
	public boolean updateHikeWithRating(int hike_id, int rating) {
		return hikeRepository.updateHikeWithRating(hike_id, rating);
	}

	@Override
	public boolean deleteHike(int hike_id){
		return hikeRepository.deleteHike(hike_id);
	}

	@Override
	public boolean updateHikeWithDescription(int hike_id, String description){
		return hikeRepository.updateHikeWithDescription(hike_id, description);
	}

	@Override
	public boolean updateHikeWithImage(int hike_id, String image) {
		return hikeRepository.updateHikeWithImage(hike_id, image);
	}
}
