(function(){tinymce.create('tinymce.plugins.MaxChars',{init:function(ed,url){var t=this;t.editor=ed;ed.addCommand('CheckMaxChars',t._checkMaxChars,t);ed.onPostRender.add(function(ed,o){if(tinymce.isIE){ed.getBody().innerText.replace(/<\/?[^>]+(>|$)/g,"").replace(/&nbsp;/g,"")}else{getContent=ed.getBody().textContent.replace(/<\/?[^>]+(>|$)/g,"").replace(/&nbsp;/g,"")}t._MCtext=ed.getBody().innerHTML},t);ed.onKeyUp.add(t._checkMaxChars,t);ed.onChange.add(t._checkMaxChars,t);ed.onSetContent.add(t._checkMaxChars,t)},getInfo:function(){return{longname:'Maximum Characters',author:'Lorenzo Campanis',authorurl:'http://www.lcampanis.com',infourl:'http://www.lcampanis.com',version:"2.0"}},_checkMaxChars:function(){var ed=this.editor;var mc=parseInt(ed.getParam("max_chars"));var no_spaces=ed.getParam("max_chars_nospaces");var lb=ed.getParam("max_chars_indicator");var mn=ed.getParam("max_chars_text","");var mm=ed.getParam("max_chars_maxText","");var cnt_cur="";var cnt_html="";if(tinymce.isIE){cnt_cur=ed.getBody().innerText.replace(/<\/?[^>]+(>|$)/g,"").replace(/&nbsp;/gi,"");cnt_html=ed.getBody().innerHTML}else{cnt_cur=ed.getBody().textContent.replace(/<\/?[^>]+(>|$)/g,"").replace(/&nbsp;/gi,"");cnt_html=ed.getBody().innerHTML}var cnt=cnt_cur.replace(/<\/?[^>]+(>|$)/g,"");if(no_spaces==1)cnt=cnt.replace(/\s/g,"");if(mc>0&&cnt.length>=0&&mc<cnt.length&&this._MCtext!=cnt_cur){ed.setContent(this._MCtext)}else this._MCtext=cnt_html;if(lb=document.getElementById(lb)){var lb_val=(lb.tagName.toLowerCase()=="input")?lb.value:parseInt(lb.innerHTML);if(lb_val!=null){if(lb.tagName.toLowerCase()=='input'){if(mc-cnt.length<0){if(mm!="")lb.value=mm}else{lb.value=mn+" "+(mc-cnt.length)}}else{if(mc-cnt.length<0){if(mm!="")lb.innerHTML=mm}else{lb.innerHTML=mn+" "+(mc-cnt.length)}}}}return 1}});tinymce.PluginManager.add('maxchars',tinymce.plugins.MaxChars)})();