package com.woobot.jobProject.repository;

import model.JobPost;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface JobPostRepository extends MongoRepository<JobPost, String> {
}
