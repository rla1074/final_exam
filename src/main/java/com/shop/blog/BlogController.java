package com.shop.blog;

import com.shop.dto.ItemFormDto;
import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.Item;
import com.shop.service.ItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.persistence.EntityNotFoundException;
import javax.validation.Valid;
import java.security.Principal;
import java.util.List;
import java.util.Optional;

//@RequestMapping("/blog")
@Controller
@RequiredArgsConstructor
public class BlogController {
    @Autowired
    private final BlogService blogService;

    @GetMapping(value = "/blog/list")
    public ModelAndView main(){
        ModelAndView mv = new ModelAndView();
        ModelAndView mv2 = new ModelAndView();


        mv.setViewName("/blog/blog_main");
        mv.addObject("blogList",blogService.getAllBlog());
        return mv;
    }


    //@GetMapping(value = "/blog/list")
//    public String blogmain(Model model){
//
//        Blog blog = new Blog();
//        return "blog/blog_main";
//    }*/

    @GetMapping(value = "/blog/write")
    public String itemForm(Model model){
        model.addAttribute("blogDto", new BlogDto());
        return "blog/blog_write";
    }

    @PostMapping(value = "/blog/write")
    public String itemNew(@Valid BlogDto blogDto, BindingResult bindingResult,
                          Model model, @RequestParam("itemImgFile") List<MultipartFile> itemImgFileList, Principal principal){

        if(bindingResult.hasErrors()){
            return "blog/blog_write";
        }

        if(itemImgFileList.get(0).isEmpty() && blogDto.getId() == null){
            model.addAttribute("errorMessage", "첫번째 상품 이미지는 필수 입력 값 입니다.");
            return "blog/blog_write";
        }
        try {
            String email = principal.getName();
            blogService.saveItem(blogDto, itemImgFileList,email);
        } catch (Exception e){
            model.addAttribute("errorMessage", "상품 등록 중 에러가 발생하였습니다.");
            return "blog/blog_write";
        }
        return "redirect:/blog/list";

    }



}
