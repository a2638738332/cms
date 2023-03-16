package com.briup.cms.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.Version;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author briup
 * @since 2023-03-09
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("cms_fullcomment")
public class Fullcomment implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 评论id
     */
    @TableId(value = "id", type = IdType.ASSIGN_ID)
    private Long id;

    /**
     * 评论内容
     */
    private String content;

    /**
     * 评论发表时间
     */
    private LocalDateTime publishTime;

    /**
     * 评论所属用户id
     */
    private Long userId;

    /**
     * 评论所属文章id
     */
    private Long articleId;

    /**
     * 二级评论所属父评论id
     */
    private Long parentId;

    /**
     * 删除状态
     */
    @TableLogic
    private Integer deleted;

    /**
     * 回复评论id
     */
    private Long replyId;


}
