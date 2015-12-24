Ext.define('MyApp.view.Viewport', {
	extend : 'Ext.container.Viewport',
	requires : [ 'MyApp.view.MenuView', 'MyApp.view.NavigationView' ],
	layout : 'border',
	items : [ {
		xtype : 'menuview'
	}, {
		xtype : 'navigationview'
	} ]
});
