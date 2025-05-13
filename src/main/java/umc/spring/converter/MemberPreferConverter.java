package umc.spring.converter;

import umc.spring.domain.FoodCategory;
import umc.spring.domain.mapping.MemberPrefer;

import java.util.List;

public class MemberPreferConverter {

    public static List<MemberPrefer> toMemberPreferList(List<FoodCategory> foodCategoryList) {

        return foodCategoryList.stream()
                .map(foodCategory ->
                        MemberPrefer.builder()
                                .foodCategory(foodCategory)
                                .build()).toList();
    }
}
