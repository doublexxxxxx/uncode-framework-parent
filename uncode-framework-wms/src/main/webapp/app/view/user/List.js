Ext.define("MyApp.view.user.List", {
	extend : "Ext.grid.Panel",
	alias : 'widget.userlist',
	store : "User",
	initComponent : function() {
		this.columns = [ {
			text : '姓名',
			dataIndex : 'name'
		}, {
			text : '年龄',
			dataIndex : 'age',
			xtype : 'numbercolumn',
			format : '0'
		}, {
			text : '电话',
			dataIndex : 'phone'
		} ];
		this.callParent(arguments);
	}
});