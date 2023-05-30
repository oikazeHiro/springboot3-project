package com.oik.api.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.github.yulichang.base.MPJBaseServiceImpl;
import com.github.yulichang.wrapper.MPJLambdaWrapper;
import com.oik.api.entity.Menu;
import com.oik.api.entity.Role;
import com.oik.api.entity.RoleMenu;
import com.oik.api.entity.UserRole;
import com.oik.api.mapper.MenuMapper;
import com.oik.api.service.MenuService;
import com.oik.api.utils.pages.PagePlus;
import com.oik.api.utils.redis.CacheClient;
import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import static com.oik.api.utils.redis.RedisConstants.CACHE_MENU_TIME;
import static com.oik.api.utils.redis.RedisConstants.CACHE_USER_MENU;

/**
 * <p>
 * 权限 服务实现类
 * </p>
 *
 * @author oik
 * @since 2023-05-11
 */
@Service
public class MenuServiceImpl extends MPJBaseServiceImpl<MenuMapper, Menu> implements MenuService {

    @Resource
    private CacheClient cacheClient;

    @Override
    public List<String> getParams(String userId) {
        List<Menu> menus = getCacheMenu(userId);
        return menus.stream().map(Menu::getPerms)
                .filter(StringUtils::isNotEmpty).collect(Collectors.toList());
    }

    private List<Menu> getMenus(String userId) {
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Menu.class)
                .leftJoin(RoleMenu.class, RoleMenu::getMenuId, Menu::getId)
                .leftJoin(Role.class, Role::getId, RoleMenu::getRoleId)
                .leftJoin(UserRole.class, UserRole::getRoleId, Role::getId)
                .eq(UserRole::getUserId, userId)
                .orderByAsc(Menu::getOrderNum);
        List<Menu> menus = selectJoinList(Menu.class, wrapper);
        if (menus == null) {
            menus = new ArrayList<>();
        }
        return menus;
    }

    @Override
    public List<Menu> getMenuByUser(String userId) {
        List<Menu> listValue = getCacheMenu(userId);
        List<Menu> collect = listValue.stream()
                .filter(e -> StringUtils.isEmpty(e.getParentId()) && e.getType() == 0).toList();
        collect = toMenuList(listValue, collect);
        return collect;
    }

    private List<Menu> getCacheMenu(String userId) {
        return cacheClient
                .getListValue(CACHE_USER_MENU, userId, userId, Menu.class,
                        this::getMenus, CACHE_MENU_TIME, TimeUnit.MINUTES);
    }

    @Override
    public void saveOne(Menu menu) {
        save(menu);
    }

    @Override
    public void updateOne(Menu menu) {
        updateById(menu);
    }

    @Override
    public IPage<Menu> find(PagePlus<Menu> pagePlus) {
        Menu obj = pagePlus.getObj()==null?new Menu():pagePlus.getObj();
        MPJLambdaWrapper<Menu> wrapper = new MPJLambdaWrapper<>();
        wrapper.selectAll(Menu.class)
                .like(StringUtils.isNotEmpty(obj.getName()),Menu::getName,obj.getName())
                .eq(obj.getType()!=null,Menu::getType,obj.getType())
                .eq(StringUtils.isNotEmpty(obj.getParentId()),Menu::getParentId,obj.getParentId())
                .eq(obj.getLevel()!=null,Menu::getLevel,obj.getLevel());
        return selectJoinListPage(pagePlus,Menu.class, wrapper);
//        List<Menu> collect = list.stream()
//                .filter(e -> StringUtils.isEmpty(e.getParentId()) && e.getType() == 0).toList();
//        collect = toMenuList(list, collect);
//        return list;
    }

    @Override
    public List<Menu> all() {
        List<Menu> list = baseMapper.selectList(null);
        List<Menu> menusTree = getMenusTree(list);
        ArrayList<Menu> Menus = new ArrayList<>();
        Menus.add(new Menu().setName("menu").setChildren(menusTree));
        return Menus;
    }

    @Override
    public boolean deleteOne(String id) {
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Menu::getParentId,id);
        List<Menu> list = baseMapper.selectList(wrapper);
        if (list.isEmpty()){
            return removeById(id);
        }
        List<String> ids = getIds(list);
        remove(wrapper);
        return removeById(id);
    }

    private List<String> getIds(List<Menu> list) {
        List<String> list1 = new ArrayList<>(list.stream().map(Menu::getId).toList());
        LambdaQueryWrapper<Menu> wrapper = new LambdaQueryWrapper<>();
        wrapper.in(Menu::getParentId,list1);
        List<Menu> list2 = baseMapper.selectList(wrapper);
        if (list2.isEmpty()){
            return list1;
        }
        list1.addAll(getIds(list2));
        return list1;
    }

    private List<Menu> getMenusTree(List<Menu> list) {
        List<Menu> collect = list.stream()
                .filter(e -> StringUtils.isEmpty(e.getParentId()) && e.getType() == 0).toList();
        collect = toMenuList(list, collect);
        return collect;
    }
    /**
     * list -> tree
     */
    private List<Menu> toMenuList(List<Menu> raw, List<Menu> Level) {
        if (Level == null || Level.size() == 0) {
            return Level;
        }
        Level.forEach(e -> {
            List<Menu> list = raw.stream().filter(a -> a.getParentId().equals(e.getId())).toList();
            e.setChildren(toMenuList(raw, list));
        });
        return Level;
    }

}
