package org.formation.dao;

import java.util.List;

import org.formation.model.Movie;
import org.springframework.stereotype.Repository;

@Repository
public interface MovieDAO {
	public List<Movie> findAll();
}
