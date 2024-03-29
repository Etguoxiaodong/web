package com.unicom.core.service;

import com.alibaba.dubbo.config.annotation.Service;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.unicom.core.dao.ad.ContentDao;
import com.unicom.core.pojo.ad.Content;
import com.unicom.core.pojo.ad.ContentQuery;
import com.unicom.core.pojo.entity.PageResult;
import com.unicom.core.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class ContentServiceImpl implements ContentService {
    @Autowired
    private ContentDao contentDao;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public PageResult findPage(Content content, Integer page, Integer rows) {
        PageHelper.startPage(page, rows);
        ContentQuery query = new ContentQuery();
        ContentQuery.Criteria criteria = query.createCriteria();
        if (content != null) {
            if (content.getTitle() != null && !"".equals(content.getTitle())) {
                criteria.andTitleLike("%"+content.getTitle()+"%");
            }
        }
        Page<Content> contentList = (Page<Content>)contentDao.selectByExample(query);
        return new PageResult(contentList.getTotal(),contentList.getResult());
    }

    @Override
    public void add(Content content) {

        contentDao.insertSelective(content);

        //2. 根据广告对象中的分类id, 删除redis中对应的广告集合数据
      /*  redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(content.getCategoryId());
*/
    }

    @Override
    public Content findOne(Long id) {
        return contentDao.selectByPrimaryKey(id);
    }

    @Override
    public void update(Content content) {
        Content oldContent = contentDao.selectByPrimaryKey(content.getId());
        /*redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(oldContent.getCategoryId());
        redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(content.getCategoryId());*/
        //将新的广告对象更新到数据库中
        contentDao.updateByPrimaryKeySelective(content);
    }

    @Override
    public void delete(Long[] ids) {
        if (ids != null) {
            for (Long id : ids) {
                //1. 根据广告id, 到数据库中查询广告对象
                Content content = contentDao.selectByPrimaryKey(id);

                //2. 根据广告对象中的分类id, 删除redis中对应的广告集合数据
              /*  redisTemplate.boundHashOps(Constants.CONTENT_LIST_REDIS).delete(content.getCategoryId());*/


                //3. 根据广告id删除数据库中的广告数据
                contentDao.deleteByPrimaryKey(id);
            }
        }
    }

    @Override
    public  List<Content> findByCategoryId(long categoryId) {
        ContentQuery query = new ContentQuery();
        ContentQuery.Criteria criteria = query.createCriteria();
        criteria.andCategoryIdEqualTo(categoryId);
        List<Content> list = contentDao.selectByExample(query);
        return list;
    }

    public List<Content> findByCategoryIdFromRedis(Long categoryId){
        //1.从redis当中查询数据
         List<Content> contentList =  (List<Content>)redisTemplate.boundHashOps(Constants.ADCONTENT_LISTS_REDIS).get(categoryId);
        //2.如果没有值
        if (contentList == null){
            //从数据库当中查询
            contentList = this.findByCategoryId(categoryId);
            //把查出来的结果写到redis
            redisTemplate.boundHashOps(Constants.ADCONTENT_LISTS_REDIS).put(categoryId,contentList);
        }
        return contentList;
    }
}
