package com.cbcho.boot02;

import java.util.Arrays;
import java.util.stream.IntStream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Commit;
import org.springframework.test.context.junit4.SpringRunner;

import com.cbcho.boot02.model.WebBoard;
import com.cbcho.boot02.model.WebReply;
import com.cbcho.boot02.repository.WebReplyRepository;

import lombok.extern.java.Log;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log
@Commit
public class WebReplyRepositoryTest {
	
	@Autowired
	WebReplyRepository repo;
	
	public void testInsertReplies() {
		
		Long[] arr = { 301L, 300L, 299L };
		
		Arrays.stream(arr).forEach(num -> {
			
			WebBoard board = new WebBoard();
			board.setBno(num);
			
			IntStream.range(0, 10).forEach(i -> {
				
				WebReply reply = new WebReply();
				reply.setReplyText("REPLY ..." + i);
				reply.setReplyer("replyer" + i); 
				reply.setBoard(board);
				
				repo.save(reply);
			});
		});
	}
}
