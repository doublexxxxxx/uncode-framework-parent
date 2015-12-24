Ext.define("MyApp.store.User", {
	extend : "Ext.data.Store",
	model : "MyApp.model.User",
	data : [ {
		name : "Tom",
		age : 5,
		phone : "123456"
	}, {
		name : "Jerry",
		age : 3,
		phone : "654321"
	} ]
});