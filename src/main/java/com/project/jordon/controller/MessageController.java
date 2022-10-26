package com.project.jordon.controller;

import com.project.jordon.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.project.jordon.vo.MessageVO;

@RestController //REST API 서비스를 위한 컨트롤러
@RequestMapping("/message") //컨트롤러 자체에 매핑주소 등록
public class MessageController {

    @Autowired
    private MessageService messageService;

    //메시지 추가
    @RequestMapping(value="/addM",method=RequestMethod.POST) //post방식 매핑주소 처리, addM매핑주소 등록
    public ResponseEntity<String> addMessage(@RequestBody MessageVO vo){
        /* @RequestBody MessageVO vo는 전송되는 JSON데이터를 MessageVO 객체타입으로 변경
         */
        ResponseEntity<String> entity=null;

        try {
            this.messageService.addMessage(vo);//메시지 추가
            entity=new ResponseEntity<>("SUCCESS",HttpStatus.OK);//메시지 추가가 성공하면 'SUCCESS'문자를 반환하고,200 정상상태
            //코드를 반환
        }catch(Exception e) {
            e.printStackTrace();
            entity=new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);//나쁜 상태 코드와 예외 에러 메시지 반환
        }
        return entity;
    }//addMessage()
}
