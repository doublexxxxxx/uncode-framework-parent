Ext.define('MyApp.controller.IndexController', {
	extend : 'Ext.app.Controller',
	views : [ 'MyApp.view.TreeView', 'MyApp.view.NavigationView',
			'MyApp.view.LoginWin', 'MyApp.view.form.LoginForm' ],
	refs : [ {
		ref : 'navigationView',
		selector : 'navigationview'
	}, {
		ref : 'loginWin',
		selector : 'loginwin'
	}, {
		ref : 'loginForm',
		selector : 'loginform'
	}, {
		ref : 'menuView',
		selector : 'menuview'
	} ],

	init : function() {
		var me = this;

		me.control({
			'treeView' : {
				itemclick : me.onTreeItemClick
			},

			'loginwin button[action=winreset]' : {
				click : me.winReset
			},

			'loginwin' : {
				show : me.showWinAfter
			},

			'loginwin button[action=winlogin]' : {
				click : me.winLogin
			},

			'menuview button[action=relogin]' : {
				click : me.relogin
			},

			'menuview > panel' : {
				expand : me.extendAccordion
			},

			'loginform > [name="params.psw"]' : {
				specialkey : me.handleKey
			},

			'loginform > [name="params.name"]' : {
				specialkey : me.handleKey
			}
		});
	},

	extendAccordion : function(curPanel, opts) {
		var val = curPanel.value, tree = curPanel.items.get(0);

		if (!tree.store.isLoading()) {
			tree.store.load({
				id : val
			});
		}
	},

	// handler enter key
	handleKey : function(field, e) {
		var me = this;
		if (e.getKey() == e.ENTER) {
			me.winLogin();
		}
	},

	// load default value
	showWinAfter : function() {
		var cookies = Ext.util.Cookies;
		var form = this.getLoginForm(), f = form.form;

		f.findField('params.name').setValue(cookies.get('lastName'));
		f.findField('remAccount').setValue(cookies.get('remAccount'));

		f.findField('params.psw').setValue(cookies.get('lastPsw'));
		f.findField('remPass').setValue(cookies.get('remPass'));

	},

	// 重新登录
	relogin : function() {
		console.log(Ext.roleInfo.loginName);
	},

	// login form
	winLogin : function() {
		var form = this.getLoginForm(), f = form.form;
		var win = this.getLoginWin(), me = this;

		if (f.isValid()) {
			f.doAction('submit', {
				clientValidation : true,
				url : 'MyApp_userLogin.action',
				method : 'POST',

				waitMsg : 'logining, please waiting...',
				waitTitle : 'wait',
				success : function(baseForm, action) {
					var obj = action.result;

					if (obj.msg == 'success') {
						// 创建首页前可以加入登录窗口.
						win.close();
						Ext.create('MyApp.view.Viewport');

						me.saveCookInfo(baseForm);
					} else {
						Ext.Msg.alert('tip', obj.msg);
					}
				}
			});
		}
	},
	// set cookies
	saveCookInfo : function(bf) {
		var cookies = Ext.util.Cookies;

		var name = bf.findField('params.name').getValue(), psw = bf.findField(
				'params.psw').getValue(), remAccount = bf.findField(
				'remAccount').getValue(), remPass = bf.findField('remPass')
				.getValue();

		cookies.set('remAccount', remAccount);
		cookies.set('lastName', name);
		if (!remAccount) {
			cookies.set('lastName', '');
		}

		cookies.set('lastPsw', psw);
		cookies.set('remPass', remPass);
		if (!remPass) {
			cookies.set('lastPsw', '');
		}

		this.setLoginInfo(name, psw);
	},

	// set role
	setLoginInfo : function(name, psw) {
		Ext.apply(Ext.roleInfo, {
			'loginName' : name,
			'loginPsw' : psw
		});
	},

	// reset form
	winReset : function() {
		var form = this.getLoginForm();
		form.form.reset();
	},

	// tree item click
	onTreeItemClick : function(view, record, item, index, e, opts) {
		var panel = this.getNavigationView();

		panel.setTitle(record.data.text);
	}
});