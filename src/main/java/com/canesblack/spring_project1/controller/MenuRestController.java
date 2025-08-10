package com.canesblack.spring_project1.controller;

import com.canesblack.spring_project1.entity.Menu;
import com.canesblack.spring_project1.service.MenuRestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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

    // 메뉴(한개의 게시판) 수정
    @PutMapping("/menu/update/{idx}")
    public void updateMenu(@RequestBody Menu menu,@PathVariable("idx") int idx) {
        menu.setIdx(idx); // 특정 idx를 가진 게시글을 menu안의 title과 content를 가져와서 수정
        menuRestService.boardUpdate(menu);
    }

    // 메뉴(한개의 게시판) 삭제
    @DeleteMapping("/menu/delete/{idx}")
    public void deleteMenu(@PathVariable("idx") int idx) {
        menuRestService.boardDelete(idx);
    }

    // 특정메뉴(한개의 게시판 내용 조회) 조회
    @GetMapping("/menu/{idx}")
    public ResponseEntity<Menu> getMenu(@PathVariable("idx") int idx) {
        Menu menu = menuRestService.boardContent(idx);
        if(menu != null) {
            return ResponseEntity.ok(menu);
        } else
            return ResponseEntity.notFound().build();
    }

    // 특정메뉴(게시판 조회수 증가) 조회수 증가
    @PutMapping("/menu/count/{idx}")
    public void incrementCount(@PathVariable("idx") int idx) {
        menuRestService.boardCount(idx);
    }
}
