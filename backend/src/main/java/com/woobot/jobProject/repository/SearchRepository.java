package com.woobot.jobProject.repository;

import model.JobPost;

import java.util.List;

public interface SearchRepository {
    List<JobPost> findByText(String text);

}
