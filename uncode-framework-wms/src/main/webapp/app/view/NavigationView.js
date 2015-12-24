Ext
		.define(
				'MyApp.view.NavigationView',
				{
					extend : 'Ext.panel.Panel',
					xtype : 'navigationview',
					region : 'center',
					layout : 'fit',
					src : 'about:blank',
					iframeId : 'default-0',
					loadingText : 'Loading ...',
					title : 'Index',
					initComponent : function() {
						this.updateHTML();
						this.callParent(arguments);
					},

					updateHTML : function() {
						this.html = '<iframe id="iframe-default-0" name="iframe-default-0"'
								+ ' style="overflow:auto;width:100%;height:100%;"'
								+ ' frameborder="0" '
								+ ' src="'
								+ this.src
								+ '"' + '></iframe>';
					},

					reload : function() {
						this.setSrc(this.src);
					},
					reset : function() {
						var iframe = this.getDOM();
						var iframeParent = iframe.parentNode;
						if (iframe && iframeParent) {
							iframe.src = 'about:blank';
							iframe.parentNode.removeChild(iframe);
						}

						iframe = document.createElement('iframe');
						iframe.frameBorder = 0;
						iframe.src = this.src;
						iframe.id = 'iframe-' + this.iframeId;
						iframe.style.overflow = 'auto';
						iframe.style.width = '100%';
						iframe.style.height = '100%';
						iframeParent.appendChild(iframe);
					},
					setSrc : function(src, loadingText) {
						this.src = src;
						var iframe = this.getDOM();
						if (iframe) {
							iframe.src = src;
						}
					},
					setIFrameId : function(iframeId, loadingText) {
						this.iframeId = iframeId;
						var iframe = this.getDOM();
						if (iframe) {
							iframe.id = iframeId;
						}
					},
					getSrc : function() {
						return this.src;
					},
					getDOM : function() {
						return document.getElementById('iframe-'
								+ this.iframeId);
					},
					getDocument : function() {
						var iframe = this.getDOM();
						iframe = (iframe.contentWindow) ? iframe.contentWindow
								: (iframe.contentDocument.document) ? iframe.contentDocument.document
										: iframe.contentDocument;
						return iframe.document;
					},
					destroy : function() {
						var iframe = this.getDOM();
						if (iframe && iframe.parentNode) {
							iframe.src = 'about:blank';
							iframe.parentNode.removeChild(iframe);
						}
						this.callParent(arguments);
					},

					// call this to manually change content.
					// don't call until component is rendered!!!
					update : function(content) {
						this.setSrc('about:blank');
						try {
							var doc = this.getDocument();
							doc.open();
							doc.write(content);
							doc.close();
						} catch (err) {
							// reset if any permission issues
							this.reset();
							var doc = this.getDocument();
							doc.open();
							doc.write(content);
							doc.close();
						}
					}
				});