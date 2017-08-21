<div id="sidemenu" style="padding:0;">
    <#list menus as sub>
	     <p onmouseover="change_bgcolor('${sub.id}')" style="border-bottom: 1px solid #B2B2B2;line-height: 28px;"><a data-toggle="collapse" id="${sub.id}" onclick="add_to_desk('${sub.id}', '${sub.name}')" style="color:blue;">${sub.name}</a></p>
    </#list>
</div>