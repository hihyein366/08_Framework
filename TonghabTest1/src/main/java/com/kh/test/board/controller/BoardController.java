package com.kh.test.board.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.kh.test.board.model.dto.Board;
import com.kh.test.board.model.service.BoardService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	
	private final BoardService service;

	
	@PostMapping("/searchTitle")
	public String searchTitle(
		@RequestParam("boardTitle") String boardTitle, Model model) {
		List<Board> board = service.searchTitle(boardTitle);
		
		String path = null;
		
		if(!board.isEmpty()) {
			path = "/searchSuccess";
			model.addAttribute("board", board);
			
		} else {
			path = "/searchFail";
		}
		
		return path;
	}
	
}
