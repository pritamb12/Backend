package com.softwareChaser.springboot.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.softwareChaser.springboot.Error.QuizNotFoundException;
import com.softwareChaser.springboot.Model.Question;
import com.softwareChaser.springboot.Repository.QuestionRepository;

@Service
public class QuizServiceImpl implements QuizService{

	@Autowired
	private QuestionRepository Qrepo;
	

	public Question fetchByQid(Long QId) throws QuizNotFoundException {
		// TODO Auto-generated method stub
       Optional<Question> question= Qrepo.findById(QId);
		
		if(!question.isPresent())
		{
			throw new QuizNotFoundException("Quiz not found");
		}
		return question.get();
	}
	
	public Question saveQuestions(Question question) {
		return Qrepo.save(question);
	}



	
	public List<Question> getQuestions() {
		return Qrepo.findAll();
	}


	public List<Question> fetchByCategory(String Category) throws QuizNotFoundException {
		// TODO Auto-generated method stub
	   
	    Optional<List<Question>> question=  Qrepo.findByCategory(Category);
		
	 		if(Category!="JAVA" || Category!="JAVASCRIPT" || Category!="PYTHON" || Category!="C")
	 		{
	 			throw new QuizNotFoundException("Quiz not found");
	 		}
	 		return question.get();
	}

	

	public List<Question> fetchByCategoryAndDifficulty(String Category, String Difficulty) {
		// TODO Auto-generated method stub
		return Qrepo.findByCategoryAndDifficulty(Category,Difficulty); 
		
	}

	@Override
	public List<Question> fetchByDifficulty(String Difficulty) {
		// TODO Auto-generated method stub
		return Qrepo.findByDifficulty(Difficulty);
	}


	



	


		

}
