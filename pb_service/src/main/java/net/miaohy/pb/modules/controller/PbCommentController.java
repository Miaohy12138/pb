package net.miaohy.pb.modules.controller;


import net.miaohy.pb.common.model.Result;
import net.miaohy.pb.modules.request.EditCommentRequest;
import net.miaohy.pb.modules.request.CommentListRequest;
import net.miaohy.pb.modules.service.impl.PbCommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author miaohy
 * @since 2020-05-24
 */
@RestController
@RequestMapping("/pb/comment")
public class PbCommentController {

    @Autowired
    PbCommentServiceImpl commentService;

    @PostMapping("list")
    public Result getlist(@RequestBody CommentListRequest request){
        return commentService.getList(request);
    }
    @PostMapping("add")
    public Result add(@RequestBody EditCommentRequest request){
        return commentService.edit(request);
    }
}

