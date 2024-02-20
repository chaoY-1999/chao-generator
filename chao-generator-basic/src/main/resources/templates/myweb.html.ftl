<!DOCTYPE html>
<html>
<head>
    <title>chaoY-1999</title>
</head>
<body>
<h1>欢迎来到chaoY-1999官网</h1>
<ul>
    <#list menuItems as item>
        <li><a href="${item.url}">${item.label}</a></li>
    </#list>
</ul>
<footer>
    ${currentYear} chaoY-1999官网. All rights reserved.
</footer>
</body>
</html>