package com.oik.api.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.service.MPJJoinService;
import com.oik.api.entity.Menu;
import com.oik.api.utils.pages.PagePlus;

import java.util.List;

/**
 * <p>
 * 权限 服务类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
public interface MenuService extends MPJJoinService<Menu> {


    List<String> getParams(String userId);

    List<Menu> getMenuByUser(String userId);

    void saveOne(Menu menu);

    void updateOne(Menu menu);

    IPage<Menu> find(PagePlus<Menu> pagePlus);

    List<Menu> all();

    boolean deleteOne(String id);
}
