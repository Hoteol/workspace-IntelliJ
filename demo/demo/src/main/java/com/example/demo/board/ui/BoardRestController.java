package com.example.demo.board.ui;

import com.example.demo.board.domain.BadRequestException;
import com.example.demo.board.domain.Board;
import com.example.demo.board.domain.BoardDto;
import com.example.demo.board.domain.ErrorResponse;
import com.example.demo.board.infra.BoardRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
public class BoardRestController {


    final private BoardRepository boardRepository;
    final private ModelMapper modelMapper;

    @Autowired
    public BoardRestController(BoardRepository boardRepository, ModelMapper modelMapper) {
        this.boardRepository = boardRepository;
        this.modelMapper = modelMapper;
    }

    // 게시판 단일 컨텐츠 보여주는 것
    @GetMapping("/board")
    ResponseEntity board(@Param("id") int id){
        Optional<Board> optionalBoard = boardRepository.findByDeletedAndId(false, id);
        optionalBoard.orElseThrow(()->new BadRequestException("데이터가 없습니다."));
        return new ResponseEntity<>(modelMapper.map(optionalBoard.get(), BoardDto.class), HttpStatus.OK);
    }

    // 게시판 리스트를 보여주는 것, 페이징처리 PageableDefault 내장으로 페이징처리, id로 정렬
    @GetMapping("/boards")
    ResponseEntity boards(@PageableDefault(size = 3, sort = "id" ,direction = Sort.Direction.DESC) Pageable pageable){
        Page<Board> boards = boardRepository.findAll(pageable);
        return new ResponseEntity<>(boards,HttpStatus.OK);
    }

    // 게시판 컨텐츠 생성, 게시판 생성시 에러 예외 처리
    @PostMapping("/board")
    ResponseEntity createBoard(@Valid @RequestBody Board board, BindingResult bindingResult){
        if(bindingResult.hasErrors()) {
            StringBuilder builder = new StringBuilder();
            for(FieldError fieldError : bindingResult.getFieldErrors()){
                builder.append("[");
                builder.append(fieldError.getField());
                builder.append("] 은(는) ");
                builder.append(fieldError.getDefaultMessage());;
                builder.append(" 입력된 값: [");
                builder.append(fieldError.getRejectedValue());
                builder.append("]");
            }
            throw new BadRequestException(builder.toString());
        }
        return new ResponseEntity<>(boardRepository.save(board), HttpStatus.CREATED);
    }

    // 예외 처리
    @ExceptionHandler(BadRequestException.class)
    ResponseEntity badRequestExceptionHandler(BadRequestException e){
        ErrorResponse errorResponse = new ErrorResponse("잘못된 요청",e.getMessage());
        return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
    }
}
