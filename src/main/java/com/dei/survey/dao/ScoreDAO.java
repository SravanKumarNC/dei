package com.dei.survey.dao;

import com.dei.survey.model.Score;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface ScoreDAO extends JpaRepository<Score, Long> {
//    List<Score> findByCategoryIdAndQuestionId(Long categoryId, Long questionId);
}
