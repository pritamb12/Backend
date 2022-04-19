package com.softwareChaser.springboot.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.softwareChaser.springboot.Model.Question;

@Repository
public interface QuestionRepository extends JpaRepository<Question,Long> {


	
   public List<Question> findByCategoryAndDifficulty(String Category, String Difficulty);
	
	public Optional<List<Question>> findByCategory(String Category);
	
	public List<Question> findByDifficulty(String Difficulty);
	
}
