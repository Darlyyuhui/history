MapFactory.Define("ItmsMap/UserLayers/CustomLayers/Tingche*",[
	"ItmsMap/UserLayers/CustomLayersAPI*",
	"ItmsMap/Parking/ParkingLot*"
],function(api,ParkingLot){	
	function showLayers(data) {
		var park = ParkingLot();
		park.showLeadDevice(data.leadDevice.isOpen);
		park.showGenParking(data.genParking.isOpen);
		park.showDirParking(data.dirParking.isOpen);
	}
	return eval(MapFactory.GenerateAPI(api));
});