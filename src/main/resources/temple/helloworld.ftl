<html>
<head>
    <title>Welcome!</title>
</head>
<body>
<h1>Welcome </h1>
<p>Our latest product:

       <#list doc.ProjectConf.DBModelDef.Groups.DBModelGroup.DBModels as menus>
            name: "${menus.@name}",
           <#list menus.DBModel as menu>
                     id: "${menu.@id}"   caption: "${menu.@caption}",  cols: "${menu.@cols}"
          </#list>
        </#list>

</body>
</html>