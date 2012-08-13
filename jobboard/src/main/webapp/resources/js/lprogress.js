/*
* Author: blhwk (c) 2012
* Free JQuery Progreess plugin
*/
(function ($) {
    $.fn.progress = function (sett) {
        var options = $.extend({ width: 100, height: 10, prog: 0 }, sett);
        $.fn.extend({
            set: function (s) {
                prog = options.width / 100 * s;
                $(this).find("#lp-bar").width(prog);
            }
        });

        $.fn.extend({
            color: function (c) {
                $(this).find("#lp-bar").attr("class", "lp-" + c);
            }
        });

        $.fn.extend({
            text: function (t) {
                $(this).find("#ext-t").html(t);
            }
        });

        var _iel = '<div id="ext-bar" class="lp-wrap"  style="width: ' + options.width + 'px; ">';
        _iel += '<div class="lp-inner">';
        _iel += '<div id="lp-bar" class="lp-blue" style="height: ' + options.height + 'px; width: ' + options.prog + 'px; "></div>';
        _iel += '<div id="ext-txt" class="lp-text lp-text-back" >';
        _iel += '<div id="ext-t" class="lp-text-t" style="width: ' + options.width + 'px; height: ' + options.height + 'px"></div>';
        _iel += '</div></div></div>';

        $(this).append(_iel);

        return $(this);
    };
})(jQuery);