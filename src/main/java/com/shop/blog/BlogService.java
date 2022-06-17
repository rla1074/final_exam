package com.shop.blog;

import com.shop.dto.ItemFormDto;
import com.shop.entity.Item;
import com.shop.entity.ItemImg;
import com.shop.repository.ItemRepository;
import com.shop.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.util.StringUtils;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class BlogService {

    @Value("${blogImgLocation}") //설정파일에서 read
    private String blogImgLocation;//blogImgLocation=C:/shop/blog

    private final BlogRepository blogRepository;

    private final FileService fileService;

//    private final ItemImgService itemImgService;
//    private final ItemImgRepository itemImgRepository;
public Long saveItem(BlogDto blogDto, List<MultipartFile> itemImgFileList) throws Exception{

    //리뷰 등록 => Blog table 저장
    Blog blog = blogDto.createItem();
    blogRepository.save(blog);

    //이미지 등록 => Blog table 에 저장, 이미지는 D:/shop/blog에 저장
    for(int i=0;i<itemImgFileList.size();i++){
        Blog blog = new Blog();


        if(i == 0)
            itemImg.setRepimgYn("Y");
        else
            itemImg.setRepimgYn("N");

        itemImgService.saveItemImg(itemImg, itemImgFileList.get(i));
    }

    return item.getId();
}

    public void saveItemImg(Blog blog, MultipartFile itemImgFile) throws Exception{
        String oriImgName = itemImgFile.getOriginalFilename();
        String imgName = "";
        String imgUrl = "";

        //파일 업로드(이미지 파일을 D:/shop/item에 저장
        if(!StringUtils.isEmpty(oriImgName)){
            imgName = fileService.uploadFile(blogImgLocation, oriImgName,
                    itemImgFile.getBytes());
            imgUrl = "/images/item/" + imgName;
            // /images/item/uuid이름
        }
        //상품 이미지 정보 저장
        blog.updateItemImg(oriImgName, imgName, imgUrl);
//        itemImg.setOriImgName(oriImgName);
//        itemImg.setImgName(imgName);
//        itemImg.setImgUrl(imgUrl);
        blogRepository.save(itemImg);
    }


}
