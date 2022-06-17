package com.shop.blog;

import com.shop.dto.ItemFormDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping("/blog")
@Controller
@RequiredArgsConstructor
public class BlogController {

    @GetMapping(value = "/list")
    public String blogmain(){
        return "blog/blog_main";
    }

    @GetMapping(value = "/write")
    public String blogwrite(Model model){
        model.addAttribute("BlogDto", new BlogDto());
        return "blog/blog_write";
    }

}
