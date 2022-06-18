package com.shop.blog;

import com.shop.dto.ItemImgDto;
import com.shop.entity.Member;

import lombok.Getter;
import lombok.Setter;
import org.modelmapper.ModelMapper;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class BlogDto {

    private Long id;

    @NotBlank(message = "리뷰제목은 필수 입력 값입니다.")
    private String title;

    @NotBlank(message = "리뷰내용은 필수 입력 값입니다.")
    private String content;

    //    private List<ItemImgDto> blogimgDtoList = new ArrayList<>();

    private List<Long> itemImgIds = new ArrayList<>();

    private static ModelMapper modelMapper = new ModelMapper();
    // DTO <==> Entity

    public Blog createItem(){
        return modelMapper.map(this, Blog.class);
    }

    public static BlogDto of(Blog blog){
        return modelMapper.map(blog,BlogDto.class);
    }

    private String imgUrl1; //블로그 이미지1 조회 경로
    private String imgUrl2; //블로그 이미지2 조회 경로
}
