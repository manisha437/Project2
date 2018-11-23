package com.dao;

import java.util.List;

import com.models.Job;

public interface JobDao {
void addJob(Job job);
List<Job>     getAllJobs();
}