package com.shop.api;

import com.shop.constant.ItemSellStatus;
import com.shop.dto.ItemFormDto;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.web.multipart.MultipartFile;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VueFormDto {
    private String itemNm;
    private String price;
    private String stockNumber;
    private String itemDetail;
    private MultipartFile file1;
    private MultipartFile file2;


    public static ItemFormDto makeItemFormDto(VueFormDto vueFormDto)
    {
        ItemFormDto itemFormDto = new ItemFormDto();
        itemFormDto.setItemNm((vueFormDto.getItemNm()));
        itemFormDto.setItemDetail((vueFormDto.getItemDetail()));
        itemFormDto.setPrice(Integer.parseInt(vueFormDto.getPrice()));
        itemFormDto.setStockNumber(Integer.parseInt(vueFormDto.getStockNumber()));
        itemFormDto.setItemSellStatus(ItemSellStatus.SELL);
        return itemFormDto;
    }
}
