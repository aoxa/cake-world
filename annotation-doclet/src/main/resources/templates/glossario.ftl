<html>
<head>
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css">

    <!-- Optional theme -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap-theme.min.css">

    <!-- Latest compiled and minified JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
    <!--
    <style>
        .clase-nombre{ width: 35%;float: left; margin-top: 10px; text-decoration: underline; }
        .clase-com{ width: 65%; float: left; }
        .metodo-firma{ width: 35%; margin-left: 10px; float: left; }
        .metodo-com{ width: 55%; float: left; }
        .derivada-nombre{ width: 35%; margin-left: 10px; float: left; }
        .derivada-com{ width: 55%; float: left; }
        .clear { clear: both; }
    </style>
    -->
</head>
<body>
<div class="titulo"><h2>Glosario</h2></div>
<#list classes as class>
<div class="clase-nombre col-md-3"><strong>${class.name}</strong></div><div class="clase-com col-md-9">${class.comment}</div>
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