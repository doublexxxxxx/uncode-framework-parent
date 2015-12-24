Ext.define('MyApp.view.TreeView',{
	extend: 'Ext.tree.Panel',
	xtype: 'treeView',
	store: Ext.create('MyApp.store.MenuTreeStore', {}),
    rootVisible: false,
    width: 250,
    useArrows: true
});