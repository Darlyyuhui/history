/**
 * 给文本中的特殊符号添加转义符号
 * 也有网友说是，利用jQuery.json插件里面的quoteString方法。对特殊字符进行处理
 */
var replaceEscape = function(){
    var escapeable = /["\\\x00-\x1f\x7f-\x9f]/g,
        meta = {
            '\b': '\\b',
            '\t': '\\t',
            '\n': '\\n',
            '\f': '\\f',
            '\r': '\\r',
            '"' : '\\"',
            '\\': '\\\\'
        };
    return {execute:function(string){
        if ( string.match( escapeable ) ) {
            return '"' + string.replace( escapeable, function( a ) {
                var c = meta[a];
                if ( typeof c === 'string' ) {
                    return c;
                }
                c = a.charCodeAt();
                return '\\u00' + Math.floor(c / 16).toString(16) + (c % 16).toString(16);
            }) + '"';
        }
        return   string  ;
    }, getTxt:function(string){
        //去掉所有的html标签
        var dd = string.replace(/<\/?.+?>/g,"");
        //去掉所有的空格
        var dds = dd.replace(/&nbsp;/g,"");
        //去掉首位引号
        return dds.replace(/^\"|\"$/g,"");
    }}
}();