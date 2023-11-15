package org.formation.service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.formation.dao.MovieDAO;
import org.formation.model.Movie;
import org.springframework.stereotype.Service;

@Service
public class MovieLister {

	private final MovieDAO movieDAO;
	public MovieLister(MovieDAO movieDAO){
		this.movieDAO=movieDAO;
	}

	public List<Movie> moviesDirectedBy(String director) {

		List<Movie> movies = movieDAO.findAll();

		return movies.stream()
				.filter(movie -> movie.getDirector().equalsIgnoreCase(director))
				.collect(Collectors.toList());
	}
}
