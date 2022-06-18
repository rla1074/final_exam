package com.shop.blog;


import com.shop.dto.ItemSearchDto;
import com.shop.dto.MainItemDto;
import com.shop.entity.ItemImg;
import com.shop.entity.Member;
import com.shop.repository.MemberRepository;
import com.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.io.IOException;
import java.security.Principal;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {

    @Value("${blogImgLocation}") //설정파일에서 read
    private String blogImgLocation;//blogImgLocation=C:/shop/blog

    @Autowired
    private final BlogRepository blogRepository;

    private final FileService fileService;

    private final MemberRepository memberRepository;
    //List<MultipartFile> itemImgFileList
public Long saveItem(BlogDto blogDto,List<MultipartFile> itemImgFileList,String email) throws Exception {

    //리뷰 등록 => Blog table 저장

    //이미지 등록 => Blog table 에 저장, 이미지는 D:/shop/blog에 저장
    String oriImgName="";
    String oriImgName2="";
    String imgName = "";
    String imgName2 = "";
    String imgUrl = "";
    String imgUrl2 = "";

    Blog blog = blogDto.createItem();
    blogRepository.save(blog);
    Member member = memberRepository.findByEmail(email);


    if(itemImgFileList.get(1).isEmpty())
        {
            oriImgName = itemImgFileList.get(0).getOriginalFilename();
            imgName = fileService.uploadFile(blogImgLocation, oriImgName,
                itemImgFileList.get(0).getBytes());
            imgUrl = "/images/blog/" + imgName;
            blog.setImgUrl1(imgUrl);
            blog.setMember(member);
        }
        else
        {
            oriImgName = itemImgFileList.get(0).getOriginalFilename();
            imgName = fileService.uploadFile(blogImgLocation, oriImgName,
                    itemImgFileList.get(0).getBytes());
            imgUrl = "/images/blog/" + imgName;
            blog.setImgUrl1(imgUrl);

            oriImgName2 = itemImgFileList.get(1).getOriginalFilename();
            imgName2 = fileService.uploadFile(blogImgLocation, oriImgName2,
                    itemImgFileList.get(1).getBytes());
            imgUrl2 = "/images/blog/" + imgName2;
            blog.setImgUrl2(imgUrl2);
            blog.setMember(member);
        }

    //리뷰 등록 => Blog table 저장
    return blog.getId();
}
    public List<Blog> getAllBlog()
    {
        return blogRepository.findAll();
    }
}
