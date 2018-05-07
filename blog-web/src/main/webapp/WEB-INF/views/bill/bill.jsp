<%@ page contentType="text/html;charset=UTF-8" %>
<%@ page trimDirectiveWhitespaces="true" %> 
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags"%>

<c:set var="ctx" value="${pageContext.request.contextPath}"/>

<div class="container" style="margin: 0 auto; max-width: 730px;background-color: #fff;">
    <div class="col-md-12">
    <div class="blog-post" style="margin-top: 20px;padding: 10px;border: 1px dashed;border-radius: 5px;">
        <h4 class="blog-post-title">单据号：${bill.billNo}</h4>
        <div class="row" style="margin-left: 0px;margin-bottom: 5px;">
            <div class="col-sm-4">毛重：${bill.grossWeight} </div>
            <div class="col-sm-4">进场：<fmt:formatDate value="${bill.inDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div class="col-sm-3">过磅人：${bill.inCreatedBy}</div>
        </div>
        <div class="row" style="margin-left: 0px;margin-bottom: 5px;">
            <div class="col-sm-4">皮重：${bill.tareWeight}  </div>
            <div class="col-sm-4">出场：<fmt:formatDate value="${bill.outDate}" pattern="yyyy-MM-dd HH:mm:ss"/></div>
            <div class="col-sm-3">过磅人：${bill.outCreatedBy}</div>
        </div>
        <div class="row" style="margin-left: 0px;margin-bottom: 5px;">
            <div class="col-sm-4">净重：${bill.netWeight}</div>
            <div class="col-sm-4">出场：实重：${bill.realNum}</div>
        </div>
        <div class="row" style="margin-left: 0px;margin-bottom: 5px;">
            <div class="col-sm-4">材料类型：${bill.materialType}</div>
            <div class="col-sm-4">规格型号：${bill.materialCode}</div>
        </div>
        <div class="row" style="margin-left: 0px;">
            <div class="col-sm-4">车牌号：${bill.vehicleNo}</div>
            <div class="col-sm-8">供应商：${bill.supplier}</div>
        </div>
    </div>

    <div class="row"  style="padding: 0 10px;">
        <h4 class="blog-post-title">进场图像</h4>
        <c:forEach begin="0" end="3" step="1" var="i">
           <div class="col-sm-3">
            <img class="img-thumbnail" src="${ctx}/api/bill/download?name=${bill.billNo}_in_${i}.jpg">
           </div>
        </c:forEach>
    </div>

    <div class="row" style="padding: 0 10px;">
        <h4>出场图像</h4>
        <c:forEach begin="0" end="3" step="1" var="i">
            <div class="col-sm-3">
                <img class="img-thumbnail" src="${ctx}/api/bill/download?name=${bill.billNo}_out_${i}.jpg">
            </div>
        </c:forEach>
    </div>

    <div class="row" style="padding: 0 10px;">
        <h4>磅单扫描</h4>
        <div class="col-sm-3">
            <img class="img-thumbnail" src="${ctx}/api/bill/download?name=${bill.billNo}_scanner_${i}.jpg">
        </div>
    </div>
    </div>
</div>

