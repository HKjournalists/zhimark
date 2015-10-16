package com.spring.mvc.model.gson;

/**
 * Created by liluoqi on 15/6/26.
 */
public class RepaymentProfitBriefGson {
    private double settledProfit;
    private double processingProfit;
    private double unsettledProfit;
    private double waitingProfit;
    private long settledOrderCount;
    private long processingOrderCount;
    private long unsettledOrderCount;
    private long waitingOrderCount;

    public RepaymentProfitBriefGson() {

    }

    public RepaymentProfitBriefGson(double settledProfit, double processingProfit,
                                    double unsettledProfit, double waitingProfit,
                                    long settledOrderCount, long processingOrderCount,
                                    long unsettledOrderCount, long waitingOrderCount) {
        this.settledProfit = settledProfit;
        this.processingProfit = processingProfit;
        this.unsettledProfit = unsettledProfit;
        this.waitingProfit = waitingProfit;
        this.settledOrderCount = settledOrderCount;
        this.processingOrderCount = processingOrderCount;
        this.unsettledOrderCount = unsettledOrderCount;
        this.waitingOrderCount = waitingOrderCount;
    }

    public double getSettledProfit() {
        return settledProfit;
    }

    public void setSettledProfit(double settledProfit) {
        this.settledProfit = settledProfit;
    }

    public double getProcessingProfit() {
        return processingProfit;
    }

    public void setProcessingProfit(double processingProfit) {
        this.processingProfit = processingProfit;
    }

    public double getUnsettledProfit() {
        return unsettledProfit;
    }

    public void setUnsettledProfit(double unsettledProfit) {
        this.unsettledProfit = unsettledProfit;
    }

    public double getWaitingProfit() {
        return waitingProfit;
    }

    public void setWaitingProfit(double waitingProfit) {
        this.waitingProfit = waitingProfit;
    }

    public long getSettledOrderCount() {
        return settledOrderCount;
    }

    public void setSettledOrderCount(long settledOrderCount) {
        this.settledOrderCount = settledOrderCount;
    }

    public long getProcessingOrderCount() {
        return processingOrderCount;
    }

    public void setProcessingOrderCount(long processingOrderCount) {
        this.processingOrderCount = processingOrderCount;
    }

    public long getUnsettledOrderCount() {
        return unsettledOrderCount;
    }

    public void setUnsettledOrderCount(long unsettledOrderCount) {
        this.unsettledOrderCount = unsettledOrderCount;
    }

    public long getWaitingOrderCount() {
        return waitingOrderCount;
    }

    public void setWaitingOrderCount(long waitingOrderCount) {
        this.waitingOrderCount = waitingOrderCount;
    }
}
