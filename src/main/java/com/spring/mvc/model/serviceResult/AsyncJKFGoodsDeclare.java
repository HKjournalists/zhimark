package com.spring.mvc.model.serviceResult;

/**
 * Created by liluoqi on 15/5/5.
 */
public class AsyncJKFGoodsDeclare {
    private String personalGoodsFormNo;
    private String approveResult;
    private String approveComment;
    private String processTime;

    public AsyncJKFGoodsDeclare() {

    }

    public AsyncJKFGoodsDeclare(String personalGoodsFormNo, String approveResult,
                                String approveComment, String processTime) {
        this.personalGoodsFormNo = personalGoodsFormNo;
        this.approveResult = approveResult;
        this.approveComment = approveComment;
        this.processTime = processTime;
    }

    public String getPersonalGoodsFormNo() {
        return personalGoodsFormNo;
    }

    public void setPersonalGoodsFormNo(String personalGoodsFormNo) {
        this.personalGoodsFormNo = personalGoodsFormNo;
    }

    public String getApproveResult() {
        return approveResult;
    }

    public void setApproveResult(String approveResult) {
        this.approveResult = approveResult;
    }

    public String getApproveComment() {
        return approveComment;
    }

    public void setApproveComment(String approveComment) {
        this.approveComment = approveComment;
    }

    public String getProcessTime() {
        return processTime;
    }

    public void setProcessTime(String processTime) {
        this.processTime = processTime;
    }
}
