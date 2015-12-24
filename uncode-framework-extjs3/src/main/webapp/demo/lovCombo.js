Ext.onReady(function() {
	var ds = new Ext.data.JsonStore({
		autoLoad : true,
		url : "data.json",
		fields : [ {
			name : 'VALUE'
		}, {
			name : 'TEXT'
		} ],
		root : "datasource"
	});
	var combox = new Ext.ux.form.LovCombo({
		renderTo : Ext.getBody(),
		store : ds,
		mode : "local",
		fieldLabel : "测试",
		displayField : "TEXT",
		valueField : "VALUE",
		hiddenName : "ces",
		name : "ces",
		triggerAction : "all",
		id : "cc",
		// width : 220,
		// autoSelect : true,
		value : "8960,8970,8964,8965,8967,8980",
		// lazyInit : true,
		showSelectAll : true,
		resizable : true
	});
	combox.render(Ext.getBody());
});