Ext.onReady(function() {
	/*var myData = [ [ 3300, "彭仁夔" ], [ 3301, "李明" ], [ 3302, "王华" ], [ 3303, "张三" ], [ 3304, "李四" ], [ 3305, "王五" ], [ 3306, "彭小明" ], [ 3307, "张华" ],
			[ 3308, "李小" ] ];
	var store1 = new Ext.data.SimpleStore({
		fields : [ {
			name : 'value',
			type : 'string'
		}, {
			name : 'name',
			type : 'string'
		} ],
		data : myData
	});*/
	var divisionStore = new Ext.data.JsonStore({
		autoLoad : true,
		editable : true,
		url : 'http://localhost:8888/extjs/demo/multicombox.do',
		fields : [ {
			name : 'value',
			mapping : 'value'
		}, {
			name : 'name',
			mapping : 'name'
		} ],
		listeners : {
			'load' : function() {
			}
		}
	});
	var test = new Ext.ux.form.MultiComBox({
		store : divisionStore,
		displayField : 'name',
		valueField : 'value', // 
		typeAhead : true,
		triggerAction : 'all',
		mode : 'local',
		emptyText : '请选择...',
		selectOnFocus : true,
		loadingText : 'loading....'
	})
	test.render(Ext.getBody());
});