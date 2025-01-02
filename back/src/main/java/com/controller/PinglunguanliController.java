package com.controller;

import com.entity.PinglunguanliEntity;
import com.service.PinglunguanliService;
import com.utils.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/pinglunguanli")
public class PinglunguanliController {

    @Autowired
    private PinglunguanliService pinglunguanliService;

    @PostMapping
    public PinglunguanliEntity addComment(@RequestParam Long refid,
                                          @RequestParam Long userid,
                                          @RequestParam String nickname,
                                          @RequestParam String content) {
        // 创建评论
        PinglunguanliEntity comment = new PinglunguanliEntity();
        comment.setRefid(refid);
        comment.setUserid(userid);
        comment.setNickname(nickname);
        comment.setContent(content);
        // 设置其他字段（比如情感分析可以添加这里）
        pinglunguanliService.insert(comment);
        return comment;
    }

    @GetMapping("/{refid}")
    public List<PinglunguanliEntity> getComments(@PathVariable Long refid) {
        // 根据 refid 查询评论列表
        return pinglunguanliService.selectList(new com.baomidou.mybatisplus.mapper.EntityWrapper<PinglunguanliEntity>()
                .eq("refid", refid));
    }

    @PostMapping("/{id}/reply")
    public void replyToComment(@PathVariable Long id, @RequestParam String replyContent) {
        // 更新评论的回复内容
        PinglunguanliEntity comment = pinglunguanliService.selectById(id);
        if (comment != null) {
            comment.setReply(replyContent);
            pinglunguanliService.updateById(comment);
        }
    }

    @DeleteMapping("/{id}")
    public void deleteComment(@PathVariable Long id) {
        // 删除评论
        pinglunguanliService.deleteById(id);
    }
}










