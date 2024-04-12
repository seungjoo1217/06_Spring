package edu.kh.project.email.model.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import edu.kh.project.email.model.mapper.EmailMapper;

@Transactional // 예외 발생하면 롤백할게~(기본값 커밋)
@Service
public class EmailServiceImpl implements EmailService{
	
	private EmailMapper mapper;
}
