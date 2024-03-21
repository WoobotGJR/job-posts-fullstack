package com.woobot.jobProject.controller;

import com.woobot.jobProject.repository.JobPostRepository;
import com.woobot.jobProject.repository.SearchRepository;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.servlet.http.HttpServletResponse;
import model.JobPost;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class JobPostController {
    @Autowired
    JobPostRepository repository;

    @Autowired
    SearchRepository srepo;

    @Hidden
    @RequestMapping("/")
    public void redirect(HttpServletResponse response) throws IOException {
        response.sendRedirect("/swagger-ui.html");
    }
    @GetMapping("/job-posts")
    public List<JobPost> getAllJobPosts() {
        return repository.findAll();
    }

    @GetMapping("/job-posts/{query}")
    public List<JobPost> searchJobPosts(@PathVariable String query) {
        return srepo.findByText(query);
    }

    @PostMapping("/post")
    public JobPost createPost(@RequestBody JobPost post) {
        return repository.save(post);
    }
}
