package com.game.listing.repositories;

import java.util.List;

import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.game.listing.models.Todo;

@Repository
public interface TodoRepository {

	List<Todo> findAll(Sort sortByCreatedAtDesc);

	Todo save(Todo todo);

	Todo findOne(String id);

	void delete(String id);

}