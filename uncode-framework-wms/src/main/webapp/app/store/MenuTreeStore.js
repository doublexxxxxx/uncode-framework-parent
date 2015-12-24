Ext.define('MyApp.store.MenuTreeStore', {
	extend : 'Ext.data.TreeStore',
	model : 'MyApp.model.MenuInfo',
	proxy : {
		type : 'ajax',
		url : 'Cart_getTreeInfo.action',
		actionMethods : {
			read : 'POST'
		},
		loading : false
	},
	nodeParam : 'id',
	defaultRootId : 0,
	loading : false
});