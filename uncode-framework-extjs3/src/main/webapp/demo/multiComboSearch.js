Ext.onReady(function() {
	
	var acceptManager_data = [['全选','1'],['西安','2'],['深圳','3'],['北京','4'],['上海','5']];     
	 qualification_store_MSC = new Ext.data.SimpleStore({  
	        fields: [  
	            "name","value"  
	        ],  
	        proxy: new Ext.data.MemoryProxy(acceptManager_data)  
	        //url:'queryCity'  
	    });  
	    qualification_store_MSC.load();
	
	var divisionStore = new Ext.data.JsonStore({
		autoLoad : true,
		editable : true,
		url : 'http://localhost:8888/extjs/demo/multicombosearch.do',
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
	/** 
	 * 根据条件设置多选下拉框，由于使用过多，抽出模板，简化代码 
	 * @param hidden 
	 * @param id 
	 * @param store 
	 * @param fieldLabel 
	 */
	var multiboxSearch = new Ext.form.MultiComboSearch({
		renderTo : 'multiboxSearch',
		hidden : false,
		id : 'BSSAP_MSC',
		store : qualification_store_MSC,
		valueField : "value",
		displayField : "name",
		// 分隔符，就是每行表单中的label文本显示后面紧接着的那个分隔符
		labelSeparator : ':',
		// 多选显示分隔字符
		displaySeparator : ';',
		// 多选提交到后台的值分隔符
		valueSeparator : '+',
		mode : 'local',
		value : '',
		forceSelection : true,
		hiddenName : 'test',
		selectOnFocus : true,
		triggerAction : 'all',
		// 空字段中显示的文本(默认为 null)。
		emptyText : '',
		listeners : {
			'select' : function() {
				alert(multiboxSearch.getValues());
			}
		}
	});
	multiboxSearch.render(Ext.getBody());
});