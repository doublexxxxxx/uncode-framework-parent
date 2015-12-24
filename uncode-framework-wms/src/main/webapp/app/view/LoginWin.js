Ext.define('MyApp.view.LoginWin',{
	extend: 'Ext.window.Window',
	alias: 'widget.loginwin',
	title: 'Login',
    width: 340,
    height: 200,
    plain: true,
    headerPosition: 'top',
    layout: 'fit',
    renderTo: Ext.getBody(),
    constrain: true,
    buttonAlign: 'center',
    resizable: false,
    requires: ['MyApp.view.form.LoginForm'],
    initComponent : function() {
    	this.buttons = [
	        {text: 'login',action: 'winlogin'},
	        {text: 'reset',action: 'winreset'}
    	];
    	this.items = [
    	    {xtype: 'loginform'}
    	];
    	
		this.callParent(arguments);
	}
});