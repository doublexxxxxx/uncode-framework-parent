Ext.define('MyApp.view.form.LoginForm', {
	extend : 'Ext.form.Panel',
	xtype : 'loginform',
	alias : 'widget.loginform',
	frame : false,
	bodyPadding : 10,
	border : 0,
	id : 'loginform-id',
	fieldDefaults : {
		labelAlign : 'left',
		anchor : '98%',
		labelWidth : 80
	},
	layout : {
		type : 'vbox',
		align : 'stretch',
		pack : 'center'
	},
	items : [ {
		xtype : 'textfield',
		allowBlank : false,
		fieldLabel : 'UserName',
		name : 'params.name'
	}, {
		xtype : 'textfield',
		allowBlank : false,
		fieldLabel : 'UserPass',
		name : 'params.psw',
		inputType : 'password'
	}, {
		xtype : 'checkboxgroup',
		vertical : false,
		items : [ {
			boxLabel : 'remAccount',
			name : 'remAccount',
			checked : true
		}, {
			boxLabel : 'remPass',
			name : 'remPass'
		} ]
	} ]
});