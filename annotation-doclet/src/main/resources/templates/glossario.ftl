<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <script src="http://code.jquery.com/jquery-1.11.3.js"></script>

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
</head>
<body>
<div class="titulo"><h2>Glosario</h2></div>
<#list classes as class>
<div class="clase-nombre col-md-3"><#if class.deprecated><strike></#if><strong>${class.name}</strong><#if class.deprecated></strike></#if></div><div class="clase-com col-md-9">${class.comment}</div>
    <#list class.methods as method>
    <div class="col-md-1"></div>
    <div class="metodo-firma col-md-2"><em>${method.signature}</em></div>
    <div class="metodo-com col-md-9">${method.comment}</div>
    </#list>
    <#list class.deriveds as derived>
    <div class="col-md-1"></div>
    <div class="derivada-nombre col-md-2">${derived.name}</div>
    <div class="derivada-com col-md-9">${derived.comment}</div>
    </#list>
    <div class="clear"></div>
    <hr />
</#list>
</body>
</html>