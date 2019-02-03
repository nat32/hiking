package com.hiking.controller;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Principal;
import java.util.*;


import com.hiking.repository.HikeRepository;
import org.springframework.validation.FieldError;
import org.springframework.web.multipart.MultipartFile;

import com.hiking.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hiking.model.Hike;


import com.hiking.service.HikeService;

import org.springframework.web.servlet.view.RedirectView;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
public class HikeController {

	@Autowired
	private HikeService hikeService;

	@Autowired
	private UserService userService;

	@Autowired
	private HikeRepository hikeRepository;


	@RequestMapping(value = "/showHikes/{user_id}", method = RequestMethod.GET)
	public String showHikes(@PathVariable(value = "user_id") int user_id, ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		List<Hike> hikes = hikeService.getUserHikes(user_id);


		if(userIdAuth == user_id) {
			model.addAttribute("hikes", hikes);
			model.addAttribute("user_id", user_id);

			return "showHikes";
		} else {
			return "403";
		}


	}

	@RequestMapping(value = "/addHike/{user_id}", method = RequestMethod.GET)
	public String addHike(@Valid @PathVariable(value = "user_id") int user_id, @ModelAttribute("hike") Hike hike, ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);


		if(userIdAuth == user_id) {
			model.addAttribute("user_id", user_id);
			return "addHike";
		} else {
			return "403";
		}


	}


	@RequestMapping(value = "/registerHike", method = RequestMethod.POST)
	public RedirectView registerHike(@ModelAttribute("hike") @Valid  Hike hike, BindingResult result,
									 ModelMap model) {

		int user_id = hike.getUser_id();

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);

		model.addAttribute("user_id", user_id);


		if(result.hasErrors()){

			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors ) {
				model.addAttribute(error.getField() + "_error", 1);
			}

			model.addAttribute("hike", hike);
			rv.setContextRelative(true);
			rv.setUrl("/addHike/{user_id}");
			return rv;
		}else{
			hikeService.addHike(hike);

			rv.setUrl("/showHikes/{user_id}");
			return rv;
		}



	}

	@RequestMapping(value = "/modifyHike/{hike_id}/{user_id}", method = RequestMethod.GET)
	public String modifyHike(@PathVariable(value = "hike_id") int hike_id, @PathVariable(value = "user_id") int user_id,
							 @ModelAttribute("modified_hike") Hike modified_hike, ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		Hike current_hike = hikeService.getHike(hike_id);
		model.addAttribute("current_hike", current_hike);

		if(userIdAuth == user_id) {
			return "modifyHike";

		} else {
			return "403";
		}

	}

	@RequestMapping(value = "/modifiedHike", method = RequestMethod.POST)
	public RedirectView submitModifiedHike(@ModelAttribute("modified_hike") @Valid Hike modified_hike,
										   BindingResult result, ModelMap model)
	{

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setAttributesMap(model);

		if(result.hasErrors()) {

			List<FieldError> errors = result.getFieldErrors();
			for (FieldError error : errors) {

				model.addAttribute(error.getField(), 1);

			}
			model.addAttribute("user_id", modified_hike.getUser_id());

			model.addAttribute("hike", modified_hike);
			rv.setContextRelative(true);
			rv.setUrl("/modifyHike/{user_id}");
			return rv;
		}

		boolean updated = hikeService.updateHike(modified_hike);

		model.addAttribute("hike_id", modified_hike.getId());
		if (updated) {

			model.addAttribute("message", 4);


			rv.setUrl("/showHike/{hike_id}");
			return rv;

		} else {

			model.addAttribute("message", 2);

			rv.setUrl("/modifyHike/{hike_id}/{user_id}");
			return rv;
		}


	}


	@RequestMapping(value = "/checkHike/{hike_id}/{user_id}", method = RequestMethod.GET)
	public RedirectView checkHike(@PathVariable(value = "hike_id") int hike_id, @PathVariable(value = "user_id") int user_id,
								  @ModelAttribute("hike") Hike hike, ModelMap model, HttpServletRequest request)
	{

		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		boolean success = hikeService.checkHike(hike_id);

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);


		if(userIdAuth == user_id) {

			if (success) {

				model.addAttribute("user_id", user_id);

				rv.setAttributesMap(model);
				rv.setUrl("/doneHikes/{user_id}");
				return rv;

			} else {

				model.addAttribute("message", 1);
				model.addAttribute("user_id", user_id);

				rv.setAttributesMap(model);
				rv.setUrl("/showHikes/{hike_id}");
				return rv;
			}
		}else{
			rv.setAttributesMap(model);
			rv.setUrl("/403");
			return rv;
		}
	}

	@RequestMapping(value = "/doneHikes/{user_id}", method = RequestMethod.GET)
	public String doneHikes(@PathVariable(value = "user_id") int user_id, ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		List<Hike> hikes = hikeService.getUserDoneHikes(user_id);


		if(userIdAuth == user_id) {
			model.addAttribute("hikes", hikes);
			model.addAttribute("user_id", user_id);
			return "doneHikes";
		} else {
			return "403";
		}


	}

	@RequestMapping(value = "/setRating/{rating}/{user_id}/{hike_id}/{from}", method = RequestMethod.GET)
	public RedirectView setRating(@PathVariable(value = "rating") int rating, @PathVariable(value = "user_id") int user_id,
								  @PathVariable(value = "hike_id") int hike_id, @PathVariable(value = "from") String from,
								  ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);


		if(userIdAuth == user_id) {

			boolean ratingOk = hikeService.updateHikeWithRating(hike_id, rating);

			model.addAttribute("user_id", user_id);

			if(from.equals("showHikes")) {

				boolean checkOk = hikeService.checkHike(hike_id);

					if(ratingOk && checkOk) {

						model.addAttribute("message", 3);

						rv.setAttributesMap(model);
						rv.setUrl("/doneHikes/{user_id}");
						return rv;
					} else {

						model.addAttribute("message", 1);
						rv.setAttributesMap(model);
						rv.setUrl("/showHikes/{user_id}");
						return rv;

					}


			}else if(from.equals("doneHikes")) {

				//done Hikes
				if (ratingOk) {
					model.addAttribute("message", 1);
				} else {
					model.addAttribute("message", 2);
				}

				rv.setAttributesMap(model);
				rv.setUrl("/doneHikes/{user_id}");
				return rv;
			}else{

				rv.setAttributesMap(model);
				rv.setUrl("/403");
				return rv;
			}
		}else {

			rv.setAttributesMap(model);
			rv.setUrl("/403");
			return rv;
		}
	}

	@RequestMapping(value = "/deleteHike/{hike_id}/{user_id}/{from}", method = RequestMethod.GET)
	public RedirectView deleteHike(@PathVariable(value = "hike_id")  int hike_id, @PathVariable(value = "user_id") int user_id,
								   @PathVariable(value = "from") String from, ModelMap model, HttpServletRequest request)
	{

		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		boolean deleted_hike = hikeService.deleteHike(hike_id);

		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setAttributesMap(model);

		if (userIdAuth == user_id) {
			if (deleted_hike) {

				model.addAttribute("user_id", user_id);

				if (from.equals("showHikes")) {
					rv.setUrl("/showHikes/{user_id}");
				} else {
					rv.setUrl("/doneHikes/{user_id}");
				}
				return rv;

			} else {

				model.addAttribute("user_id", user_id);
				//model.addAttribute("message", 3);

				if (from.equals("showHikes")) {
					rv.setUrl("/showHikes/{user_id}");
				} else {
					rv.setUrl("/doneHikes/{user_id}");
				}
				return rv;

			}
		} else {

			rv.setUrl("/403");
			return rv;
		}


	}

	@RequestMapping(value = "/showHike/{hike_id}", method = RequestMethod.GET)
	public String showHike(@PathVariable(value = "hike_id") int hike_id, ModelMap model, HttpServletRequest request)
	{
		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		Hike hike = hikeService.getHike(hike_id);
		int user_id = hike.getUser_id();


		if(userIdAuth == user_id) {
			model.addAttribute("hike", hike);
			return "showHike";
		} else {
			return "403";
		}
	}


	@RequestMapping(value = "/getImage/{user_id}/{imageName}")
	@ResponseBody
	public byte[] getImage(@PathVariable String imageName, @PathVariable int user_id, HttpServletRequest request)  {


		String imagepath = "/images";
		File dir = new File( imagepath  + File.separator + user_id);

		String imagePath = imagepath + File.separator + user_id + File.separator + imageName;

		Path path = Paths.get(imagePath);

		byte[] data = new byte[0];
		try {
			data = Files.readAllBytes(path);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return data;
	}

    private static final List<String> contentTypes = Arrays.asList("image/png", "image/jpeg", "image/gif");

	@RequestMapping(value = "/changeDetailsPost", method = RequestMethod.POST)
	public @ResponseBody
	RedirectView changeDetailsPost(@RequestParam("description") String description, @RequestParam("user_id") int user_id,
							 @RequestParam("hike_id") int hike_id,
							 @RequestParam("file") MultipartFile file, ModelMap model) {


		RedirectView rv = new RedirectView();
		rv.setContextRelative(true);
		rv.setAttributesMap(model);

		if(!description.trim().equals("")){

			hikeService.updateHikeWithDescription(hike_id, description);
			model.addAttribute("hike_id", hike_id);

		}

		if (!file.isEmpty()) {
			try {

				String name = "image_" + hike_id;

                String fileContentType = file.getContentType();
                if(contentTypes.contains(fileContentType)) {

                	if(file.getSize() < 5000000){

						byte[] bytes = file.getBytes();

						String imagepath  = "/images";
						File dir = new File( imagepath  + File.separator + user_id);
						if (!dir.exists())
							dir.mkdirs();

						// Create the file on server
						File serverFile = new File(dir.getAbsolutePath()
								+ File.separator + name);
						BufferedOutputStream stream = new BufferedOutputStream(
								new FileOutputStream(serverFile));
						stream.write(bytes);
						stream.close();

						hikeService.updateHikeWithImage(hike_id, name);

					}else{

						model.addAttribute("msg", 1);

						model.addAttribute("hike_id", hike_id);
						rv.setUrl("/changeDetails/{hike_id}");
						return rv;
					}



                }else{
                    model.addAttribute("msg", 1);

                    model.addAttribute("hike_id", hike_id);
                    rv.setUrl("/changeDetails/{hike_id}");
                    return rv;
                }

			} catch (Exception e) {
				model.addAttribute("msg", 3);

				model.addAttribute("hike_id", hike_id);
				rv.setUrl("/changeDetails/{hike_id}");
				return rv;

			}

			model.addAttribute("hike_id", hike_id);
			rv.setUrl("/showHike/{hike_id}");
			return rv;

		} else {
			model.addAttribute("hike_id", hike_id);
			rv.setUrl("/showHike/{hike_id}");
			return rv;

		}

	}



	@RequestMapping(value = "/changeDetails/{hike_id}", method = RequestMethod.GET)
	public String changeDetails(@PathVariable(value = "hike_id") int hike_id, ModelMap model, HttpServletRequest request)
	{

		Principal principal = request.getUserPrincipal();

		String username = principal.getName();

		int userIdAuth = userService.getUserIdByName(username);

		Hike hike = hikeService.getHike(hike_id);

		int user_id = hike.getUser_id();


		if(userIdAuth == user_id) {
			model.addAttribute("hike", hike);
			return "changeDetails";
		} else {
			return "403";
		}

	}


}
