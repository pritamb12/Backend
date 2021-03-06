package com.softwareChaser.springboot.Controller;

import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;




import com.softwareChaser.springboot.Error.QuizNotFoundException;
import com.softwareChaser.springboot.Model.Question;
import com.softwareChaser.springboot.Service.QuizService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:3000")
public class QuizController {
	
	//slf4j logger
	Logger logger=LoggerFactory.getLogger(QuizController.class);
	
	@Autowired
	private QuizService Qservice;
	
	//get quiz id --user role

//	@GetMapping(value="quiz-id/{id}")
    @RequestMapping(value = "/quiz-id/{id}", method = RequestMethod.GET)
	public ResponseEntity<Question> fetchByQid(@PathVariable("id") Long Qid) throws QuizNotFoundException
	{
	     
		logger.info(" Question id is "+Qid);
		
		return new ResponseEntity<>(Qservice.fetchByQid(Qid),HttpStatus.OK);
	}
	
	//delete quiz question--admin role
	@DeleteMapping("/quiz-del/{id}")
	@ApiResponse(description = "Quiz Questions Successfully deleted",responseCode = "200")
	public String deleteQuestionById(@PathVariable("id") Long QId)
	{
		logger.info("deleting department");
		Qservice.deleteQuestionById(QId);
		return "Department deleted successfully";
	}
	
	//update quiz question--admin role
	@PutMapping("/quiz-update/{id}")
    @ApiResponse(description = "Quiz Questions id updated ",responseCode = "200")
	public ResponseEntity<Question> updateQuizByQid(@PathVariable("id") Long Qid,@RequestBody Question question)
	{
       
		logger.info(" Question id is updated "+Qid);
		return new ResponseEntity<>(Qservice.updateQuizByQid(Qid,question),HttpStatus.OK);
		
	}
	
	//save quiz question--admin role
    @RequestMapping(value = "/save-quiz", method = RequestMethod.POST)
    @ApiResponse(description = "Quiz Questions Successfully added",responseCode = "200")
	public ResponseEntity<Question> SaveQuestions(@RequestBody Question question) {
	
		logger.info("Questions saved ");
		return new ResponseEntity<>(Qservice.saveQuestions(question), HttpStatus.OK);
	}
	

	//get quiz queation all--user role
    @RequestMapping(value = "/get-quiz", method = RequestMethod.GET)
    @Operation(summary="Get all quiz questions ",responses = {
			@ApiResponse(description = "Quiz Questions Successfully added",responseCode = "200",content = @Content(mediaType = "application/JSON",schema = @Schema(implementation = Question.class)))
	})
	public ResponseEntity<List <Question>> getQuestions() {
		
			
			return new ResponseEntity<>(Qservice.getQuestions(),HttpStatus.OK);
	        
	}
	
	
	//get quiz category -- user role
	@GetMapping("quiz-category/{Category}")
	public ResponseEntity<List<Question>> fetchByCategory(@PathVariable("Category") String Category) 
	{
		logger.info("You have choosed category "+Category);
		return new ResponseEntity<>(Qservice.fetchByCategory(Category),HttpStatus.OK);
	}	

	//get quiz difficulty --user role
	@GetMapping("quiz-difficulty/{Difficulty}")
	public ResponseEntity<List<Question>> fetchByDifficulty(@PathVariable("Difficulty") String Difficulty)
	{
		logger.info("You have entered into "+Difficulty);
		return new ResponseEntity<>(Qservice.fetchByDifficulty(Difficulty),HttpStatus.OK);
	}	
	
	// get quiz by category and difficulty -- user role
	@GetMapping("quiz/CategoryAndDifficulty") 
	public ResponseEntity<List<Question>> fetchByCategoryAndDifficulty(@RequestParam String Category,@RequestParam String Difficulty) 
	{ 
		logger.info("you have choosed "+Category+" of "+Difficulty);
	return new ResponseEntity<>(Qservice.fetchByCategoryAndDifficulty(Category, Difficulty),HttpStatus.OK); 

	}
	
	// get quiz by category and difficulty -- user role
	@GetMapping(value = "/quiz/{Category}/difficulty/{Difficulty}")
	public List<Question> fetchbyCategoryandDifficulty(@PathVariable String Category,@PathVariable String Difficulty){
		
	
		logger.info("you have choosed "+Category+" of "+Difficulty);
		List<Question> question = Qservice.fetchByCategory(Category);
		
		return Qservice.fetchByDifficulty(Difficulty);
		
	}
	// access denied  
    @GetMapping("/access-denied-response")
	public String accessDenied() {
	     return "Access Denied... You don't have permission.";
	
    }

}
