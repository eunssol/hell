<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, height=device-height, initial-scale=1.0, user-scalable=yes">
    <meta name="format-detection" content="telephone=no, address=no, email=no">
    <meta name="keywords" content="">
    <meta name="description" content="">
    <title>게시판목록</title>
	<%@ include file="/WEB-INF/view/include/head.jsp" %> <!-- 디렉티브 방식 : 소스를 그대로 가져옴   (인클루드 지시어)   -->
	<%-- <jsp:include page="WEB-INF/view/include/head.jsp"/>  --%><!-- 액션태그로 방식 : 얘를 실행한 결과를 가져옴 jsp의 실행결과를 가져온다    -->
	<%-- <c:import url="/WEB-INF/view/include/head.jsp"/> --%> <!--얘도 실행결과를 가져오는 것. 세개 다 결과는 똑같음  -->
</head>
<body>
    <div class="wrap">
        <%@ include file="/WEB-INF/view/include/top.jsp" %>
        <div class="sub">
            <div class="size">
                <h3 class="sub_title">뉴스게시판</h3>
    
                <div class="bbs">
                    <table class="list">
                    <p><span><strong>총 ${newsVo.totCount }개</strong>  |  ${newsVo.reqPage}/${newsVo.totPage }페이지</span></p>
                        <caption>게시판 목록</caption>
                        <colgroup>
                            <col width="80px" />
                            <col width="*" />
                            <col width="100px" />
                            <col width="200px" />
                            <col width="80px" />
                        </colgroup>
                        <thead>
                            <tr>
                                <th>번호</th>
                                <th>제목</th>
                                <th>작성자</th>
                                <th>작성일</th>
                                <th>조회수</th>
                            </tr>
                        </thead>
                        <tbody>
						<c:if test="${empty list }">
                            <tr>
                                <td class="first" colspan="5">등록된 글이 없습니다.</td>
                            </tr>
                        </c:if>
                        <c:forEach var="vo" items="${list }">     
                            <tr>
                                <td>${vo.no }</td>
                                <td class="txt_l">
                                    <a href="detail.do?no=${vo.no }">${vo.title }</a>
                                </td>
                                <td class="writer">
                                    홍길동
                                </td>
                                <td class="date">${vo.regdate }</td>
                                <td>${vo.readcount }</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="btnSet"  style="text-align:right;">
                        <a class="btn" href="write.do">글작성 </a>
                    </div>
                    <div class="pagenate clear">
                        <ul class='paging'>
                        <c:if test="${newsVo.startPage > newsVo.pageRange}">
                        	<li><a href="index.do?reqPage=${newsVo.startPage-1}&stype=${param.stype}&sval=${param.sval}&orderby=${param.orderby}&direct=${param.direct}"><</a></li>
                        </c:if>
                        <c:forEach var="rp" begin="${newsVo.startPage}" end="${newsVo.endPage }">
                            <li><a href='index.do?reqPage=${rp}&stype=${param.stype}&sval=${param.sval}&orderby=${param.orderby}&direct=${param.direct}' <c:if test="${rp==newsVo.reqPage }">class='current'</c:if>>${rp }</a></li>
                        </c:forEach>
                        <c:if test="${newsVo.totPage > newsVo.endPage}">
                        	<li><a href="index.do?reqPage=${newsVo.endPage+1}&stype=${param.stype}&sval=${param.sval}&orderby=${param.orderby}&direct=${param.direct}">></a></li>
                        </c:if>
                        </ul> 
                    </div>
                
                    <!-- 페이지처리 -->
                    <div class="bbsSearch">
                        <form method="get" name="searchForm" id="searchForm" action="">
                            <span class="srchSelect">
                                <select id="orderby" name="orderby" class="dSelect" title="검색분류 선택" onchange="$('#searchForm').submit();">
                                    <option value="regdate" <c:if test="${param.orderby=='regdate'}">selected</c:if>>작성일</option>
                                    <option value="readcount" <c:if test="${param.orderby=='readcount'}">selected</c:if>>조회수</option>
                                </select>
                                <select id="direct" name="direct" class="dSelect" title="검색분류 선택"  onchange="$('#searchForm').submit();">
                                    
                                    <option value="DESC" <c:if test="${param.direct=='DESC'}">selected</c:if>>내림차순</option>
                                    <option value="ASC" <c:if test="${param.direct=='ASC'}">selected</c:if>>오름차순</option>
                                </select>
                                <select id="stype" name="stype" class="dSelect" title="검색분류 선택">
                                    <option value="all">전체</option>
                                    <option value="title" <c:if test="${param.stype=='title'}">selected</c:if>>제목</option>
                                    <option value="content" <c:if test="${param.stype=='content'}">selected</c:if>>내용</option>
                                </select>
                            </span>
                            <span class="searchWord">
                                <input type="text" id="sval" name="sval" value="${param.sval }"  title="검색어 입력">
                                <input type="button" id="" value="검색" title="검색" onclick="$('#searchForm').submit();">
                            </span>
                        </form> 
                    </div>
                </div>
            </div>
        </div>
        <%@ include file="/WEB-INF/view/include/bottom.jsp" %>
    </div>
</body>
</html>