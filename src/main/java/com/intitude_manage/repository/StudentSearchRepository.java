package com.intitude_manage.repository;

import java.util.List;

import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import com.intitude_manage.document.StudentDocument;

public interface StudentSearchRepository extends ElasticsearchRepository<StudentDocument , String>{

	  List<StudentDocument> findByNameContaining(String name);

	    List<StudentDocument> findByEmail(String email);

	    List<StudentDocument> findByCourse(String course);
	
}
