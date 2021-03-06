package com.soft1851.spring.orm.dao;

import com.soft1851.spring.orm.entity.Post;

import java.util.List;

public interface PostDao {
    /**
     * 新增帖子
     * @param post
     * @return
     */
    int insert(Post post);

    /**
     * 批量新增Post
     * @param posts
     * @return int[]
     */
    int[] batchInsert(List<Post> posts);

    /**
     * 根据id删除Post
     *
     * @param postId
     * @return
     */
    int delete(int postId);


    /**
     * 修改Post
     * @param post
     * @return
     */
    int update(Post post);

    /**
     * 根据id查询Post
     * @param postId
     * @return
     */
    Post get(int postId);

    /**
     * 查询所有论坛信息
     * @return
     */
    List<Post> selectAll();

    /**
     * 查询所有帖子数目
     * @return
     */
    int selectCount();


    /**
     * 批量删除Post
     * @param posts
     * @return int[]
     */
    int[] batchDelete(List<Post> posts);
}
