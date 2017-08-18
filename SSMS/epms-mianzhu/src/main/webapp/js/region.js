var regionSelects = function(root, t,inputName,inputCode){
    var cmbProvince = document.getElementById("cmbProvince");
    var cmbCity = document.getElementById("cmbCity");
    var cmbArea = document.getElementById("cmbArea");
    var cmbTown = document.getElementById("cmbTown");
    var cmbVillage = document.getElementById("cmbVillage");
    
    function cmbAddOption(cmb, str, id, pid, lc){
        var option = document.createElement("OPTION");
        option.innerHTML = str;
        option.value = lc;
        option.pid = pid;
        option.lc = lc;
        cmb.options.add(option);
    }
    
    function cmbSelect(cmb, str){
        for (var i = 0; i < cmb.options.length; i++) {
            if (cmb.options[i].innerHTML == str) {
                cmb.selectedIndex = i;
                return;
            }
        }
    }
    
    function builderOptions(id, obj){
        var p = $("#" + id + " option:selected").val();
        $.getJSON(root + "/region/showSelect/" + p + "/", function(data){
            for (var i = 0; i < data.length; i++) {
                cmbAddOption(obj, data[i].name, data[i].id, data[i].pid, data[i].longcode);
            }
        });
        
    }
	
	function changeVillage(){
		if (cmbVillage.selectedIndex == -1){
			return;
		}             
		var lc = cmbVillage.options[cmbVillage.selectedIndex].lc;
        var v = $("#cmbVillage option:selected").html();
        var o = $("#cmbTown option:selected").html();
        var a = $("#cmbArea option:selected").html();
        var c = $("#cmbCity option:selected").html();
        var p = $("#cmbProvince option:selected").html();
        $("#" + t).html(p + c + a + o +v);
        $("#o_" + inputName).val(p + c + a + o +v);
        $("#o_" + inputCode).val(lc);
    }
    
    function changeTown(){
        //cmbVillage.options.length = 0;
        //cmbVillage.onchange = null;
        if (cmbTown.selectedIndex == -1)             
            return;
        $("#cmbVillage option").remove();
        builderOptions("cmbTown", cmbVillage);
        var o = cmbTown.options[cmbTown.selectedIndex].innerHTML;
        var a = $("#cmbArea option:selected").html();
        var p = $("#cmbProvince option:selected").html();
        var c = $("#cmbCity option:selected").html();
        $("#" + t).html(p + c + a + o);
		//cmbVillage.onchange = changeVillage;
    }
    
    function changeArea(){
        cmbTown.options.length = 0;
        cmbTown.onchange = null;
        if (cmbArea.selectedIndex == -1){
			return;
		}             
        $("#cmbTown option").remove();
		$("#cmbVillage option").remove();
        builderOptions("cmbArea", cmbTown);
        var a = cmbArea.options[cmbArea.selectedIndex].innerHTML;
        var p = $("#cmbProvince option:selected").html();
        var c = $("#cmbCity option:selected").html();
        $("#" + t).html(p + c + a);
        cmbTown.onchange = changeTown;
    }
    
    function changeCity(){
        cmbArea.options.length = 0;
        cmbArea.onchange = null;
        if (cmbCity.selectedIndex == -1)             
            return;
        $("#cmbArea option").remove();
        builderOptions("cmbCity", cmbArea);
        var c = cmbCity.options[cmbCity.selectedIndex].innerHTML;
        var p = $("#cmbProvince option:selected").html();
        $("#" + t).html(p + c);
        cmbArea.onchange = changeArea;
    }
    
    function changeProvince(){
        cmbCity.options.length = 0;
        cmbCity.onchange = null;
        if (cmbProvince.selectedIndex == -1)             
            return;
        $("#cmbCity option").remove();
        builderOptions("cmbProvince", cmbCity);
        
        var p = $("#cmbProvince option:selected").html();
        $("#" + t).html(p);
        cmbCity.onchange = changeCity;
    }
    try{
        cmbProvince.onchange = changeProvince;
        cmbCity.onchange = changeCity;
        cmbArea.onchange = changeArea;
        cmbTown.onchange = changeTown;
    }catch(e){

    }

	//cmbVillage.onchange = changeVillage;
};
