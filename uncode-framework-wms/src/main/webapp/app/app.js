Ext.application({
	name : 'MyApp',
	controllers : [ 'MyApp.controller.IndexController' ],
	launch : function() {
		// login code
		var win = Ext.create('MyApp.view.LoginWin');
		win.show();
	}
});