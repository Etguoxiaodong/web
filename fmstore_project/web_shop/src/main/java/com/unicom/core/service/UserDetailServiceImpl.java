package com.unicom.core.service;

import com.unicom.core.dao.seller.SellerDao;
import com.unicom.core.pojo.seller.Seller;
import com.unicom.core.pojo.seller.SellerQuery;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.ArrayList;

public class UserDetailServiceImpl implements UserDetailsService {
	/*当点击登录时会调用此方法，把用户名传进来*/
	@Autowired
	private SellerDao sellerDao;
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//判断传入用户名是否为空
		if(username == null){
			return null;
		}
		//根据用户名到数据库中查询用户信息
		SellerQuery sellerQuery = new SellerQuery();
		SellerQuery.Criteria criteria = sellerQuery.createCriteria();
		criteria.andSellerIdEqualTo("3");
		Seller seller = sellerDao.selectByPrimaryKey(username);
		if(seller != null){
			/*定义角色和权限*/
			ArrayList<GrantedAuthority> arrayList = new ArrayList<>();
			arrayList.add(new SimpleGrantedAuthority("ROLE_SELLER"));
			/*判断当前用户状态是否已经通过审核*/
			if("1".equals(seller.getStatus())){
				return new User(username,seller.getPassword(),arrayList);
			}
		}
		return null;
	}
}
