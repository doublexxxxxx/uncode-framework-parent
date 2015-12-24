Ext.define('MyApp.view.MenuView',{
	extend: 'Ext.panel.Panel',
	xtype: 'menuview',
	alias: 'widget.menuview',
	requires: ['MyApp.view.TreeView'],
	layout:'accordion',
	region: 'west',
	width: 250,
	id: 'accordion-menu',
    layoutConfig: {
        titleCollapse: true,
        animate: true,
        activeOnTop: true
    },
    dockedItems: [{
        dock: 'bottom',
        xtype: 'toolbar',
        items: [
             {
            	 text: 'relogin',
            	 iconCls: 'add16',
            	 action: 'relogin'
             }
        ]
    }],
    listeners: {
    	beforerender: function(thiz, opts) {
    		Ext.Ajax.request({
    		    url: 'Cart_getMenuInfo.action',
    		    method: 'POST',
    		    success: function(response, opts) {
    		        var obj = Ext.decode(response.responseText);
    		        var root = obj.root;
    		        
    		        Ext.iterate(root, function(menu){
    		        	thiz.add({
    	    				xtype: 'panel', 
    	    				title: menu.name, 
    	    				value: menu.id,
    	    				layout: 'fit',
    	    				items: [{xtype: 'treeView'}]
    	    			});
    		        });    		        
    		    },
    		    failure: function(response, opts) {
    		        console.log('fail' + response.status);
    		    }
    		});
    	}
    }
});