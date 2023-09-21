<%@ page language="java" contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>project</title>
    <!-- CSS -->
    <link rel="stylesheet" href="<c:url value='/css/selectedCardList.css'/>"/>
</head>
<body>
<div class="wrap__card">
    <%--    <div class="card__inner">--%>
    <section id="nav">
<%--        <div class="return" onclick="location.href='<c:url value='/'/>'">--%>
        <div class="return" >
            <img src="<c:url value='/iconImg/goback.png'/>"   alt=""  onclick="location.href='<c:url value='/'/>'" />
<%--            <img id="hidden_img" src="<c:url value='/iconImg/minus.png'/>" alt=""/>--%>
<%--            <img src="<c:url value='/iconImg/minus.png'/>" alt="" onclick=IconByCards() />--%>
        </div>
    </section>

    <%--        <div class="card__inner">--%>
    <ul class="cards">
        <c:forEach var="selectedCardsList" items="${selectedCardsList}">
            <li>
                <div class="view front">
                    <img src="<c:url value='/img/card${selectedCardsList.cardNo}.jpg'/>" alt=""/>
                </div>
                <div class="view back">
                    <img src="<c:url value='/img/card${selectedCardsList.cardNo}f.jpg'/>" alt=""/>
                </div>
            </li>
        </c:forEach>
    </ul>
    <%--        </div>--%>

    <section id="footer">
        <button class="return" type="button" onclick="copyUrl()">
            <img src="<c:url value='/iconImg/link.png'/>" alt=""/>
        </button>
        <button class="return" type="button" onclick="location.href='<c:url value='/card/deleteAll'/>'">
            <img src="<c:url value='/iconImg/rotate.png'/>" alt=""/>
        </button>
        <button class="return" type="button" onclick="location.href='<c:url value='/card/selectedCardList'/>'">
            <img src="<c:url value='/iconImg/cardImg.png'/>" alt=""/>
        </button>
    </section>
    <%--    </div>--%>
</div>


<script>
    const cards = document.querySelectorAll(".cards li");

    cards.forEach(card => {
        card.addEventListener("click", flipCard);
    });


    let selectCard; // 선택한 카드

    function flipCard(e) {
        let clickedCard = e.target;
        if (clickedCard !== selectCard) {
            selectCard = clickedCard
            selectCard.classList.add("flip");
        } else {
            selectCard.classList.remove("flip");
            selectCard = ""
        }
    };


    function copyUrl() {
        navigator.clipboard.writeText(window.location.href).then(res => {
            alert("주소가 복사되었습니다");
        })
    };



</script>


</body>
</html>
