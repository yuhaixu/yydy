<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>put order</title>
</head>
<body>

<form action="http://yuhaixu.tpddns.cn:12345/web/ali_pay/put_ali_order" method="post">
    order num:<br>
    <input type="text" name="order_num" value="order_num">
    <br>
    total_amount:<br>
    <input type="text" name="total_amount" value="total_amount">
    <br>
    goods_name:<br>
    <input type="text" name="goods_name" value="goods_name">

    <br><br>
    <input type="submit" value="Submit">
</form>

</body>
</html>
