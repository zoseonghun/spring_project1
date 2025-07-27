package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

//
@RestController
public class MenuRestController {

    @Autowired
    private MenuRestService menuRestService;

    // 메뉴(모든게시판)조회
    @GetMapping("/menu/all")
    public ResponseEntity<List<Menu>> getAllMenus() {
        List<Menu>menus = menuRestService.getList();
        if(menus != null && !menus.isEmpty()) {
            return ResponseEntity.ok(menus);
        } else {
            return ResponseEntity.noContent().build();
        }
    }

    // 메뉴(한개의 게시판) 생성
    @PostMapping("/menu/add")
    public ResponseEntity<String> addMenu(@RequestBody Menu menu) {
        // 작성된 시점의 날짜를 자동 설정
        if (menu.getIndate() == null || menu.getIndate().isEmpty()) {
            menu.setIndate(LocalDate.now().toString());
        }
        // 조회수는 처음에는 0으로 설정
        menu.setCount(0);
        // 메뉴를 데이터베이스에 삽입
        menuRestService.boardInsert(menu);
        return ResponseEntity.ok("게시글 작성");
    }
}
